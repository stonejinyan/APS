package com.aps.bean;

import lombok.Data;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.util.Date;
import java.util.List;

@Data
public class PlanningCalendar {

    @PlanningId
    private long id;
    private Date dateTime;
    private double quantity = 0;
    private List<PlanningProcess> planningProcesses;

    public PlanningCalendar(long id,Date dateTime,List<PlanningProcess> planningProcesses) {
        this.id=id;
        this.dateTime = dateTime;
        this.planningProcesses = planningProcesses;
    }

    public PlanningCalendar() {
    }
}
