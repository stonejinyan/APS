package com.aps.dao;

import com.aps.bean.Batch;
import com.aps.bean.BatchTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BatchDao {
    public int insert(Batch batch);
    public int update(Batch batch);
    public List<BatchTable> findList(Batch batch);
    public BatchTable find(Batch batch);
}
