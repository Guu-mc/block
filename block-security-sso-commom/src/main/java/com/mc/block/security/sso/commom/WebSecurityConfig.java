package com.mc.block.security.sso.commom;

import com.mc.block.security.sso.commom.configure.AuthenticationTokenFilter;
import com.mc.block.security.sso.commom.configure.MFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
