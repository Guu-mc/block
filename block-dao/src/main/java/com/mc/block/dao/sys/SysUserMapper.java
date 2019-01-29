package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.block.pojo.sys.SysUser;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    @MSelect
    @Cacheable
    SysRoleUser findBySysUserId(@Param("sysUserId") Integer sysUserId);
    @MSelect
    @Cacheable
    SysUser findByUsername(@Param("username") String username);
    @MSelect
    @Cacheable
    SysUser findOne(SysUser sysUser);
    @MSelect
    @Cacheable
    List<SysUser> findAll();
}
