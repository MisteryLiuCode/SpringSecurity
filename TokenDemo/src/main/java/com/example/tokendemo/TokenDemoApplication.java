package com.example.tokendemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.tokendemo.mapper")
public class TokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenDemoApplication.class, args);
    }

}
