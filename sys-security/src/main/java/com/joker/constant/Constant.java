package com.joker.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/17:33
 * @Description:
 */
public enum Constant {

    ROLES_PRE_PREFIX("ROLE:"),
    TOKEN_PRE_PREFIX("TOKEN:"),
    PERSSION_PRE_PREFIX("PERSSION:"),
    INF_MENU_PRE_PREFIX("INFO:MENU:");



    public String getMsg() {
        return msg;
    }

    ;





    public String msg;

    Constant(String msg) {
        this.msg = msg;
    }

}
