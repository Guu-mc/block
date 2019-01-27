//package com.mc.block.web.controller;
//
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.mc.block.commom.ResultUtil;
//import com.mc.block.confine.result.Result;
//import com.mc.block.interfaces.IProductTypeService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("productType")
//public class ProductTypeController {
//    @Reference
//    private IProductTypeService productTypeService;
//
//    @GetMapping("findAll")
//    public Result findAll(){
//        return ResultUtil.success(productTypeService.findAll());
//    }
//}
