package com.joker.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/06/11:01
 * @Description:
 */
public class HeadPicException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code;

    public HeadPicException(int code,String msg){
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
