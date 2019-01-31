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
        String key = getKey(cacheable.value(), cacheable.keyGroup(), cacheable.key(), po.toLongString(), po.getArgs());
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
        String key = getKey(cachePut.value(), cachePut.keyGroup(), cachePut.key(), point.toLongString(), point.getArgs());
        redisService.set(key, ClassUtils.objectToByte(retVal), cachePut.timeout());
        return retVal;
    }

    @Before("@annotation(cacheEvict)")
    public void cacheEvict(JoinPoint point, CacheEvict cacheEvict) {
        String longString = clean(point.toLongString());
        int t = longString.lastIndexOf("(");
        int i = longString.lastIndexOf(".", t);
        String classname = cacheEvict.value();
        if(classname.isEmpty()){
            classname = longString.substring(0, i);
        }
        if(cacheEvict.allEntries()){
            String key_ = classname.hashCode()+":*";
            Set<String> keys = redisService.keys(key_);
            for (String key : keys) {
                redisService.del(key);
            }
        } else {
            String method = cacheEvict.keyGroup();
            if(method.isEmpty()){
                method = longString.substring(i+1);
            }
            String _key = cacheEvict.key();
            if(_key.isEmpty()){
                _key = Arrays.toString(point.getArgs());
            }
            String key = syntheticKey(classname, method, _key);
            redisService.del(key);
        }
    }

    private String getKey(String classname, String method, String _key, String longString, Object[] obj){
        longString = clean(longString);
        int t = longString.lastIndexOf("(");
        int i = longString.lastIndexOf(".", t);
        if(classname.isEmpty()){
            classname = longString.substring(0, i);
        }
        if(method.isEmpty()){
            method = longString.substring(i+1);
        }
        if(_key.isEmpty()){
            _key = Arrays.toString(obj);
        } else {
            _key = String.valueOf(parser.parseRaw(_key).getValue());
        }
        return syntheticKey(classname, method, _key);
    }

    private String syntheticKey(String classname, String method, String _key){
        return classname.hashCode()+":"+method.hashCode()+":"+_key.hashCode();
    }

    private String clean(String str){
        String[] split = str.split(" ");
        str = split[split.length-1];
        return str.substring(0, str.length()-1);
    }
}