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
        redisTemplate.opsForValue().set("name", "兰2");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name " + name);
    }

    /**
     *  redisTemplate 的序列化实践方案一
     *  1.自定义redisTemplate
     *  2.修改redisTemplate的序列化器为GenericJackson2JsonRedisSerializer
     *
     *  缺点：
     *      {
     *   "@class": "com.lan.pojo.User",
     *   "name": "东哥",
     *   "age": 18
     * }
     *  存储中class类型占用较多空间
     */
    @Test
    void testObject() {
        redisTemplate.opsForValue().set("user:5", new User("东哥", 18));
        Object name = redisTemplate.opsForValue().get("user:5");
        System.out.println("user:5 " + name);
    }

}
