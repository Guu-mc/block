package com.mc.block.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mc.block.dao.ProductTypeMapper;
import com.mc.block.interfaces.IProductTypeService;
import com.mc.block.pojo.ProductType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> findAll() {
        return productTypeMapper.findAll();
    }

}
