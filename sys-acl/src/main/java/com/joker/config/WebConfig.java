package com.joker.config;


import com.joker.interceptor.LoginAndRefreshTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/04/16:14
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoginAndRefreshTokenInterceptor getRefreshTokenInterceptor(){
        return new LoginAndRefreshTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getRefreshTokenInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/api/user/check",
                "/api/acl/permission/getassign",
                "/api/user/toalassign");
    }

}
