package com.mc.block.dao;

import com.mc.block.pojo.Sha1;
import com.mc.orange.mmsql.annotations.MInsert;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface ISha1Mapper {
    @MInsert
    @Options(useGeneratedKeys = true)
    int insert(Sha1 sha1);
    @MSelect
    Sha1 selectHash(@Param("hash") String sha1);
}
