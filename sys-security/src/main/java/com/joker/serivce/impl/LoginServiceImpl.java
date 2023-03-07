package com.joker.serivce.impl;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.joker.entity.Login;
import com.joker.entity.LoginUser;
import com.joker.serivce.LoginService;
import com.joker.to.UserTo;
import com.joker.utils.JwtUtils;
import com.joker.utils.R;
import com.joker.vo.UserVo;
import com.netflix.client.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.joker.constant.Constant.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/12:03
 * @Description:
 */
@Service
@Slf4j

public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private RedisTemplate<String , Object> redisTemplate ;


    @Override
    public R login(Login login) {

        log.info("{{}}",authenticationManager);

        // 创建Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getPhone() , login.getPassword()) ;

        // 调用AuthenticationManager的authenticate方法进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if(authentication == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 将用户的数据存储到Redis中
        LoginUser user = (LoginUser) authentication.getPrincipal();
        UserTo userTo = user.getUser();

        UserVo vo = new UserVo();
        BeanUtils.copyProperties(userTo,vo);
        String token = JwtUtils.getJwtToken(vo,userTo.getSalt());

        redisTemplate.opsForValue().set(TOKEN_PRE_PREFIX.getMsg() + userTo.getId(),token, 7,TimeUnit.DAYS);

        return R.ok().data("token",token);
    }


    @Override
    public R logout(HttpServletRequest request) {

        // 1.获取请求头中的token
        String token = request.getHeader("Authorization");

        if( StringUtils.isEmpty( token )){
            throw new TokenExpiredException("未登录");
        }

        UserVo user = JwtUtils.getUser(token);

        // 删除Redis中的用户数据
        redisTemplate.delete(ROLES_PRE_PREFIX.getMsg() + user.getId()) ;
        redisTemplate.delete(PERSSION_PRE_PREFIX.getMsg() + user.getId()) ;
        redisTemplate.delete(INF_MENU_PRE_PREFIX.getMsg()+ user.getId()) ;
        redisTemplate.delete(TOKEN_PRE_PREFIX.getMsg() + user.getId()) ;


        R ok = R.ok();
        ok.setMessage("已登出");
        // 返回
        return ok ;
    }
}
