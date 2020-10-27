package com.aps.solver;

import com.aps.ApsApplication;

import com.aps.bean.*;
import com.aps.dao.PlanningProcessDao;
import com.aps.dao.ProcessModelPriorityDao;
import com.aps.dao.TestMODao;
import com.aps.util.DateUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApsApplication.class)
public class MOPlanningSolverTest {

    @Autowired
    private MOPlanningSolver timeTableSolver;
    @Autowired
    private TestMODao testMODao;
    @Autowired
    private PlanningProcessDao planningProcessDao;
    @Autowired
    ProcessModelPriorityDao processModelPriorityDao;

    @Test
    public void solve() {
        MOPlanning problem = generateProblem();
        MOPlanning solution = timeTableSolver.solve(problem);
        List<MO> moList = solution.getMoList();
        for(int i = 0; i< moList.size(); i++){
            MO mo = moList.get(i);
            testMODao.update(mo);
        }
    }

    private MOPlanning generateProblem() {
        List<PlanningProcess> planningProcesses = planningProcessDao.findList(new PlanningProcess());
        List<MO> moList = testMODao.findList(new MO());
        for(MO mo:moList){
            Date myMAXDate = DateUtil.getNextDateDay(mo.getDeliveryDate(),-(int)mo.getLt());
            Date myMinDate = DateUtil.getNextDateDay(mo.getDeliveryDate(),-(int)mo.getLt()-3);
            if (MOPlanningSolver.minDate==null||MOPlanningSolver.minDate.after(myMinDate)){
                MOPlanningSolver.minDate=myMinDate;
            }
            if (MOPlanningSolver.maxDate==null||MOPlanningSolver.maxDate.before(myMAXDate)){
                MOPlanningSolver.maxDate=myMAXDate;
            }
        }
        Date nowDate = MOPlanningSolver.minDate;
        List<PlanningCalendar> calendarList = new ArrayList<>();
        for (int i = 0; i < DateUtil.getTwoDay(MOPlanningSolver.minDate,MOPlanningSolver.maxDate); i++) {
            nowDate = DateUtil.getNextDateDay(nowDate,1);
            calendarList.add(new PlanningCalendar(i,nowDate,planningProcesses));
        }
        MOPlanningSolver.planningCalendarList = calendarList;
        List<ProcessModelPriority> processModelPriorities = processModelPriorityDao.findList(new ProcessModelPriority());
        for (MO mo:moList){
            mo.setCalendarList(getCalendarList(mo));
        }
        System.out.println(moList.get(0).getCalendarList().size());
        return new MOPlanning(calendarList, planningProcesses, moList,processModelPriorities);
    }


    public List<PlanningCalendar> getCalendarList(MO mo){
        List<PlanningCalendar> planningCalendarList = new ArrayList<>();
        Date deliverydate = mo.getDeliveryDate();
        double lt = mo.getLt();
        Date startDate = DateUtil.getNextDateDay(deliverydate,(int)-lt);
        Date materialdate = mo.getMaterialDate();
        if (startDate.before(materialdate)||startDate.equals(materialdate)){
            for (PlanningCalendar planningCalendar : MOPlanningSolver.planningCalendarList) {
                if (planningCalendar.getDateTime().equals(materialdate)){
                    planningCalendarList.add(planningCalendar);
                }
            }
        }else {
            for (int i = 0; i < 3; i++) {
                Date date = DateUtil.getNextDateDay(startDate, -i);
                if (date.before(materialdate)) {
                    break;
                }
                for (PlanningCalendar planningCalendar : MOPlanningSolver.planningCalendarList) {
                    if (planningCalendar.getDateTime().equals(date)){
                        planningCalendarList.add(planningCalendar);
                    }
                }
            }
        }
        return planningCalendarList;
    }

    @Test
    public void testMODao(){
        System.out.println(testMODao.findList(new MO()).size());
    }
}
