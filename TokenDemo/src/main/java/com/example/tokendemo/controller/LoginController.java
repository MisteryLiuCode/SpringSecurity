package com.example.tokendemo.controller;

import com.example.tokendemo.domin.LoginReq;
import com.example.tokendemo.domin.ResponseResult;
import com.example.tokendemo.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName LoginController
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/20 18:30
 **/
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody LoginReq req){
//        登录
        return  loginService.login(req);
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
//        登录
        return  loginService.logout();
    }





}
