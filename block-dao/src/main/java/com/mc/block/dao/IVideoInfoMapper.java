package com.mc.block.dao;

import com.mc.block.pojo.VideoInfo;
import com.mc.orange.mmsql.annotations.MInsert;
import org.apache.ibatis.annotations.Options;

public interface IVideoInfoMapper {
    @MInsert
    @Options(useGeneratedKeys = true)
    int insert(VideoInfo videoInfo);
}
