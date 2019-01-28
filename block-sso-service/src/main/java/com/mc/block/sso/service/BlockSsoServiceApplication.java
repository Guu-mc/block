package com.mc.block.sso.service;

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
public class BlockSsoServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(BlockSsoServiceApplication.class, args);
        Thread.currentThread().join();
    }

}

