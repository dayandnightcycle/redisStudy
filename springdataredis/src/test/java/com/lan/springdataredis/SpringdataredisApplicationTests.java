package com.lan.springdataredis;

import com.lan.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringdataredisApplicationTests {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name", "兰");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name " + name);
    }

    @Test
    void testObject() {
        redisTemplate.opsForValue().set("user:5", new User("东哥", 18));
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name " + name);
    }

}
