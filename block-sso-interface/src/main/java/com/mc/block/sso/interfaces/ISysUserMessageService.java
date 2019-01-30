package com.mc.block.sso.interfaces;

import com.mc.block.pojo.sys.SysUserMessage;

import java.util.List;

public interface ISysUserMessageService {
    List<SysUserMessage> findByUserIdAndStatus(Integer userId, Integer status);

    int findCountByUserIdAndStatus(Integer userId, Integer status);

    SysUserMessage findByIdAndUserId(Integer id, Integer userId);

    String messageContent(Integer id, Integer userId);

    void removeRead(Integer id, Integer userId) throws Exception;

    void restore(Integer id, Integer userId) throws Exception;
}
