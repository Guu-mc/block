package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.redis.interfaces.IRedisService;
import com.mc.block.sso.interfaces.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
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
        Claims claims = getClaimsFromToken(token);
        String username = (String) claims.get(CLAIM_KEY_USERNAME);
        String realToken = (String) redisService.get(CLAIM_KEY_USERNAME+"::"+username);
        if(token.equals(realToken) && (claims = getClaimsFromToken(realToken))!=null){
            return new User(username, null, claims.get(CLAIM_KEY_ROLES, Collection.class));
        }
        return null;
    }

    @Override
    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        String token = generateToken(claims);
        redisService.set(CLAIM_KEY_USERNAME+"::"+userDetails.getUsername(), token, EXPIRATION);
        return token;
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        User fromToken = getUserFromToken(token);
        return fromToken!=null && fromToken.getUsername().equals(user.getUsername());
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
