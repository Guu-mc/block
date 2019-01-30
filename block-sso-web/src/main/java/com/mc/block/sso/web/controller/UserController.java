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

    @PostMapping("logout")
    public Result logout(@RequestAttribute("username") String username) {
        sysUserService.logout(username);
        return ResultUtil.success();
    }

    @GetMapping("info")
    public Result info(@RequestAttribute("username") String username){
        return ResultUtil.success(sysUserService.userInfo(username));
    }

    @GetMapping("messageCount")
    public Result messageCount(@RequestAttribute("username") String username){
        return ResultUtil.success(sysUserService.messageCount(username));
    }

    @GetMapping("messageInit")
    public Result messageInit(@RequestAttribute("username") String username){
        return ResultUtil.success(sysUserService.messageInit(username));
    }

    @GetMapping("messageContent")
    public Result messageContent(@RequestAttribute("username") String username, Integer id){
        if(id == null){
            throw new ResultParamException("id不能为空");
        }
        return ResultUtil.success(sysUserService.messageContent(username, id));
    }

    @PostMapping("messageRemoveRead")
    public Result messageRemoveRead(@RequestAttribute("username") String username, Integer id) throws Exception {
        if(id == null){
            throw new ResultParamException("id不能为空");
        }
        sysUserService.messageRemoveRead(username, id);
        return ResultUtil.success();
    }

    @PostMapping("messageRestore")
    public Result messageRestore(@RequestAttribute("username") String username, Integer id) throws Exception {
        if(id == null){
            throw new ResultParamException("id不能为空");
        }
        sysUserService.messageRestore(username, id);
        return ResultUtil.success();
    }


}
