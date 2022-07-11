package com.digov.api.config.redis;

import com.digov.api.util.common.CommonUtil;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    /*** 锁前缀 **/
    private static final String LOCK_PREFIX = "redis_lock";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public boolean addLock(String key, int validTime) {
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            long expireAt = System.currentTimeMillis() + validTime + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());

            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());

                if (Objects.nonNull(value) && value.length > 0) {

                    long expireTime = Long.parseLong(new String(value));
                    // 如果锁已经过期
                    if (expireTime < System.currentTimeMillis()) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + validTime + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    public void delLock(String key) {
        String lock = LOCK_PREFIX + key;
        redisTemplate.delete(lock);
    }

    public void save(RedisPrefix redisPrefix, String key, String value) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisKey = redisPrefix.name().concat(":").concat(key);
        operations.set(redisKey, value);
    }

    public void save(RedisPrefix redisPrefix, String key, String value, Long time, TimeUnit timeUnit) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisKey = redisPrefix.name().concat(":").concat(key);
        operations.set(redisKey, value, time, timeUnit);
    }

    public String get(RedisPrefix redisPrefix, String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisKey = redisPrefix.name().concat(":").concat(key);
        return operations.get(redisKey);
    }

    public boolean hasKey(RedisPrefix redisPrefix, String key) {
        String redisKey = redisPrefix.name().concat(":").concat(key);
        Boolean hasKey = redisTemplate.hasKey(redisKey);
        if (CommonUtil.isEmpty(hasKey)) {
            return false;
        }
        return hasKey.booleanValue();
    }

    public void delete(RedisPrefix redisPrefix, String key) {
        String redisKey = redisPrefix.name().concat(":").concat(key);
        redisTemplate.delete(redisKey);
    }

    public void updateTime(RedisPrefix redisPrefix, String key, Long time, TimeUnit timeUnit) {
        String redisKey = redisPrefix.name().concat(":").concat(key);
        redisTemplate.expire(redisKey, time, timeUnit);
    }
}
