package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysPermissionRole;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionRoleMapper {

    @MSelect
    @Cacheable
    SysPermissionRole findOne(SysPermissionRole sysPermissionRole);

    @MSelect
    @Cacheable
    List<SysPermissionRole> findBySysRoleId(@Param("sysRoleId") Integer sysRoleId);
}
