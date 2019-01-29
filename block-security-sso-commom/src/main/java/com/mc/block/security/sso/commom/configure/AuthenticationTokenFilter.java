package com.mc.block.security.sso.commom.configure;

import com.mc.block.commom.StringUtils;
import com.mc.block.pojo.bo.UserBo;
import com.mc.block.security.sso.commom.WebSecurityConfig;
import com.mc.block.sso.interfaces.ITokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String tokenHeader = "Authorization";
    private static final String tokenHead = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        //先从url中取token
        String authToken = request.getParameter("token");
        String authHeader = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(tokenHead)) {
            //如果header中存在token，则覆盖掉url中的token
            authToken = authHeader.substring(tokenHead.length()); // "Bearer "之后的内容
        }
        ITokenService tokenService = WebSecurityConfig.getTokenService();
        if (StringUtils.isNotBlank(authToken)) {
            UserBo user = tokenService.getUserFromToken(authToken);
            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                //检查token是否有效
                if (tokenService.validateToken(authToken, user)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    request.setAttribute("username", user.getUsername());
                    //设置用户登录状态
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}