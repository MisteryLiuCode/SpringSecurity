package com.example.tokendemo.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName LoginReq
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/20 18:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginReq {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
