package com.joker.serivce;

import com.joker.entity.Login;
import com.joker.utils.R;
import com.netflix.client.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/12:00
 * @Description:
 */

public interface LoginService {
    public R login(Login login);

    public R logout(HttpServletRequest request);
}
