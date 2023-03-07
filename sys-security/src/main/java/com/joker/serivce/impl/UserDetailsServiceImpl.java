package com.joker.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joker.entity.LoginUser;
import com.joker.exception.GlobalException;
import com.joker.fegin.UserFeginService;
import com.joker.to.UserTo;
import com.joker.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/11:09
 * @Description:
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserFeginService service;

    @Autowired
    RedisTemplate<String ,Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {


            R r = service.getUserInfoByAccount(phone);
            log.info("{{}}",service);
            log.info("{{}}",r);

            if( r.getCode() != 20000){
                throw new RuntimeException("接口繁忙，清稍后再试");
            }

            Map<String, Object> map =  (Map<String, Object>)r.getData();
            String jsonUser = (String)map.get("user");
            UserTo user = JSON.parseObject(jsonUser, UserTo.class);
            log.info("{{}}",user);

            // 如果查询不到数据，说明用户名或者密码错误，直接抛出异常
            if(user == null) {
                throw new RuntimeException("手机号或者密码错误") ;
            }


        R r_role = service.toAssignRole(user.getId());

        if( r_role.getCode() != 20000){
            throw new RuntimeException("接口繁忙，清稍后再试");
        }

        log.info("{{}}",r_role);

        Map<String, Object> data = r_role.getData();

        String jsonString = (String)data.get("list");

        log.info("{{}}",jsonString);

        JSONArray array = JSON.parseArray(jsonString);

        List<Long> ids = new ArrayList<>();
        List<String> rolesName = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = (JSONObject)array.get(i);
            ids.add( Long.parseLong(obj.get("id").toString()));
            rolesName.add((String) obj.get("roleName"));
        }

        R rPermission = service.toAssignPermission(ids);

        if( rPermission.getCode() != 20000){
            throw new RuntimeException("接口繁忙，清稍后再试");
        }

        Map<String, Object> rPermissionData = rPermission.getData();
        List<String> paths = (List<String>)rPermissionData.get("paths");


        log.info("{{}}",paths);


        redisTemplate.opsForValue().set("ROLE:"+user.getId(),rolesName, 7,TimeUnit.DAYS);
        redisTemplate.opsForValue().set("PERSSION:"+user.getId(),paths,7,TimeUnit.DAYS);


        // 将查询到的对象转换成Spring Security所需要的UserDetails对象
        return new LoginUser(user);
    }
}
