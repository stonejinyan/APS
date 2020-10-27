package com.aps.bean;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@PlanningEntity
public class MO  extends BaseRowModel implements Serializable {
    @PlanningId
    private int moID;
    private String panel;
    private String mo;
    private String des;
    private double quantity;
    private String ci;
    private int modelID;
    private String so;
    private String batch;
    private String wbs;
    private String projectName;
    private int leadTimeDays;
    private Date deliveryDate;
    private Date materialDate;
    private Date moStartDate;
    private String productModelName;
    private String productClassifyName;
    private double tt;
    private double lt;
    private double lt_include_fat;
    private double lt_to_fat;
    private double availableDays;
    private int processID;
    private String processName;

    @PlanningVariable(valueRangeProviderRefs = "planningProcessRange")
    private PlanningProcess planningProcess;

    @PlanningVariable(valueRangeProviderRefs = "planningCalendarRange")
    private PlanningCalendar planningCalendar;

    @ValueRangeProvider(id = "planningCalendarRange")
    @ProblemFactCollectionProperty
    private List<PlanningCalendar> calendarList;

    public MO() {
    }

}
