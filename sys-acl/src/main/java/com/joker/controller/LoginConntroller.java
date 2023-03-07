package com.joker.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joker.entity.*;
import com.joker.interceptor.LoginAndRefreshTokenInterceptor;
import com.joker.service.*;
import com.joker.to.UserTo;
import com.joker.utils.*;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.joker.constant.Constant.*;
//import static org.apache.shiro.web.filter.mgt.DefaultFilter.perms;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/02/10:17
 * @Description:
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@Validated
@ControllerAdvice
public class LoginConntroller {

    @Autowired
    private UserService userService;

    @Autowired
    private AclUserRoleService userRoleService;

    @Autowired
    private AclRolePermissionService rolePermissionService;

    @Autowired
    private AclPermissionService permissionService;

    @Autowired
    private AclRoleService roleService;

    // 注入 RedisTemplate
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取info --- 获取角色和其他基础信息
     * @return
     */
    @RequestMapping("info")
    public R info() {
        UserVo userVo = LoginAndRefreshTokenInterceptor.userMainData.get();
        log.info("{{}}",userVo);

        //获取用户所有角色ID
        QueryWrapper<AclUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userVo.getId());

        List<AclUserRole> list = userRoleService.list(wrapper);
        List<Long> roleIds = list.stream().map((userRole) -> {
            return userRole.getRoleId();
        }).collect(Collectors.toList());


        Set<String> roles = new HashSet<>();// 所有角色
        List<AclRole> aclRoles = roleService.listByIds(roleIds);
        for (AclRole role:aclRoles) {
            roles.add( role.getRoleName() );
        }

        User user = userService.getById(userVo.getId());
        log.info("{{}}",roles);

        return R.ok().data("roles",roles).data("name",user.getNickName()).data("avatar",user.getHeadPic());
    }



    /**
     * 获取菜单信息 -- 使用缓存
     * @return
     */
    @RequestMapping("menu")
    public R menu() {

        UserVo userVo = LoginAndRefreshTokenInterceptor.userMainData.get();
        log.info("{{}}",userVo);

        Long size = redisTemplate.opsForList().size(INF_MENU_PRE_PREFIX.getMsg() + userVo.getId()) - 1;

        if( size > -1){
            List<String> range = redisTemplate.opsForList().range(INF_MENU_PRE_PREFIX.getMsg() + userVo.getId(), 0, size);

            List<JSONObject> permissionList = new ArrayList<>();
            range.stream().forEach(obj -> {
                permissionList.add( JSONObject.parseObject(obj));
            });
            return R.ok().data("permissionList", permissionList);
        }


        List<JSONObject>  permissionList = permissionService.selectPermissionByUserId(userVo.getId());

        log.info("{{}}",permissionList);
        log.info(JSON.toJSONString(permissionList));

        permissionList.stream().forEach(jsonObject -> {
            redisTemplate.opsForList().rightPush(INF_MENU_PRE_PREFIX.getMsg() + userVo.getId(),JSON.toJSONString(jsonObject));
        });
        redisTemplate.expire(INF_MENU_PRE_PREFIX.getMsg() + userVo.getId(),6000000,TimeUnit.SECONDS);

        return R.ok().data("permissionList", permissionList);

    }


    //获取用户
    @RequestMapping("/check")
    public R register(@RequestParam("phone") String phone){
        log.info("{{}}",phone);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        User user = userService.getOne(wrapper);
        if( user != null ){
            UserTo userTo = new UserTo();
            BeanUtils.copyProperties(user,userTo);

            return R.ok().data("user",JSON.toJSONString(userTo));
        }

        return R.error();

    }


}
