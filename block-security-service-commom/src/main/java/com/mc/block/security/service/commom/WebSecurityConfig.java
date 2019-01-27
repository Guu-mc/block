package com.mc.block.security.service.commom;

import com.mc.block.security.service.commom.handler.RestAuthenticationFailureHandler;
import com.mc.block.security.service.commom.handler.RestAuthenticationSuccessHandler;
import com.mc.block.security.service.commom.service.AuthenticationTokenFilter;
import com.mc.block.security.service.commom.service.MFilterSecurityInterceptor;
import com.mc.block.security.service.commom.service.MUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private MUserDetailsService userDetailsService = new MUserDetailsService();
    private MFilterSecurityInterceptor filterSecurityInterceptor = new MFilterSecurityInterceptor();
    private AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler = new RestAuthenticationSuccessHandler();
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler = new RestAuthenticationFailureHandler();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login")
                .loginPage("http://localhost:8080/login")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .and()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf()
                .disable()
                .headers()
                .cacheControl();

        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
