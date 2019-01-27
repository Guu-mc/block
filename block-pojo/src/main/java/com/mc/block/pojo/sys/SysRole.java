package com.mc.block.pojo.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SysRole implements Serializable {

    @Null
    @JsonIgnore
    private Integer id;
    @NotNull(message = "不能为空")
    @Size(min = 1, max = 200, message = "字符在1-200之间")
    private String name;

    public Integer getId() {
        return id;
    }

    public SysRole setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
