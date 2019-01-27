package com.mc.block.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mc.orange.mmsql.annotations.MAssociate;
import com.mc.orange.mmsql.annotations.MTable;

import java.io.Serializable;
import java.util.ArrayList;

@MTable("aaa")
public class PlayInfo implements Serializable {
    private Integer id;
    @JsonIgnore
    private String cover;
    private String shortVideo;
    private String video;
    @JsonIgnore
    private String originalVideo;
    @MAssociate(select = "gg")
    private ArrayList<Sha1> sha1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public PlayInfo setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public String getShortVideo() {
        return shortVideo;
    }

    public PlayInfo setShortVideo(String shortVideo) {
        this.shortVideo = shortVideo;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public PlayInfo setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getOriginalVideo() {
        return originalVideo;
    }

    public void setOriginalVideo(String originalVideo) {
        this.originalVideo = originalVideo;
    }
}
