package com.aps.solver;

import com.aps.bean.*;
import com.aps.dao.PlanningProcessDao;
import com.aps.dao.ProcessModelPriorityDao;
import com.aps.dao.TestMODao;
import com.aps.util.DateUtil;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.SolverManagerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
public class MOPlanningSolver {

    @Autowired
    TestMODao testMODao;
    @Autowired
    PlanningProcessDao planningProcessDao;
    @Autowired
    ProcessModelPriorityDao processModelPriorityDao;
    public static  final UUID problemId = UUID.randomUUID();
    public static HardMediumSoftScore hardScore = HardMediumSoftScore.of(0,0,0);
    public static List<MO> mos;
    public static Date maxDate;
    public static Date minDate;
    public static int[][] processModelPrioritys = new int[7][39];
    public static int[][] dateProcessQuantitys;
    public static int days = 0;
    public static Date endDate = DateUtil.strToDate("2020-08-25");
    public static List<PlanningCalendar> planningCalendarList;

    SolverConfig solverConfig = SolverConfig.createFromXmlResource("solverConfig.xml");
    SolverManager<MOPlanning, UUID> solverManager = SolverManager.create(solverConfig, new SolverManagerConfig());
    private static final Logger log = LoggerFactory.getLogger(MOPlanningSolver.class);
    public MOPlanning solve(MOPlanning problem) {
        testMODao.clear();
        for(ProcessModelPriority processModelPriority:problem.getProcessModelPriorities()){
            processModelPrioritys[processModelPriority.getProcessID()][processModelPriority.getProductModelID()] = processModelPriority.getPriority();
        }
        days = (int) DateUtil.getTwoDay(MOPlanningSolver.minDate,MOPlanningSolver.maxDate);
        dateProcessQuantitys = new int[7][days+1];
        SolverJob<MOPlanning, UUID> solverJob = solveLive(problemId);
        // Submit the problem to start solving
        //SolverJob<MOPlanning, UUID> solverJob = solverManager.solve(problemId, problem);
        MOPlanning solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
            for (MO mo:solution.getMoList()){
                testMODao.update(mo);
            }
        } catch (InterruptedException | ExecutionException e) {
           e.printStackTrace();
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }
    public SolverJob<MOPlanning, UUID> solveLive(UUID problemId) {
        return solverManager.solveAndListen(problemId,this::findById,this::save);
    }
    // Called once, when solving starts
    public MOPlanning findById(UUID problemId) {
        log.info("计算开始,problemId:"+problemId);
        log.info("状态："+solverManager.getSolverStatus(problemId));
        return generateProblem();
    }
    // Called multiple times, for every best solution change
    public void save(MOPlanning moPlanning) {
        //log.info("当前最优解："+moPlanning.getScore().toString());
        mos = new ArrayList<>();
        for (MO mo:moPlanning.getMoList()) {
            MO myMO = new MO();
            myMO.setMoID(mo.getMoID());
            //log.info("当前最优解："+mo.getStartDate().getDateTime());
            myMO.setMoStartDate(mo.getPlanningCalendar().getDateTime());
            myMO.setProcessID(mo.getPlanningProcess().getProcessID());
            myMO.setModelID(mo.getModelID());
            mos.add(myMO);
        }
        hardScore = hardScore.of(moPlanning.getScore().getHardScore(),moPlanning.getScore().getMediumScore(),moPlanning.getScore().getSoftScore());
    }
    public void stopSolving() {
        if(mos!=null) {
            for (MO mo:mos){
                testMODao.update(mo);
            }
        }
        solverManager.terminateEarly(problemId);
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
            for (int i = 0; i < 5; i++) {
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
}
