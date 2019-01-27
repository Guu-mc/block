package com.mc.block.redis.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("dubbo-provider.xml")
public class BlockRedisServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(BlockRedisServiceApplication.class, args);
        Thread.currentThread().join();
    }

}

