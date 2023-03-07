package com.joker.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/23/17:15
 * @Description:
 */
public enum ConstantException {

    HeadPicExceptionReturn("上传失败，请稍后再试",21110),
    DeleetNoSelectError("请选择需要删除的记录",21111),
    ParameterErrorError("参数异常",21112),

    GlobalExceptionReturn("系统异常，请联系管理员",00000),
    ExceptionReturn("未知异常，请联系管理员",00001),

    AlgorithmMismatchExceptionReturn("非法TOKEN,请重新登录",50008),
    SignatureVerificationExceptionReturn("非法TOKEN,请重新登录",50008),
    TokenExpiredExceptionReturn("TOKEN不存在或过期，请重新登录",50014),


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
