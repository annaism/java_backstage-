package com.joker.exceptionhandler;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.joker.exception.DeletNoSelectException;
import com.joker.exception.GlobalException;
import com.joker.exception.HeadPicException;
import com.joker.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

import static com.joker.constant.Constant.*;
import static com.joker.constant.ConstantException.*;

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

    @ExceptionHandler(TokenExpiredException.class)
    public R handleError(TokenExpiredException e){
        R error = R.error();
        error.setMessage(TokenExpiredExceptionReturn.getMsg());
        error.setCode(TokenExpiredExceptionReturn.getCode());
        return error;
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public R handleError(SignatureVerificationException e){
        R error = R.error();
        error.setMessage(SignatureVerificationExceptionReturn.getMsg());
        error.setCode(SignatureVerificationExceptionReturn.getCode());
        return error;
    }

    @ExceptionHandler(AlgorithmMismatchException.class)
    public R handleError(AlgorithmMismatchException  e){
        R error = R.error();
        error.setMessage(AlgorithmMismatchExceptionReturn.getMsg());
        error.setCode(AlgorithmMismatchExceptionReturn.getCode());
        return error;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleError(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String message = String.format("%s",fieldError.getDefaultMessage());

        R error = R.error();
        error.setMessage(message );
        return error;
    }


    //头像上传异常
    @ExceptionHandler(HeadPicException.class)
    public R handle(HeadPicException e) {
        e.printStackTrace();

        R error = R.error();
        error.setMessage(HeadPicExceptionReturn.getMsg() );
        error.setCode(HeadPicExceptionReturn.getCode());
        return error;

    }


    //特定异常
    @ExceptionHandler(GlobalException.class)
    public R error(ArithmeticException e) {
        e.printStackTrace();

        R error = R.error();
        error.setMessage(GlobalExceptionReturn.getMsg());
        error.setCode(GlobalExceptionReturn.getCode());
        return error;
    }

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();

        R error = R.error();
        error.setMessage(ExceptionReturn.getMsg());
        error.setCode(ExceptionReturn.getCode());
        return error;
    }

}
