package com.mc.block.pojo;

import java.io.Serializable;

public class Sha1 implements Serializable {
    private Integer id;
    private String hash;
    private String src;

    public Integer getId() {
        return id;
    }

    public Sha1 setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public Sha1 setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public Sha1 setSrc(String src) {
        this.src = src;
        return this;
    }
}
