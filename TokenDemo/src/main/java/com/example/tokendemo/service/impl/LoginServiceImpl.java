package com.example.tokendemo.service.impl;

import com.example.tokendemo.domin.LoginReq;
import com.example.tokendemo.domin.ResponseResult;
import com.example.tokendemo.domin.User;
import com.example.tokendemo.domin.UserDetailsImpl;
import com.example.tokendemo.service.LoginService;
import com.example.tokendemo.utils.JwtUtil;
import com.example.tokendemo.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/20 18:35
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(LoginReq req) {
//        登录认证
//        这个认证再之前流程图里面已有
//        传入账号，密码
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(req.getUserName(),req.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //        认证未通过，给出相应的提示
        if (authenticate==null){
            throw new RuntimeException("登录失败");
        }
//        获取登录信息
        UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
        User user = principal.getUser();
//        使用userid创建jwt
        String id = user.getId().toString();
        String token = JwtUtil.createJWT(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
//        把登录信息存入redis里
        redisCache.setCacheObject("login"+id,user);

        return new ResponseResult(200,"登录成功",token);
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authenticationToken.getPrincipal();
//        从redis里面删除这个用户
        redisCache.deleteObject("login"+user.getId());
        return new ResponseResult(200,"登出成功");
    }
}
