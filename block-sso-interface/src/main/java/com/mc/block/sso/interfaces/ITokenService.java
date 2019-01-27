package com.mc.block.sso.interfaces;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITokenService {
    User getUserFromToken(String token);
    String generateToken(User userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
}
