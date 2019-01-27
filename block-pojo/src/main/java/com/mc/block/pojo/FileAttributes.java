package com.mc.block.pojo;

import java.io.Serializable;

public class FileAttributes implements Serializable {
    private Integer id;
    private String group;
    private String path;
    private String name;
    private String ext;
    private String type;
    private String sha1;

    public Integer getId() {
        return id;
    }

    public FileAttributes setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPath() {
        return path;
    }

    public FileAttributes setPath(String path) {
        this.path = path;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getExt() {
        return ext;
    }

    public FileAttributes setExt(String ext) {
        this.ext = ext;
        return this;
    }

    public String getType() {
        return type;
    }

    public FileAttributes setType(String type) {
        this.type = type;
        return this;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }
}
