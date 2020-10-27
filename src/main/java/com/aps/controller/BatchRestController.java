package com.aps.controller;

import com.aps.bean.Batch;
import com.aps.bean.BatchTable;
import com.aps.dao.BatchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batchRest")
public class BatchRestController {

    @Autowired
    BatchDao batchDao;

    @RequestMapping("/findList")
    public List<BatchTable> findList(Batch batch){
        return batchDao.findList(batch);
    }

    @RequestMapping("/find")
    public BatchTable find(Batch batch){
        return batchDao.find(batch);
    }
}
