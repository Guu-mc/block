package com.mc.block.web.advice;

import com.mc.block.commom.ResultUtil;
import com.mc.block.confine.result.Result;
import com.mc.block.constant.Post;
import com.mc.block.constant.UsuallyCode;
import com.mc.block.exception.EException;
import com.mc.block.exception.ResultParamException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResultParamException.class)
    public Result resultParamErrorHandler(ResultParamException e){
        System.out.println("请求参数异常 : " + e.getMessage());
        return ResultUtil.error(UsuallyCode.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(EException.class)
    public Result eErrorHandler(EException e){
        System.out.println("异常 : " + e);
        return ResultUtil.error(UsuallyCode.SERVICE_UNAVAILABLE, "ErrorType : "+e.getType().name()+" Message e : "+e.getMessage()+
                " Exception e : "+e.getE()+" ExceptionData : "+e.getData());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e){
        String message = e.getMessage();
        if(message.startsWith("org.springframework.dao.DuplicateKeyException:")) {
            return ResultUtil.error(Post.CREATED, "数据重复: " + e);
        }
        return ResultUtil.error(UsuallyCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception e){
        System.out.println("异常 : " + e);
        return ResultUtil.error(UsuallyCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
