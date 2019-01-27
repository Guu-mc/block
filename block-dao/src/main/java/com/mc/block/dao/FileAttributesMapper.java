package com.mc.block.dao;

import com.mc.block.pojo.FileAttributes;
import com.mc.orange.mmsql.annotations.MInsert;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "fileAttributes")
public interface FileAttributesMapper {
    @MInsert
    @Options(useGeneratedKeys = true)
    @CacheEvict(allEntries = true)
    int insert(FileAttributes fileAttributes);
    @MSelect
    @Cacheable
    FileAttributes findBySha1(@Param("sha1") String sha1);
}
