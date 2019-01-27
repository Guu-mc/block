package com.mc.block.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.redis.interfaces.IRedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @Reference
    private IRedisService redisService;

    @GetMapping("ff")
    public void ff(){
        redisService.set("gg",1);
    }
}
