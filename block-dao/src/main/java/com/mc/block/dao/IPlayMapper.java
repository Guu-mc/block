package com.mc.block.dao;

import com.mc.block.pojo.PlayInfo;
import com.mc.orange.mmsql.annotations.MSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPlayMapper {
    @MSelect
    PlayInfo selectOne(PlayInfo playInfo);
    List<PlayInfo> selectList(PlayInfo playInfo);
    List<PlayInfo> selectRandom(@Param("size") Integer size);

}
