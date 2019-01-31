package com.mc.block.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.constant.pojo.SysUserMessageConstant;
import com.mc.block.dao.sys.SysUserMessageMapper;
import com.mc.block.pojo.sys.SysUserMessage;
import com.mc.block.sso.interfaces.ISysUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SysUserMessageServiceImpl implements ISysUserMessageService {
    @Autowired
    private SysUserMessageMapper sysUserMessageMapper;

    @Override
    public List<SysUserMessage> findByUserIdAndStatus(Integer userId, Integer status){
        return sysUserMessageMapper.findByUserIdAndStatus(userId, status);
    }

    @Override
    public int findCountByUserIdAndStatus(Integer userId, Integer status) {
        return sysUserMessageMapper.findCountByUserIdAndStatus(userId, status);
    }

    @Override
    public SysUserMessage findByIdAndUserId(Integer id, Integer userId) {
        return sysUserMessageMapper.findByIdAndUserId(id, userId);
    }

    @Override
    public String messageContent(Integer id, Integer userId) {
        SysUserMessage sysUserMessage = sysUserMessageMapper.findByIdAndUserId(id, userId);
        if(sysUserMessage != null){
            if(SysUserMessageConstant.STATUS_UNREAD.equals(sysUserMessage.getStatus())){
                sysUserMessage.setStatus(SysUserMessageConstant.STATUS_READ);
                sysUserMessageMapper.update(sysUserMessage);
            }
            return sysUserMessage.getContent();
        }
        return "";
    }

    @Override
    public void removeRead(Integer id, Integer userId) throws Exception {
        change(id, userId, SysUserMessageConstant.STATUS_TAG_DELETION);
    }

    @Override
    public void restore(Integer id, Integer userId) throws Exception {
        change(id, userId, SysUserMessageConstant.STATUS_READ);
    }

    private void change(Integer id, Integer userId, Integer status) throws Exception {
        SysUserMessage sysUserMessage = sysUserMessageMapper.findByIdAndUserId(id, userId);
        if(sysUserMessage == null){
            throw new Exception("没有此条消息");
        }
        sysUserMessage.setStatus(status);
        sysUserMessageMapper.update(sysUserMessage);
    }
}
