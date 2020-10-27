package com.aps.controller;

import com.aps.bean.Project;
import com.aps.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projectRest")
public class ProjectRestController {

    @Autowired
    ProjectDao projectDao;

    @RequestMapping("/findList")
    public List<Project> findList(Project project){
        return projectDao.findList(project);
    }

    @RequestMapping("/find")
    public Project find(Project project){
        return projectDao.find(project);
    }
}
