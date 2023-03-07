package com.joker.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.joker.utils.JwtUtils;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.joker.constant.Constant.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/18:20
 * @Description:
 */
@Component
@ControllerAdvice
@Slf4j
public class LoginAndRefreshTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,Object> stringRedisTemplate;
    public static ThreadLocal<UserVo> userMainData = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的token
        String token_header = request.getHeader("Authorization");

        // 2.基于TOKEN获取redis中的用户
        UserVo user = JwtUtils.getUser(token_header);
        String token_redis = (String) stringRedisTemplate.opsForValue().get(TOKEN_PRE_PREFIX.getMsg() + user.getId());

        log.error(token_redis);
        // token不存在，或者token不同均不放行
        if (StringUtils.isBlank(token_redis) || !token_redis.equals(token_header)) {
            throw new TokenExpiredException("TOKEN不存在或过期，请登录");
        }

        userMainData.set(user);

        // 7.刷新token有效期
        stringRedisTemplate.expire(TOKEN_PRE_PREFIX.getMsg() + user.getId(), 7, TimeUnit.DAYS);
        stringRedisTemplate.expire(ROLES_PRE_PREFIX.getMsg() +user.getId(), 7, TimeUnit.DAYS);
        stringRedisTemplate.expire(PERSSION_PRE_PREFIX.getMsg() + user.getId(), 7, TimeUnit.DAYS);


        // 8.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        userMainData.remove();
    }

}


