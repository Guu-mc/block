package com.mc.block.img.file.web.advice;

import com.mc.block.commom.ResultUtil;
import com.mc.block.confine.result.Result;
import com.mc.block.constant.UsuallyCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception e){
        return ResultUtil.error(UsuallyCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
