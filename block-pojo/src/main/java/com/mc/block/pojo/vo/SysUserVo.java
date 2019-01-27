package com.mc.block.pojo.vo;

import com.mc.block.pojo.sys.SysUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysUserVo implements Serializable {
    private SysUser userInfo;
    private List<String> roles;

    public SysUser getUserInfo() {
        return userInfo;
    }

    public SysUserVo setUserInfo(SysUser userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public SysUserVo setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }


    public SysUserVo addRoles(String role) {
        if(roles == null){
            roles = new ArrayList<>();
        }
        roles.add(role);
        return this;
    }

}
