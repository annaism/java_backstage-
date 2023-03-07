package com.joker.controller;

import com.joker.entity.Login;
import com.joker.exception.GlobalException;
import com.joker.serivce.LoginService;
import com.joker.utils.R;
import com.netflix.client.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
//import static org.apache.shiro.web.filter.mgt.DefaultFilter.perms;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/02/10:17
 * @Description:
 */
@RestController
@RequestMapping("/api/all/user")
@Slf4j
@ControllerAdvice
public class LoginController {


    @Autowired
    LoginService service;


    //登录认证
    @RequestMapping("/login")
    public R login(@RequestBody  Login login) throws GlobalException {

        R r = service.login(login);

        return r;
    }

    //登录认证
    @RequestMapping("/logout")
    public R logout(HttpServletRequest request) throws GlobalException {

        R r = service.logout(request);

        return r;
    }




}
