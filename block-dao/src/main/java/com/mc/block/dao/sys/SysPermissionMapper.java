package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysPermission;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionMapper {

    @MSelect
    @Cacheable
    SysPermission findById(@Param("id") Integer id);
}
