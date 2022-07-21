package com.example.tokendemo.filter;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.tokendemo.domin.User;
import com.example.tokendemo.utils.JwtUtil;
import com.example.tokendemo.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtAuthenticationTokenFilter
 * @Description
 * @Author 刘帅彪
 * @Date2022/7/21 15:59
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        获取token
        String token = request.getHeader("token");
//        如果没有token，那么直接放行，交给后面的过滤器
        if (StringUtils.isBlank(token)) {
            doFilter(request, response, filterChain);
            return;
        }
        String userid;
//        解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
//        从redis里面获取token信息
        String redisKey = "login" + userid;
        User user = redisCache.getCacheObject(redisKey);
        if (org.springframework.util.StringUtils.isEmpty(user)) {
            throw new RuntimeException("用户未登录");
        }
//        将数据放在SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null,null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        放行
        filterChain.doFilter(request, response);
    }
}
