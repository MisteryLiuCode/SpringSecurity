package com.example.tokendemo.domin;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName UserDetailsImpl
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/20 16:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetailsImpl implements UserDetails {

//    这里面是获取用户信息
    private User user;
//    写入权限信息
    public List<String> permissions;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetailsImpl(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorityList;
//    获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        权限信息写入
        if (authorityList!=null){
            return authorityList;
        }
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorityList.add(authority);
        }
        return authorityList;
    }

//    获取用户密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }

//    获取用户账号
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
