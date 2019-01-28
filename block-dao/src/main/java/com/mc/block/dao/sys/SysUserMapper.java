package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.block.pojo.sys.SysUser;
import com.mc.block.redis.annotation.CachePut;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    @MSelect
    SysRoleUser findBySysUserId(@Param("sysUserId") Integer sysUserId);
    @MSelect
    @CachePut
    SysUser findByUsername(@Param("username") String username);
    @MSelect
    SysUser findOne(SysUser sysUser);
    @MSelect
    List<SysUser> findAll();
}
