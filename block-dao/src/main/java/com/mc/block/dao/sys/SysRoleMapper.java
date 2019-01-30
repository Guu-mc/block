package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRole;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    @MSelect
    @Cacheable
    SysRole findById(@Param("id") Integer id);
    @MSelect
    @Cacheable
    SysRole findByName(@Param("name") String name);
}
