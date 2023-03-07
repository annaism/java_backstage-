package com.joker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/11:58
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String phone;
    private String password;
}
