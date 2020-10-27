package com.aps.dao;

import com.aps.bean.ProductModelTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductModelTableDao {
    public ProductModelTable findByClassifyAndModelName(ProductModelTable productModelTable);
}
