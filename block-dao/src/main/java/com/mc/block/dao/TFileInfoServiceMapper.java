package com.mc.block.dao;

import com.mc.block.pojo.TFileInfo;
import com.mc.orange.mmsql.annotations.MInsert;
import com.mc.orange.mmsql.annotations.MSelect;
import com.mc.orange.mmsql.annotations.MUpdate;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface TFileInfoServiceMapper {
    @MInsert
    @Options(useGeneratedKeys = true)
    int insert(TFileInfo tFileInfo);
    @MUpdate
    int update(TFileInfo tFileInfo);

    @MSelect
    TFileInfo findBySha1(@Param("sha1") String sha1);
}
