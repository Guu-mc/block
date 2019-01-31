package com.mc.block.dao;

import com.mc.block.pojo.FileAttributes;
import com.mc.block.redis.annotation.CacheEvict;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MInsert;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface FileAttributesMapper {
    @MInsert
    @Options(useGeneratedKeys = true)
    @CacheEvict
    int insert(FileAttributes fileAttributes);
    @MSelect
    @Cacheable
    FileAttributes findBySha1(@Param("sha1") String sha1);
}
