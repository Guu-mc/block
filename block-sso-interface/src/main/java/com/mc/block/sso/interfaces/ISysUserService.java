package com.mc.block.sso.interfaces;

import com.mc.block.pojo.sys.SysUser;
import com.mc.block.pojo.vo.SysUserMessageVo;
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

    void logout(String username);

    int messageCount(String username);

    SysUserMessageVo messageInit(String username);

    String messageContent(String username, Integer id);

    void messageRemoveRead(String username, Integer id) throws Exception;

    void messageRestore(String username, Integer id) throws Exception;
}
