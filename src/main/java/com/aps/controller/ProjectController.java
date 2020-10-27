package com.aps.controller;

import com.aps.bean.Project;
import com.aps.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectDao projectDao;

    @RequestMapping("/page")
    public String projectPage(ModelMap map){
        return "/projectPage";
    }

    @RequestMapping("/insert")
    public String insert(Project project){
        projectDao.insert(project);
        return "/projectPage";
    }
    @RequestMapping("/update")
    public String update(Project project){
        projectDao.update(project);
        return "/projectPage";
    }
}
