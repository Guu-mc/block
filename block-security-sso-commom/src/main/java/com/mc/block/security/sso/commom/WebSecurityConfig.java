package com.mc.block.security.sso.commom;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.security.sso.commom.configure.AuthenticationTokenFilter;
import com.mc.block.security.sso.commom.configure.MFilterSecurityInterceptor;
import com.mc.block.sso.interfaces.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private MFilterSecurityInterceptor filterSecurityInterceptor = new MFilterSecurityInterceptor();
    private AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();

    @Reference
    private ITokenService tokenService;
    private static ITokenService tokenService1;

    @PostConstruct
    private void init(){
        tokenService1 = tokenService;
    }

    public static ITokenService getTokenService() {
        return tokenService1;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .formLogin()
                .loginPage("http://localhost:8080/login")
                .and()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf().disable()
                .headers()
                .cacheControl();

        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                        return null;
                    }
                });
    }
}
