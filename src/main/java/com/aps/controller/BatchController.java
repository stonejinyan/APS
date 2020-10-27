package com.aps.controller;

import com.aps.bean.Batch;
import com.aps.bean.Project;
import com.aps.dao.BatchDao;
import com.aps.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    BatchDao batchDao;
    @Autowired
    ProjectDao projectDao;

    @RequestMapping("/page")
    public String batchPage(ModelMap map){
        map.addAttribute("projects",projectDao.findList(new Project()));
        return "/batchPage";
    }

    @RequestMapping("/insert")
    public String insert(ModelMap map,Batch batch){
        batchDao.insert(batch);
        map.addAttribute("projects",projectDao.findList(new Project()));
        return "/batchPage";
    }
    @RequestMapping("/update")
    public String update(ModelMap map,Batch batch){
        batchDao.update(batch);
        map.addAttribute("projects",projectDao.findList(new Project()));
        return "/batchPage";
    }
}
