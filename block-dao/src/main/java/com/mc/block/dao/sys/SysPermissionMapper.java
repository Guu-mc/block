package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysPermission;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "sysPermission")
public interface SysPermissionMapper {

    @MSelect
    @Cacheable
    SysPermission findById(@Param("id") Integer id);
}
