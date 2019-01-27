package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.sys.SysPermissionMapper;
import com.mc.block.pojo.sys.SysPermission;
import com.mc.block.sso.interfaces.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Override
    public SysPermission findById(Integer id) {
        return sysPermissionMapper.findById(id);
    }
}
