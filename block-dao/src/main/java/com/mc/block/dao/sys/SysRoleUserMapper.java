package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "sysRoleUser")
public interface SysRoleUserMapper {
    @MSelect
    @Cacheable
    List<SysRoleUser> findBySysUserId(@Param("sysUserId") Integer sysUserId);
}
