package com.mc.block.security.sso.commom;

import com.mc.block.security.sso.commom.configure.AuthenticationTokenFilter;
import com.mc.block.security.sso.commom.configure.MFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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

@EnableWebSecurity
@Import({AuthenticationTokenFilter.class, MFilterSecurityInterceptor.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MFilterSecurityInterceptor filterSecurityInterceptor;
    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;
    private static final String loginPage = "http://localhost:8080/login";

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
