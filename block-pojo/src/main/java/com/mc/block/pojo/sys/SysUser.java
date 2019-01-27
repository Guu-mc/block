package com.mc.block.pojo.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Null;
import java.io.Serializable;

public class SysUser implements Serializable {

    @Null
    @JsonIgnore
    private Integer id;
    private String userCode;
    private String username;
    private String name;
    @JsonIgnore
    private String password;
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
