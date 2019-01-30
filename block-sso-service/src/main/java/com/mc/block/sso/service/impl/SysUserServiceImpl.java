package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.sys.SysUserMapper;
import com.mc.block.pojo.bo.AuthorityBo;
import com.mc.block.pojo.bo.UserBo;
import com.mc.block.pojo.sys.*;
import com.mc.block.pojo.vo.SysUserVo;
import com.mc.block.sso.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysRoleUserService sysRoleUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ISysPermissionRoleService sysPermissionRoleService;
    @Autowired
    private ISysPermissionService sysPermissionService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String userLogin(String username, String password) throws Exception {
        SysUser sysUser = sysUserMapper.findByUsername(username);
        if(sysUser == null || !bCryptPasswordEncoder.matches(password, sysUser.getPassword())){
            throw new Exception("用户不存在或密码有误");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<SysRoleUser> sysRoleUsers = sysRoleUserService.findBySysUserId(sysUser.getId());
        for (SysRoleUser sysRoleUser : sysRoleUsers) {
            SysRole sysRole = sysRoleService.findById(sysRoleUser.getSysRoleId());
            grantedAuthorities.addAll(userGrantedAuthoritys(sysRole));
        }
        return tokenService.generateToken(new UserBo(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities));
    }

    @Override
    public SysUser findByUsername(String s) {
        return sysUserMapper.findByUsername(s);
    }

    @Override
    public SysUser findOne(SysUser sysUser) {
        return sysUserMapper.findOne(sysUser);
    }

    @Override
    public SysUserVo userInfo(String username) {
        SysUserVo sysUserVo = new SysUserVo();
        SysUser sysUser = sysUserMapper.findByUsername(username);
        sysUserVo.setUserInfo(sysUser);
        List<SysRoleUser> sysRoleUsers = sysRoleUserService.findBySysUserId(sysUser.getId());
        sysRoleUsers.forEach(sysRoleUser -> {
            SysRole sysRole = sysRoleService.findById(sysRoleUser.getSysRoleId());
            sysUserVo.addRoles(sysRole.getName());
        });
        return sysUserVo;
    }

    @Override
    public List<GrantedAuthority> roleAnonymousUser() {
        SysRole sysRole = sysRoleService.findByName("ANONYMOUS");
        return userGrantedAuthoritys(sysRole);
    }

    private List<GrantedAuthority> userGrantedAuthoritys(SysRole sysRole){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(sysRole != null){
            List<SysPermissionRole> sysPermissionRoles = sysPermissionRoleService.findBySysRoleId(sysRole.getId());
            for (SysPermissionRole sysPermissionRole : sysPermissionRoles) {
                SysPermission sysPermission = sysPermissionService.findById(sysPermissionRole.getSysPermissionId());
                grantedAuthorities.add(new AuthorityBo(sysRole.getName(), sysPermission.getUrl(), sysPermission.getMethod()));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}
