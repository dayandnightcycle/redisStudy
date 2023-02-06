package com.lan.springdataredis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lan.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class SpringdataStringRedisApplicationTests {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "宁");
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name " + name);
    }

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testObject() throws JsonProcessingException {
        User user = new User("东哥", 19);
        //手动序列化
        String json = objectMapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:6", json);
        String jsonUser = stringRedisTemplate.opsForValue().get("user:6");
        //手动反序列化
        User user1 = objectMapper.readValue(jsonUser, User.class);
        System.out.println("user1 " + user1);
    }

    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("user:7", "name", "爆米花");
        stringRedisTemplate.opsForHash().put("user:7", "age", "10");
        String name = "name";
        Object o = stringRedisTemplate.opsForHash().get("user:7", name);
        System.out.println("o" + o);

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:7");
        System.out.println("entries " + entries);


    }

}
