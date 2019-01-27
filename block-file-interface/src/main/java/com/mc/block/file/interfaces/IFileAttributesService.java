package com.mc.block.file.interfaces;

import com.mc.block.pojo.FileAttributes;

public interface IFileAttributesService {
    int insert(FileAttributes fileAttributes);

    FileAttributes findBySha1(String sha1);
}
