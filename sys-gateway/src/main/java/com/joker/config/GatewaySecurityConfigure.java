package com.joker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/14:01
 * @Description:
 */
@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfigure {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //配置白名单和访问规则，CommonEnum枚举类
        http.csrf().disable();
        return http.build();
    }

}
