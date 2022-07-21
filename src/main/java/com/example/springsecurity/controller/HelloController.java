package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/19 16:27
 **/

@RestController
public class HelloController {

//    测试springBoot工程
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
