package com.mc.block.file.service;

import com.mc.block.redis.annotation.ReidsCaching;
import com.mc.orange.mmsql.annotations.MMsql;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("dubbo-provider.xml")
@MapperScan("com.mc.block.dao")
@MMsql(showSql = true)
@ReidsCaching
public class BlockFileServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(BlockFileServiceApplication.class, args);
        Thread.currentThread().join();
    }
}
