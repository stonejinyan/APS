package com.aps.solver;

import com.aps.bean.TimeTable;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.SolverManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class TimeTableSolver {
    SolverConfig solverConfig = SolverConfig.createFromXmlResource("solverConfig.xml");
    SolverManager<TimeTable, UUID> solverManager = SolverManager.create(solverConfig, new SolverManagerConfig());


    public TimeTable solve(TimeTable problem) {
        UUID problemId = UUID.randomUUID();
        // Submit the problem to start solving
        SolverJob<TimeTable, UUID> solverJob = solverManager.solve(problemId, problem);
        TimeTable solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        System.out.println();
        return solution;
    }
}
