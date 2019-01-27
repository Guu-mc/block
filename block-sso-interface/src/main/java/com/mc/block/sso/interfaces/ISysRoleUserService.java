package com.mc.block.sso.interfaces;

import com.mc.block.pojo.sys.SysRoleUser;

import java.util.List;

public interface ISysRoleUserService {
    List<SysRoleUser> findBySysUserId(Integer sysUserId);
}
