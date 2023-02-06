package com.hmdp.config;

import com.hmdp.utils.LoginIntercepotor;
import com.hmdp.utils.RefreshTokenIntercepotor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        registry.addInterceptor(new LoginIntercepotor()).excludePathPatterns(
                "/user/code",
                "/user/login",
                "/blog/hot",
                "/shop/**",
                "/voucher/**",
                "/shop-type/**",
                "/upload/**"
        ).order(1);
        //刷新拦截器
        registry.addInterceptor(new RefreshTokenIntercepotor(stringRedisTemplate)).addPathPatterns("/**").order(0);//默认就是/**
    }
}
