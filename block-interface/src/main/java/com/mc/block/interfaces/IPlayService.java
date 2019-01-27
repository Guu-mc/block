package com.mc.block.interfaces;

import com.mc.block.pojo.PlayInfo;

import java.util.List;

public interface IPlayService {

    List<PlayInfo> recommend(PlayInfo playInfo, String unionId, int size);
}
