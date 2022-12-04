package com.joker.exceptionhandler;

import com.joker.exception.GlobalException;
import com.joker.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/11/30/23:06
 * @Description:全局异常处理器
 */
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().data("msg","执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(GlobalException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().data("msg","执行了GlobalException异常处理..");
    }

    //自定义异常
//    @ExceptionHandler(GuliException.class)
//    @ResponseBody //为了返回数据
//    public R error(GuliException e) {
//        log.error(e.getMessage());
//        e.printStackTrace();
//
//        return R.error().code(e.getCode()).message(e.getMsg());
//    }
}
