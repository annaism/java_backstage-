package com.joker.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/03/15:25
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTo {
    /**
     * 登录账户
     */
    private String phone;

    /**
     * 账号密码
     */
    private String password;
    
    /**
     * ID
     */
    private Long id;

    private String salt;
}
