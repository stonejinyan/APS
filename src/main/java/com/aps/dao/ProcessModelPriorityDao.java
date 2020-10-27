package com.aps.dao;

import com.aps.bean.ProcessModelPriority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessModelPriorityDao {
    public ProcessModelPriority find(ProcessModelPriority processModelPriority);
    public List<ProcessModelPriority> findList(ProcessModelPriority processModelPriority);
}