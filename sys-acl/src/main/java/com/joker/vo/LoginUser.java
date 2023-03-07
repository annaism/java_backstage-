package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/04/11:22
 * @Description:
 */
@Data
public class LoginUser {

    private String account;
    private String password;
}
