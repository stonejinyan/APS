package com.aps.bean;

import lombok.Data;
import org.optaplanner.core.api.domain.lookup.PlanningId;

@Data
public class PlanningProcess {
    @PlanningId
    private int processID;
    private String processName;
    private double quantity;
    public PlanningProcess(int processID, String processName) {
        this.processID = processID;
        this.processName = processName;
    }
    public PlanningProcess() {
    }
}
