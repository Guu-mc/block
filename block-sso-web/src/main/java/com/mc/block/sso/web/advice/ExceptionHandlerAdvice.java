package com.mc.block.sso.web.advice;

import com.mc.block.commom.ResultUtil;
import com.mc.block.confine.result.Result;
import com.mc.block.constant.UsuallyCode;
import com.mc.block.exception.EException;
import com.mc.block.exception.ResultParamException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResultParamException.class)
    public Result resultParamErrorHandler(ResultParamException e){
        return ResultUtil.error(UsuallyCode.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(EException.class)
    public Result eErrorHandler(EException e){
        return ResultUtil.error(UsuallyCode.SERVICE_UNAVAILABLE, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception e){
        return ResultUtil.error(UsuallyCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
