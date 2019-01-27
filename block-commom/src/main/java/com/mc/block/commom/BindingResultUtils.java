package com.mc.block.commom;

import com.mc.block.exception.ResultParamException;
import org.springframework.validation.BindingResult;

public class BindingResultUtils {

    public static void binding(BindingResult errors){
        if(errors != null && errors.getErrorCount() != 0){
            throw new ResultParamException(errors);
        }
    }
}
