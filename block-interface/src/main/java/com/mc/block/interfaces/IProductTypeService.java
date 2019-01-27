package com.mc.block.interfaces;

import com.mc.block.pojo.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> findAll();
}
