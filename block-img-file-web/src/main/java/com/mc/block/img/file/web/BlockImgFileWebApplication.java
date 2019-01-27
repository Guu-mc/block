package com.mc.block.img.file.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("dubbo-consumer.xml")
public class BlockImgFileWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockImgFileWebApplication.class, args);
    }
}
