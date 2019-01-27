package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.sys.SysUserMapper;
import com.mc.block.pojo.sys.SysRole;
import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.block.pojo.sys.SysUser;
import com.mc.block.pojo.vo.SysUserVo;
import com.mc.block.sso.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysRoleUserService sysRoleUserService;
    @Autowired
    private ISysRoleService sysRoleService;

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
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}
