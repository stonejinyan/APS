package com.aps.dao;

import com.aps.bean.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDao {
    public int insert(Project project);
    public int update(Project project);
    public List<Project> findList(Project project);
    public Project find(Project project);
}
