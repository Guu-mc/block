package com.mc.block.security.service.commom.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.commom.StringUtils;
import com.mc.block.pojo.bo.AuthorityBo;
import com.mc.block.pojo.sys.*;
import com.mc.block.sso.interfaces.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MUserDetailsService implements UserDetailsService {
    @Reference
    private ISysUserService sysUserService;
    @Reference
    private ISysRoleUserService sysRoleUserService;
    @Reference
    private ISysRoleService sysRoleService;
    @Reference
    private ISysPermissionRoleService sysPermissionRoleService;
    @Reference
    private ISysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(s)){
            throw  new UsernameNotFoundException("用户不能为空");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SysUser sysUser = sysUserService.findByUsername(s);
        if(sysUser == null){
            throw  new UsernameNotFoundException("用户不存在或密码有误");
        }
        List<SysRoleUser> sysRoleUsers = sysRoleUserService.findBySysUserId(sysUser.getId());
        for (SysRoleUser sysRoleUser : sysRoleUsers) {
            SysRole sysRole = sysRoleService.findById(sysRoleUser.getSysRoleId());
            List<SysPermissionRole> sysPermissionRoles = sysPermissionRoleService.findBySysRoleId(sysRole.getId());
            for (SysPermissionRole sysPermissionRole : sysPermissionRoles) {
                SysPermission sysPermission = sysPermissionService.findById(sysPermissionRole.getSysPermissionId());
                grantedAuthorities.add(new AuthorityBo(sysRole.getName(), sysPermission.getUrl(), sysPermission.getMethod()));
            }
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
    }
}
