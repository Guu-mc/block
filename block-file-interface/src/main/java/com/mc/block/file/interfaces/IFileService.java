package com.mc.block.file.interfaces;

import com.mc.block.pojo.FileAttributes;

public interface IFileService {

    boolean isExist(String sha1);

    void upload(FileAttributes fileAttributes);

    FileAttributes findBySha1(String sha1);
}
