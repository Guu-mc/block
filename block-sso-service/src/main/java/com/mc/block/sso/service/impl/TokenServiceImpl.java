package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.commom.ClassUtils;
import com.mc.block.pojo.bo.UserBo;
import com.mc.block.redis.interfaces.IRedisService;
import com.mc.block.sso.interfaces.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

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
    public UserBo getUserFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims!=null){
            String username = (String) claims.get(CLAIM_KEY_USERNAME);
            Map<String, Object> map = (Map<String, Object>) ClassUtils.byteToObject((byte[]) redisService.get(CLAIM_KEY_USERNAME + "::" + username));
            if(token.equals(map.get("token"))){
                return (UserBo) map.get("user");
            }
        }
        return null;
    }

    @Override
    public boolean removeToken(String username) {
        boolean b = false;
        if(username != null){
            try {
                redisService.del(CLAIM_KEY_USERNAME+"::"+username);
                b = true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public String generateToken(UserBo userDetails) {
        Map<String, Object> map = new HashMap<>();
        map.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        map.put(CLAIM_KEY_CREATED, new Date());
        map.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        String token = generateToken(map);
        map.clear();
        map.put("token", token);
        map.put("user", userDetails);
        redisService.set(CLAIM_KEY_USERNAME+"::"+userDetails.getUsername(), ClassUtils.objectToByte(map), EXPIRATION);
        return token;
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        UserBo fromToken = getUserFromToken(token);
        return fromToken!=null && fromToken.getUsername().equals(userDetails.getUsername());
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
