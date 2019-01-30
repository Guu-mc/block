package com.mc.block.pojo.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class SysUserMessage implements Serializable {
    private Integer id;
    @JsonIgnore
    private Integer userId;
    @JsonIgnore
    private Integer senderId;
    private String title;
    @JsonIgnore
    private String content;
    private Date createTime;
    @JsonIgnore
    private Integer status;

    public Integer getId() {
        return id;
    }

    public SysUserMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public SysUserMessage setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public SysUserMessage setSenderId(Integer senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SysUserMessage setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SysUserMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SysUserMessage setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public SysUserMessage setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
