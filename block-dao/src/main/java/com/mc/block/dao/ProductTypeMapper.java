package com.mc.block.dao;

import com.mc.block.pojo.ProductType;
import com.mc.orange.mmsql.annotations.MSelect;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;

@CacheConfig(cacheNames = "productType")
public interface ProductTypeMapper {
    @MSelect
    @Cacheable
    List<ProductType> findAll();
}
