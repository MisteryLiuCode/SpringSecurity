package com.example.tokendemo.mapper;

import com.example.tokendemo.domin.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName MapperTest
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/20 15:46
 **/
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

//    测试mapper扫描
    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
