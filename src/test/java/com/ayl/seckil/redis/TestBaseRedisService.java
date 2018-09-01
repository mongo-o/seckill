package com.ayl.seckil.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AYL    2018/8/25 1:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBaseRedisService {
    @Autowired
    private BaseRedisService redisService;

    @Test
    public void testGet() {
        redisService.set(RedisUserKey.USER_TOKEN,"key1","value1");
        redisService.set(RedisUserKey.TEST_TOKEN,"key1","value1");
        System.out.println(redisService.get(RedisUserKey.USER_TOKEN,"key1", String.class));
    }

    @Test
    public void testRedisFailover() {
        int i = 0;
        for (;;) {
            try {
                String result = (String) redisService.get(RedisUserKey.USER_TOKEN, "key1", String.class);
                if (i % 10 == 0) {
                    System.out.println("==============" + result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
