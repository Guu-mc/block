package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.sys.SysRoleMapper;
import com.mc.block.pojo.sys.SysRole;
import com.mc.block.sso.interfaces.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public SysRole findById(Integer id) {
        return sysRoleMapper.findById(id);
    }

    @Override
    public SysRole findByName(String name) {
        return sysRoleMapper.findByName(name);
    }
}
