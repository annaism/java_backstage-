package com.joker.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.utils.JwtToken;
import com.joker.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/03/16:58
 * @Description:
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 进行token的验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //在请求头中获取token
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpServletRequest.getHeader("Authorization"); //前端命名Authorization
        //token不存在
        if(token == null || "".equals(token)){
            R msg = R.error().data("msg", "无token，无权访问，请先登录");
            httpServletResponse.getWriter().write(JSON.toJSONString(msg));
            return false;
        }

        //token存在，进行验证
        JwtToken jwtToken = new JwtToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);  //通过subject，提交给myRealm进行登录验证
            return true;
        } catch (ExpiredCredentialsException e){
            R msg = R.error().data("msg", "token过期，请重新登录");
            httpServletResponse.getWriter().write(JSON.toJSONString(msg));
            e.printStackTrace();
            return false;
        } catch (ShiroException e){
            R msg = R.error().data("msg", "token被伪造，无效token");
            httpServletResponse.getWriter().write(JSON.toJSONString(msg));
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 过滤器拦截请求的入口方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);  //token验证
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * isAccessAllowed()方法返回false，即认证不通过时进入onAccessDenied方法
     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        return super.onAccessDenied(request, response);
//    }

    /**
     * token认证executeLogin成功后，进入此方法，可以进行token更新过期时间
     */
//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

//    }


    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/" + message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
