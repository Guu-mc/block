package com.mc.block.pojo.sys;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.constraints.Null;
import java.io.Serializable;

public class SysPermissionRole implements Serializable {
    @Null
    @Ignore
    private Integer id;
    private Integer sysRoleId;
    private Integer sysPermissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Integer sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

}
