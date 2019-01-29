package com.mc.block.dao;

import com.mc.block.pojo.ProductType;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.orange.mmsql.annotations.MSelect;
import java.util.List;

public interface ProductTypeMapper {
    @MSelect
    @Cacheable
    List<ProductType> findAll();
}
