package com.mc.block.file.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.FileAttributesMapper;
import com.mc.block.file.interfaces.IFileAttributesService;
import com.mc.block.pojo.FileAttributes;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FileAttributesServiceImpl implements IFileAttributesService {

    @Autowired
    private FileAttributesMapper fileAttributesMapper;
    @Override
    public int insert(FileAttributes fileAttributes) {
        return fileAttributesMapper.insert(fileAttributes);
    }

    @Override
    public FileAttributes findBySha1(String sha1) {
        return fileAttributesMapper.findBySha1(sha1);
    }
}
