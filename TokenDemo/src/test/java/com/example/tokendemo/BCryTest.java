package com.example.tokendemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @ClassName BCryTest
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/20 17:09
 **/
@Slf4j
@SpringBootTest
public class BCryTest {
    @Resource
    private BCryptPasswordEncoder encoder;

    @Test
    public void testBycy(){
        log.info("开始加密");
        System.out.println(encoder.encode("1234"));
        log.info("开始解密");
        System.out.println(encoder.matches("1234", "$2a$10$1MoJ7r2vuLtnBydf0I/Sy.OsEYNixEUBfMn7jUwWbK5BvjW8JC/ye"));
    }


}
