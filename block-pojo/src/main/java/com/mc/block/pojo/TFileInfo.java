package com.mc.block.pojo;

import com.mc.orange.mmsql.annotations.MIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 文件分块
 */
public class TFileInfo {
    private Integer id;                     //临时文件标识符
    private String path;                    //文件路径(包含文件名)
    private String name;                    //文件名(包含扩展名)
    private String ext;                     //扩展名
    private String type;                    //文件类型
    @NotNull
    @Size
    private Long totalSize;                 //文件总大小
    @MIgnore
    @NotNull
    @Size(max = 52428800)                   //50Mb
    private Integer size;                   //分块大小
    @NotNull
    @Size(min = 40, max = 40)
    private String Sha1;                    //源文件Sha1
    @MIgnore
    @NotNull
    @Size(min = 40, max = 40)
    private String Sha1Block;               //分块文件Sha1
    @NotNull
    @Size(min = 1)
    private Integer totalBlock;             //总块数
    @MIgnore
    @NotNull
    @Size(min = 1)
    private Integer block;                  //当前块
    private Integer includeBlock;           //以包含块数, 当前块为1 可能会包含多个块
    private Integer accomplish;             // 0: 未完成 1:上传以完成
    private Date uploadDate;                //上传时间

    public Integer getId() {
        return id;
    }

    public TFileInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public TFileInfo setPath(String path) {
        this.path = path;
        return this;
    }

    public String getName() {
        return name;
    }

    public TFileInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getExt() {
        return ext;
    }

    public TFileInfo setExt(String ext) {
        this.ext = ext;
        return this;
    }

    public String getType() {
        return type;
    }

    public TFileInfo setType(String type) {
        this.type = type;
        return this;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public TFileInfo setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public TFileInfo setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getSha1() {
        return Sha1;
    }

    public TFileInfo setSha1(String sha1) {
        Sha1 = sha1;
        return this;
    }

    public String getSha1Block() {
        return Sha1Block;
    }

    public TFileInfo setSha1Block(String sha1Block) {
        Sha1Block = sha1Block;
        return this;
    }

    public Integer getTotalBlock() {
        return totalBlock;
    }

    public TFileInfo setTotalBlock(Integer totalBlock) {
        this.totalBlock = totalBlock;
        return this;
    }

    public Integer getBlock() {
        return block;
    }

    public TFileInfo setBlock(Integer block) {
        this.block = block;
        return this;
    }

    public Integer getIncludeBlock() {
        return includeBlock;
    }

    public TFileInfo setIncludeBlock(Integer includeBlock) {
        this.includeBlock = includeBlock;
        return this;
    }

    public Integer getAccomplish() {
        return accomplish;
    }

    public TFileInfo setAccomplish(Integer accomplish) {
        this.accomplish = accomplish;
        return this;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public TFileInfo setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }
}
