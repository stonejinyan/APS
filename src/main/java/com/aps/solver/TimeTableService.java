package com.aps.solver;

import com.aps.bean.*;
import org.optaplanner.core.api.solver.SolverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TimeTableService {


    SolverManager<MOPlanning, Long> solverManager;
    MOPlanning moPlanning;
    private static final Logger log = LoggerFactory.getLogger(TimeTableService.class);
    // Returns immediately
    public void solveLive(Long problemId) {
        solverManager.solveAndListen(problemId,this::findById,this::save);
    }
    // Called once, when solving starts
    public MOPlanning findById(Long problemId) {
        log.info("计算开始,problemId:"+problemId);
        log.info("状态："+solverManager.getSolverStatus(problemId));
        MOPlanning newMOPlanning = new MOPlanning();
        newMOPlanning.setMoList(moPlanning.getMoList());
        newMOPlanning.setProcessList(moPlanning.getProcessList());
        newMOPlanning.setProcessModelPriorities(moPlanning.getProcessModelPriorities());
        return newMOPlanning;
    }
    // Called multiple times, for every best solution change
    public void save(MOPlanning moPlanning) {
        log.info("当前最优解："+moPlanning.getScore().toString());
    }
    public void stopSolving(Long problemId) {
        solverManager.terminateEarly(problemId);
    }

    public TimeTableService(SolverManager<MOPlanning, Long> solverManager,MOPlanning moPlanning) {
        this.solverManager = solverManager;
        this.moPlanning = moPlanning;
    }

}
