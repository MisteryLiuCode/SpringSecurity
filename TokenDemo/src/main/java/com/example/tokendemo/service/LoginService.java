package com.example.tokendemo.service;

import com.example.tokendemo.domin.LoginReq;
import com.example.tokendemo.domin.ResponseResult;

public interface LoginService {

    ResponseResult login(LoginReq req);

    ResponseResult logout();
}
