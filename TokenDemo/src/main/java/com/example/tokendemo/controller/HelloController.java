package com.example.tokendemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/19 18:05
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String testHello(){
        return "hello";
    }

}
