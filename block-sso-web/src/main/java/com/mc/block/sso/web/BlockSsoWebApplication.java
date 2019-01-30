package com.mc.block.sso.web;

import com.mc.block.security.sso.commom.annotation.WebSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("dubbo-consumer.xml")
@WebSecurity
public class BlockSsoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockSsoWebApplication.class, args);
    }

}

