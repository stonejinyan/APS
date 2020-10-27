package com.aps.dao;

import com.aps.bean.MO;
import com.aps.bean.MOExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TestMODao {

    //AndonType get(String id);

    //Integer getTotalNum();

    List<MO> findList(MO request);

    //List<AndonType> findAllList();

    int insert(MOExcel moExcel);

    //int insertBatch(List<AndonType> andonTypes);

    int update(MO mo);
    int clear();
    Date getMaxDate();
    Date getMinDate();

    List<MOExcel> download(MOExcel moExcel);

    //int delete(List<Integer> ids);

}

