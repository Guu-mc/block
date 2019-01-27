package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysPermissionRole;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "sysPermissionRole")
public interface SysPermissionRoleMapper {

    @MSelect
    @Cacheable
    SysPermissionRole findOne(SysPermissionRole sysPermissionRole);

    @MSelect
    @Cacheable
    List<SysPermissionRole> findBySysRoleId(@Param("sysRoleId") Integer sysRoleId);
}
