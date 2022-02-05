package com.wanghh.redis.demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2022-02-04 21:56
 **/
public class RedisLock {

    @Resource
    private Redisson redisson;

    @Resource
    private RedisTemplate redisTemplate;

    public void testRedisClock(){
        String redisLockKey = "redis_only_key";
        RLock lock = redisson.getLock(redisLockKey);
        try {
            // 1,加锁
            lock.lock();
            // 2,查询数据库商品库存
            // 3,库存不足,提示用户库存不足
            // 4,库存充足,扣减库存
            // 5,更新商品库存
            // 6,提示用户购买成功
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 7,释放锁
            lock.unlock();
        }
    }
}
