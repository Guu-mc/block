package com.mc.block.pojo;

import java.io.Serializable;

public class VideoInfo implements Serializable {
    private Integer id;
    private String videoDuration;                       //视频时长
    private Integer videoTimelen;                       //视频时长 (秒)
    private String startTime;                           //开始时间
    private String bitRate;                             //比特率
    private String codedFormat;                         //编码格式
    private String videoFormat;                         //视频格式
    private String resolvingPower;                      //分辨率
    private Integer fileAttributesId;                   //文件属性Id

    public Integer getId() {
        return id;
    }

    public VideoInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public VideoInfo setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
        return this;
    }

    public Integer getVideoTimelen() {
        return videoTimelen;
    }

    public VideoInfo setVideoTimelen(Integer videoTimelen) {
        this.videoTimelen = videoTimelen;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public VideoInfo setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getBitRate() {
        return bitRate;
    }

    public VideoInfo setBitRate(String bitRate) {
        this.bitRate = bitRate;
        return this;
    }

    public String getCodedFormat() {
        return codedFormat;
    }

    public VideoInfo setCodedFormat(String codedFormat) {
        this.codedFormat = codedFormat;
        return this;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public VideoInfo setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
        return this;
    }

    public String getResolvingPower() {
        return resolvingPower;
    }

    public VideoInfo setResolvingPower(String resolvingPower) {
        this.resolvingPower = resolvingPower;
        return this;
    }

    public Integer getFileAttributesId() {
        return fileAttributesId;
    }

    public VideoInfo setFileAttributesId(Integer fileAttributesId) {
        this.fileAttributesId = fileAttributesId;
        return this;
    }
}
