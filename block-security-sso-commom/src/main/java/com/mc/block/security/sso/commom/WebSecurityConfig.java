package com.mc.block.security.sso.commom;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.security.sso.commom.configure.AuthenticationTokenFilter;
import com.mc.block.security.sso.commom.configure.MFilterSecurityInterceptor;
import com.mc.block.sso.interfaces.ISysUserService;
import com.mc.block.sso.interfaces.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.annotation.PostConstruct;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private MFilterSecurityInterceptor filterSecurityInterceptor = new MFilterSecurityInterceptor();
    private AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    private static final String loginPage = "http://localhost:8080/login";

    @Reference
    private ITokenService tokenService;
    private static ITokenService tokenService1;
    @Reference
    private ISysUserService sysUserService;
    private static ISysUserService sysUserService1;

    @PostConstruct
    private void init(){
        tokenService1 = tokenService;
        sysUserService1 = sysUserService;
    }

    public static ITokenService getTokenService() {
        return tokenService1;
    }

    public static ISysUserService getSysUserService() {
        return sysUserService1;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig());
        http
                .cors().configurationSource(configSource)
                .and()
                .formLogin()
                .loginPage(loginPage)
                .and()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .headers()
                .cacheControl();
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

    /**
     * 跨域设置
     *
     * @return
     */
    public CorsConfiguration corsConfig() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.setMaxAge(18000L);
        corsConfig.setAllowCredentials(true);
        return corsConfig;
    }
}
