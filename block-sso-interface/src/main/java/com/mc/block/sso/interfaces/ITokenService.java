package com.mc.block.sso.interfaces;

import com.mc.block.pojo.bo.UserBo;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITokenService {
    UserBo getUserFromToken(String token);
    String generateToken(UserBo userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
}
