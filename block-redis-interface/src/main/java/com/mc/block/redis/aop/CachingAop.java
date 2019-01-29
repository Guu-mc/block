package com.mc.block.redis.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.commom.ClassUtils;
import com.mc.block.redis.annotation.CacheEvict;
import com.mc.block.redis.annotation.CachePut;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.block.redis.interfaces.IRedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Arrays;
import java.util.Set;

@Aspect
@Configuration
public class CachingAop {
    @Reference
    private IRedisService redisService;
    private SpelExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(cacheable)")
    public Object cacheable(ProceedingJoinPoint po, Cacheable cacheable) throws Throwable {
        Object obj;
        String key = getKey(cacheable.value(), po.toLongString(), cacheable.key(), po.getArgs());
        if(redisService.hasKey(key)){
            byte[] bytes = (byte[]) redisService.get(key);
            obj = ClassUtils.byteToObject(bytes);
        } else {
            obj = po.proceed();
            byte[] bytes = ClassUtils.objectToByte(obj);
            redisService.set(key, bytes, cacheable.timeout());
        }
        return obj;
    }

    @AfterReturning(value = "@annotation(cachePut)", returning="retVal")
    public Object cachePut(JoinPoint point, Object retVal, CachePut cachePut) {
        String key = getKey(cachePut.value(), point.toLongString(), cachePut.key(), point.getArgs());
        redisService.set(key, ClassUtils.objectToByte(retVal), cachePut.timeout());
        return retVal;
    }

    @Before("@annotation(cacheEvict)")
    public void cacheEvict(JoinPoint point, CacheEvict cacheEvict) {
        String frontKey = cacheEvict.value();
        if(frontKey.isEmpty()){
            frontKey = getFrontKey(point.toLongString());
        }
        if(cacheEvict.allEntries()){
            Set<String> keys = redisService.keys(frontKey + "::*");
            for (String key : keys) {
                redisService.del(key);
            }
        } else {
            String endKey;
            if(cacheEvict.key().isEmpty()){
                endKey = getEndKey(point.getArgs());
            } else {
                endKey = String.valueOf(parser.parseRaw(cacheEvict.key()).getValue().hashCode());
            }
            String key = getKey(frontKey, endKey);
            redisService.del(key);
        }
    }

    private String getEndKey(Object[] args){
        return String.valueOf(Arrays.hashCode(args));
    }

    private String getFrontKey(String longString){
        return String.valueOf(longString.hashCode());
    }

    private String getKey(String frontKey, String endKey){
        return frontKey+"::"+endKey;
    }

    private String getKey(String val, String longString, String key, Object[] args){
        String frontKey = val;
        String endKey;
        if(frontKey.isEmpty()){
            frontKey = getFrontKey(longString);
        }
        if(key.isEmpty()){
            endKey = getEndKey(args);
        } else {
            endKey = String.valueOf(parser.parseRaw(key).getValue().hashCode());
        }
        return getKey(frontKey, endKey);
    }
}