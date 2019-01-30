package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.sys.SysPermissionRoleMapper;
import com.mc.block.pojo.sys.SysPermissionRole;
import com.mc.block.sso.interfaces.ISysPermissionRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SysPermissionRoleServiceImpl implements ISysPermissionRoleService {
    @Autowired
    private SysPermissionRoleMapper sysPermissionRoleMapper;

    @Override
    public List<SysPermissionRole> findBySysRoleId(Integer sysRoleId) {
        return sysPermissionRoleMapper.findBySysRoleId(sysRoleId);
    }
}
