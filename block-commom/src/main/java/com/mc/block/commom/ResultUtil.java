package com.mc.block.commom;

import com.mc.block.confine.result.Result;
import com.mc.block.constant.UsuallyCode;

public class ResultUtil {

    public static Result success(int code, Object object){
        Result Result = new Result();
        Result.setCode(code);
        Result.setMsg("success");
        Result.setData(object);
        return Result;
    }

    /**
     * 请求成功返回
     * @param object
     * @return
     */
    public static Result success(Object object){
        return success(UsuallyCode.OK, object);
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(String resultResult){
        return error(UsuallyCode.BAD_REQUEST, resultResult);
    }

    public static Result error(Integer code,String resultResult){
        Result Result = new Result();
        Result.setCode(code);
        Result.setMsg(resultResult);
        return Result;
    }
}