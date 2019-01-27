package com.mc.block.pojo.sys;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.constraints.Null;
import java.io.Serializable;

public class SysPermission implements Serializable {
    @Null
    @Ignore
    private Integer id;
    private String name;
    private String description;
    private String url;
    private String method;
    private Integer pId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
