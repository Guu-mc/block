package com.mc.block.exception;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ResultParamException extends RuntimeException{
    private String message;

    public ResultParamException(BindingResult errors){
        for (ObjectError objectError : errors.getAllErrors()) {
            Object[] arguments = objectError.getArguments();
            if(arguments != null && arguments.length > 0){
                addMessage(((DefaultMessageSourceResolvable)arguments[0]).getCode(), objectError.getDefaultMessage());
            }
        }
    }

    public void addMessage(String field, String s){
        if(StringUtils.isEmpty(message)){
            message = field + ": " + s;
        }else {
            message += ", " + field + ": " + s;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
