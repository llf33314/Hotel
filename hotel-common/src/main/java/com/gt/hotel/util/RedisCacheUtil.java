package com.gt.hotel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存工具类
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/08/02
 * @since 1.0.0
 */
@Component
public class RedisCacheUtil {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys 数组Key
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key key
     * @return boolean
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key key
     * @return Object
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 写入缓存
     * 默认缓存：7200秒 2小时
     *
     * @param key   key
     * @param value value
     * @return boolean
     */
    public boolean set(final String key, Object value) {
        return set(key, value, 7200L);
    }

    /**
     * 写入缓存
     *
     * @param key        key
     * @param value      value
     * @param expireTime 过期时间
     * @return boolean
     */
    public boolean set(final String key, Object value, Long expireTime) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }
}
