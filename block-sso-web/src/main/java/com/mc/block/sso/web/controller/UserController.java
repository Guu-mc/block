package com.mc.block.sso.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.commom.ResultUtil;
import com.mc.block.commom.StringUtils;
import com.mc.block.confine.result.Result;
import com.mc.block.exception.ResultParamException;
import com.mc.block.sso.interfaces.ISysUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Reference
    private ISysUserService sysUserService;

    @PostMapping("login")
    public Result login(String username, String password) throws Exception {
        if(StringUtils.isEmptyAndTrim(username)){
            throw new ResultParamException("账号不能为空");
        }
        if(StringUtils.isEmptyAndTrim(password)){
            throw new ResultParamException("密码不能为空");
        }
        return ResultUtil.success(sysUserService.userLogin(username, password));
    }

    @GetMapping("info")
    public Result info(@RequestAttribute("username") String username){
        return ResultUtil.success(sysUserService.userInfo(username));
    }
}
