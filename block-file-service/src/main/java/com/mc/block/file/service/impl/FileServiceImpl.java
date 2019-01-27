package com.mc.block.file.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.file.interfaces.IFileAttributesService;
import com.mc.block.file.interfaces.IFileService;
import com.mc.block.pojo.FileAttributes;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private IFileAttributesService fileAttributesService;

    @Override
    public void upload(FileAttributes fileAttributes) {
        fileAttributesService.insert(fileAttributes);
    }

    @Override
    public FileAttributes findBySha1(String sha1) {
        return fileAttributesService.findBySha1(sha1);
    }

    @Override
    public boolean isExist(String sha1) {
        return fileAttributesService.findBySha1(sha1) != null;
    }

}
