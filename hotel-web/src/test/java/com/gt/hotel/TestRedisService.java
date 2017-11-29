package com.gt.hotel;

import com.gt.hotel.util.RedisCacheUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/28
 * @since
 */
public class TestRedisService extends BasicTest {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Test
    public void redisTest(){
        String key = "1112222";
        if(redisCacheUtil.get(key)==null) {
            System.out.println("redis 第一次");
            redisCacheUtil.set(key, "测试",60L);
        }else{
            System.out.println("redis ：");
            Object o = redisCacheUtil.get(key);
            System.out.println(o.toString());
        }
    }

}
