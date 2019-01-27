package com.mc.block.sso.service.impl;

import com.mc.block.redis.interfaces.IRedisService;
import com.mc.block.sso.interfaces.ITokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenServiceImpl implements ITokenService {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String SECRET = "iemimcujyejwppfjjlayhf";
    private static final int EXPIRATION = 10000; //过期时长，单位为秒,可以通过配置写入。

    @Reference
    private IRedisService redisService;

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    @Override
    public User getUserFromToken(String token) {
        return (User) redisService.get(token);
    }

    @Override
    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        String token = generateToken(claims);
        redisService.set(token, userDetails, EXPIRATION);
        return token;
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        User fromToken = getUserFromToken(token);
        return fromToken!=null && fromToken.getUsername().equals(user.getUsername());
    }
}
