package com.mc.block.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.IPlayMapper;
import com.mc.block.interfaces.IPlayService;
import com.mc.block.pojo.PlayInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 18000)
public class PlayServiceImpl implements IPlayService {

    @Autowired
    private IPlayMapper playMapper;

    @Override
    public List<PlayInfo> recommend(PlayInfo playInfo, String unionId, int size) {
        return playMapper.selectRandom(size);
    }
}
