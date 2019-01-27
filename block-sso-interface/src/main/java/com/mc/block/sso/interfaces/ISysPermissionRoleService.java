package com.mc.block.sso.interfaces;

import com.mc.block.pojo.sys.SysPermissionRole;

import java.util.List;

public interface ISysPermissionRoleService {
    List<SysPermissionRole> findBySysRoleId(Integer sysRoleId);
}
