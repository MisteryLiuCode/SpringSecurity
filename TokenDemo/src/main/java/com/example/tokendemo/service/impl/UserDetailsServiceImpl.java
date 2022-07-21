package com.example.tokendemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;;
import com.example.tokendemo.domin.User;
import com.example.tokendemo.domin.UserDetailsImpl;
import com.example.tokendemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
//        这是使用mybatisPlus查询数据
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if((user==null)){
            throw new RuntimeException("用户名或者密码错误");
        }
//        将用户权限封装到进去
        List<String> authList = new ArrayList<>(Arrays.asList("test","admin"));

        //把数据封装成UserDetails返回
//        UserDetailsImpl重写了UserDetails里面的方法，注入了从数据库里面查的用户信息

        return new UserDetailsImpl(user,authList);
    }
}
