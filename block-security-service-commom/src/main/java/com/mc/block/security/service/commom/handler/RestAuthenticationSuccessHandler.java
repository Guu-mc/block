package com.mc.block.security.service.commom.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.sso.interfaces.ITokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Reference
    private ITokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        User userDetails = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(userDetails);
        httpServletResponse.getWriter().write("{\"code\": 200, \"msg\": \"Login success\", \"data\": \""+ token +"\"}");
    }
}
