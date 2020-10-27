package com.aps.service.impl;

import com.aps.bean.*;
import com.aps.dao.PlanningProcessDao;
import com.aps.dao.ProcessModelPriorityDao;
import com.aps.dao.TestMODao;
import com.aps.service.APSSolve;
import com.aps.solver.MOPlanningSolver;
import com.aps.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class APSSolveImpl implements APSSolve {

    @Autowired
    private MOPlanningSolver moPlanningSolver;
    @Autowired
    private TestMODao testMODao;
    @Autowired
    private PlanningProcessDao planningProcessDao;
    @Autowired
    ProcessModelPriorityDao processModelPriorityDao;
    private static final Logger log = LoggerFactory.getLogger(APSSolveImpl.class);
    public void solve() {
        MOPlanning problem = generateProblem();
        MOPlanning solution = moPlanningSolver.solve(problem);
        List<MO> moList = solution.getMoList();
        for (int i = 0; i < moList.size(); i++) {
            MO mo = moList.get(i);
            testMODao.update(mo);
        }
        log.info(solution.getScore().toString());
    }

    private MOPlanning generateProblem() {
        List<PlanningProcess> planningProcesses = planningProcessDao.findList(new PlanningProcess());
        List<PlanningCalendar> planningCalendarList = new ArrayList<>();
        Date startDate = DateUtil.strToDate("2020-08-24");
        for(int i=0;i<37;i++){
            startDate = DateUtil.getTomorrowDate(startDate);
            if (DateUtil.getWeek(startDate).equals("1")||DateUtil.getWeek(startDate).equals("7")){
                continue;
            }else {
                planningCalendarList.add(new PlanningCalendar(i,startDate,planningProcessDao.findList(new PlanningProcess())));
            }
        }
        List<MO> moList = testMODao.findList(new MO());
        List<ProcessModelPriority> processModelPriorities = processModelPriorityDao.findList(new ProcessModelPriority());
        return new MOPlanning(planningCalendarList, planningProcesses, moList,processModelPriorities);
    }

    public void stopSolving(){
        moPlanningSolver.stopSolving();
    }
}
