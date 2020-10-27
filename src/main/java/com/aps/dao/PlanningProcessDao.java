package com.aps.dao;

import com.aps.bean.MO;
import com.aps.bean.PlanningProcess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PlanningProcessDao {
    List<PlanningProcess> findList(PlanningProcess planningProcess);
}
