package com.example.tokendemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tokendemo.domin.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
