package com.cc.elm.common.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * Created by chenchang on 2018/1/23.
 */
public class RedisUtil {
    public static void hmset(String key, Map<String, String> map) {
        Jedis jedis = RedisPool.getResource();
        jedis.hmset(key, map);
        RedisPool.returnResource(jedis);
    }

    public static void expire(String key, Integer exp) {
        Jedis jedis = RedisPool.getResource();
        jedis.expire(key, exp);
        RedisPool.returnResource(jedis);
    }

    public static Map<String, String> hgetAll(String key) {
        Jedis jedis = RedisPool.getResource();
        Map<String, String> map = jedis.hgetAll(key);
        RedisPool.returnResource(jedis);
        return map;
    }

    public static Long llen(String key) {
        Jedis jedis = RedisPool.getResource();
        Long llen = jedis.llen(key);
        RedisPool.returnResource(jedis);
        return llen;
    }

    public static List<String> lrange(String key, long start, long stop) {
        Jedis jedis = RedisPool.getResource();
        List<String> lrange = jedis.lrange(key, start, stop);
        RedisPool.returnResource(jedis);
        return lrange;
    }

    public static Long rpush(String key, String... value) {
        Jedis jedis = RedisPool.getResource();
        Long rpush = jedis.rpush(key, value);
        RedisPool.returnResource(jedis);
        return rpush;
    }

    public static String lindex(String key, long index) {
        Jedis jedis = RedisPool.getResource();
        String beanKey = jedis.lindex(key, index);
        RedisPool.returnResource(jedis);
        return beanKey;
    }

}
