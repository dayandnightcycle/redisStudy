package com.lan;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        //配置连接池;
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大连接数
        poolConfig.setMaxTotal(8);
        //最大空闲连接
        poolConfig.setMaxIdle(8);
        //最小空闲连接
        poolConfig.setMinIdle(0);
        //等待时常
        poolConfig.setMaxWaitMillis(100000);
        jedisPool = new JedisPool(poolConfig, "192.168.10.100", 6379, 1000, "123321");

    }

    public static Jedis getJedis() {
        Jedis resource = jedisPool.getResource();
        return resource;
    }

}
