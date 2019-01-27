package com.mc.block.pojo.sys;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.constraints.Null;
import java.io.Serializable;

public class SysRoleUser implements Serializable {
    @Null
    @Ignore
    private Integer id;
    private Integer sysUserId;
    private Integer sysRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

}
