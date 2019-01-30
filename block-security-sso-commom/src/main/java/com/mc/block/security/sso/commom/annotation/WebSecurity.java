package com.mc.block.security.sso.commom.annotation;

import com.mc.block.security.sso.commom.WebSecurityConfig;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({WebSecurityConfig.class})
public @interface WebSecurity {

}
