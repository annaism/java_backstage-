package com.joker.controller;

import com.joker.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/02/10:17
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class LoginConntroller {
    //login
    @PostMapping("login")
    public R login() {

        return R.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public R info() {
        System.out.println("又来了");
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
