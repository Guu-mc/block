package com.mc.block.sso.interfaces;

import com.mc.block.pojo.sys.SysUser;
import com.mc.block.pojo.vo.SysUserVo;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface ISysUserService {

    String userLogin(String username, String password) throws Exception;

    SysUser findByUsername(String s);

    SysUser findOne(SysUser sysUser);

    SysUserVo userInfo(String username);

    List<GrantedAuthority> roleAnonymousUser();

    List<SysUser> findAll();
}
