package com.aps.bean;

import lombok.Data;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import java.util.List;

@PlanningSolution
@Data
public class MOPlanning {

    @ProblemFactCollectionProperty
    private List<PlanningCalendar> calendarList;

    @ValueRangeProvider(id = "planningProcessRange")
    @ProblemFactCollectionProperty
    private List<PlanningProcess> processList;

    @PlanningEntityCollectionProperty
    private List<MO> moList;

    @PlanningScore
    private HardMediumSoftScore score;

    private List<ProcessModelPriority> processModelPriorities;


    public MOPlanning(List<PlanningCalendar> calendarList, List<PlanningProcess> processList, List<MO> moList,List<ProcessModelPriority> processModelPriorities) {
        this.calendarList = calendarList;
        this.processList = processList;
        this.moList = moList;
        this.processModelPriorities = processModelPriorities;
    }

    public MOPlanning() {
    }

}
