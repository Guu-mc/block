package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.block.pojo.sys.SysUser;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "sysUser")
public interface SysUserMapper {
    @MSelect
    @Cacheable
    SysRoleUser findBySysUserId(@Param("sysUserId") Integer sysUserId);
    @MSelect
    SysUser findByUsername(@Param("username") String username);
    @MSelect
    @Cacheable
    SysUser findOne(SysUser sysUser);
    @MSelect
    @Cacheable
    List<SysUser> findAll();
}
