package com.mc.block.service;

import com.mc.orange.mmsql.annotations.MMsql;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableCaching
@ImportResource("dubbo-provider.xml")
@MapperScan("com.mc.block.dao")
@MMsql(showSql = true)
public class BlockPlaySimsServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(BlockPlaySimsServiceApplication.class, args);
        Thread.currentThread().join();
    }
}
