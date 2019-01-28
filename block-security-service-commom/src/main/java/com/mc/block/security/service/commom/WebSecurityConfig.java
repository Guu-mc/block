package com.mc.block.security.service.commom;

import com.mc.block.security.service.commom.service.AuthenticationTokenFilter;
import com.mc.block.security.service.commom.service.MFilterSecurityInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private MFilterSecurityInterceptor filterSecurityInterceptor = new MFilterSecurityInterceptor();
    private AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("http://localhost:8080/login")
                .and()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf()
                .and()
                .headers()
                .cacheControl();

        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
