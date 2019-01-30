package com.mc.block.security.sso.commom.configure;

import com.mc.block.pojo.bo.AuthorityBo;
import com.mc.block.security.sso.commom.WebSecurityConfig;
import com.mc.block.sso.interfaces.ISysUserService;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class MAccessDecisionManager implements AccessDecisionManager {

    //decide 方法是判定是否拥有权限的决策方法
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga instanceof AuthorityBo) {
                if(checkAuthorityBo(ga, request)){
                    return;
                }
            } else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {
                //获取未登录用户权限
                ISysUserService sysUserService = WebSecurityConfig.getSysUserService();
                List<GrantedAuthority> grantedAuthorities = sysUserService.roleAnonymousUser();
                for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                    if(checkAuthorityBo(grantedAuthority, request)){
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    private boolean checkAuthorityBo(GrantedAuthority grantedAuthority, HttpServletRequest request){
        boolean b = false;
        AuthorityBo urlGrantedAuthority = (AuthorityBo) grantedAuthority;
        String url = urlGrantedAuthority.getPermissionUrl();
        String method = urlGrantedAuthority.getMethod();
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
            if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}