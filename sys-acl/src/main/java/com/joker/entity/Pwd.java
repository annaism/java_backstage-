package com.joker.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/10/10:14
 * @Description:
 */
@Data
public class Pwd implements Serializable {

    private String old;
    private String newpwd;
    private String newagin;
}
