package com.joker.exceptionhandler;

import com.joker.exception.DeletNoSelectException;
import com.joker.exception.GlobalException;
import com.joker.exception.HeadPicException;
import com.joker.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

import static com.joker.constant.ConstantException.ParameterErrorError;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/11/30/23:06
 * @Description:全局异常处理器
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    //头像上传异常
    @ExceptionHandler(HeadPicException.class)
    public R handle(HeadPicException e) {
        e.printStackTrace();
        return R.error().data("msg",e.getMessage()).data("code",e.getCode());
    }

    //删除未选择异常
    @ExceptionHandler(DeletNoSelectException.class)
    public R handle(DeletNoSelectException e) {
        e.printStackTrace();
        return R.error().data("msg",e.getMessage()).data("code",e.getCode());
    }

    //参数异常
    @ExceptionHandler(ValidationException.class)
    public R handle(ValidationException exception) {
        return R.error().data("msg",ParameterErrorError.getMsg()).data("code",ParameterErrorError.getCode());
    }


    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().data("msg",e.getMessage());
    }

    //特定异常
    @ExceptionHandler(GlobalException.class)
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().data("msg","执行了GlobalException异常处理..");
    }

}
