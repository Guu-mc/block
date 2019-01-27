package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRole;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "sysRole")
public interface SysRoleMapper {
    @MSelect
    @Cacheable
    SysRole findById(@Param("id")Integer id);
}
