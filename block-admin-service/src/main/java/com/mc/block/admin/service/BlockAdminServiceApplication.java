package com.mc.block.admin.service;

import com.mc.block.redis.annotation.RedisCaching;
import com.mc.orange.mmsql.annotations.MMsql;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("dubbo-provider.xml")
@MapperScan("com.mc.block.dao")
@MMsql(showSql = true)
@RedisCaching
public class BlockAdminServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(BlockAdminServiceApplication.class, args);
        Thread.currentThread().join();
    }

}

