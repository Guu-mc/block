//package com.mc.block.web.controller;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.mc.block.commom.ResultUtil;
//import com.mc.block.confine.result.Result;
//import com.mc.block.interfaces.IPlayService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("play")
//public class PlayController {
//
//    @Reference
//    private IPlayService playService;
//
////    @GetMapping("recommend")
////    public Result recommend(@Valid PlayInfo playInfo, BindingResult errors){
////        BindingResultUtils.binding(errors);
////        return ResultUtil.success(playService.recommend(playInfo));
////    }
//
//    @GetMapping("recommend")
//    public Result recommend(String unionId, int size){
//        if(size < 1){
//            throw new RuntimeException("size Not less than 1");
//        }
//        return ResultUtil.success(playService.recommend(null, unionId, size));
//    }
//
//    @GetMapping("test")
//    public String test(){
//        return "test";
//    }
//
//}
