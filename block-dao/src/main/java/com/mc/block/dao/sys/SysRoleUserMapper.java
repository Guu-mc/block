package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysRoleUser;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleUserMapper {
    @MSelect
    @Cacheable
    List<SysRoleUser> findBySysUserId(@Param("sysUserId") Integer sysUserId);
}
