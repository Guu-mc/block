package com.mc.block.pojo.vo;

import com.mc.block.pojo.sys.SysUserMessage;

import java.io.Serializable;
import java.util.List;

public class SysUserMessageVo implements Serializable {
    private List<SysUserMessage> unreadList;
    private List<SysUserMessage> readedList;
    private List<SysUserMessage> trashList;

    public List<SysUserMessage> getUnreadList() {
        return unreadList;
    }

    public SysUserMessageVo setUnreadList(List<SysUserMessage> unreadList) {
        this.unreadList = unreadList;
        return this;
    }

    public List<SysUserMessage> getReadedList() {
        return readedList;
    }

    public SysUserMessageVo setReadedList(List<SysUserMessage> readedList) {
        this.readedList = readedList;
        return this;
    }

    public List<SysUserMessage> getTrashList() {
        return trashList;
    }

    public SysUserMessageVo setTrashList(List<SysUserMessage> trashList) {
        this.trashList = trashList;
        return this;
    }
}
