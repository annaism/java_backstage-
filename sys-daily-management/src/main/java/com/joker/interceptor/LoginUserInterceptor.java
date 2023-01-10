package com.joker.interceptor;

import com.joker.utils.JwtUtils;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/03/23:30
 * @Description:
 */
@Component
@Slf4j
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserVo> userMainData = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("{{}}",request.getRequestURI());

        if ("OPTIONS".equals(request.getMethod().toUpperCase()) || request.getRequestURI().equals("/api/daily/uploadImage")) {
            return true;
        }

        HttpSession session = request.getSession();

        //前端需要将token放到请求头中
        String token = request.getHeader("Authorization");

        UserVo user = JwtUtils.getUser(token);
        log.info("{{}}",user);

        if( user != null ){

            userMainData.set(user);

            return true;
        }
        //未登录
        else {
            return false;
        }
    }
}
