package com.joker.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/23/17:15
 * @Description:
 */
public enum ConstantException {

    DeleetNoSelectError("请选择需要删除的记录",21111),
    ParameterErrorError("参数异常",21112)


    ;



    private String msg;
    private Integer code;

    ConstantException(String msg,Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
