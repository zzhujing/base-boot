package zz.hujing.baseboot.core.utils;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import zz.hujing.baseboot.core.exception.InternalServiceException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static zz.hujing.baseboot.core.result.CommonEnum.REDIS_NULL_RETURN;

/**
 * RedisCacheUtil
 **/
@Component
public class RedisCacheUtil {

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisCacheUtil(ObjectProvider<RedisTemplate<Object, Object>> redisTemplate) {
        this.redisTemplate = redisTemplate.getIfAvailable(RedisTemplate::new);
    }

    public Object getValue(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValue(Object key, Object value, TimeUnit timeUnit, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public void removeValue(Object key) {
        redisTemplate.delete(key);
    }


    public void removeMultiValue(Object... keys) {
        redisTemplate.delete(keys);
    }

    public Object getForHash(Object key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public void setForHash(Object key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, 30, TimeUnit.DAYS);
    }

    public void removeMultiHash(Object key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public void removeHash(Object key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public boolean hasKey(Object key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElseThrow(() -> new InternalServiceException(REDIS_NULL_RETURN));
    }

    public boolean setIfAbsent(Object key, Object value, long timeout, TimeUnit timeUnit) {
        return Optional.ofNullable(redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit))
                .orElseThrow(() -> new InternalServiceException(REDIS_NULL_RETURN));
    }

}
