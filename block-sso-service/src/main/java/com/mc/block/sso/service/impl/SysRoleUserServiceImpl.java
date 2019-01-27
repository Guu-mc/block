package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.sys.SysRoleUserMapper;
import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.block.sso.interfaces.ISysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SysRoleUserServiceImpl implements ISysRoleUserService {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public List<SysRoleUser> findBySysUserId(Integer sysUserId){
        return sysRoleUserMapper.findBySysUserId(sysUserId);
    }
}
