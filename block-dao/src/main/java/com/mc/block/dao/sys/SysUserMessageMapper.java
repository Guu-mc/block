package com.mc.block.dao.sys;

import com.mc.block.pojo.sys.SysUserMessage;
import com.mc.block.redis.annotation.CacheEvict;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import com.mc.orange.mmsql.annotations.MUpdate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMessageMapper {
    @MSelect
    @Cacheable
    List<SysUserMessage> findByUserIdAndStatus(@Param("userId")Integer userId, @Param("status")Integer status);
    @MSelect(pojo = SysUserMessage.class)
    @Cacheable
    int findCountByUserIdAndStatus(@Param("userId")Integer userId, @Param("status")Integer status);
    @MSelect
    @Cacheable
    SysUserMessage findByIdAndUserId(@Param("id")Integer id, @Param("userId")Integer userId);
    @MUpdate
    @CacheEvict(allEntries = true)
    int update(SysUserMessage sysUserMessage);
}
