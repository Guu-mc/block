package com.mc.block.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("dubbo-consumer.xml")
public class BlockWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockWebApplication.class, args);
    }
}
