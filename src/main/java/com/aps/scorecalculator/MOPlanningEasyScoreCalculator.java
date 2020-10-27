package com.aps.scorecalculator;

import com.aps.bean.*;
import com.aps.solver.MOPlanningSolver;
import com.aps.util.DateUtil;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.List;

public class MOPlanningEasyScoreCalculator implements EasyScoreCalculator<MOPlanning> {

    public static boolean isSolutionInitialized = false;

    @Override
    public HardMediumSoftScore calculateScore(MOPlanning moPlanning) {
        int[][] quantity = new int[MOPlanningSolver.planningCalendarList.size()+1][6];
        List<MO> moList = moPlanning.getMoList();
        List<ProcessModelPriority> processModelPriorities = moPlanning.getProcessModelPriorities();
        int initScore = 0;
        int hardScore = 0;
        int softScore = 0;
        int mediumScore = 0;

        int leadtimeScore = 0;
        int materialDateScore = 0;
        int processMixScore = 0;
        int processModelPriorityScore = 0;
        int earlyScore = 0;
        for (int c = 0; c < quantity.length; c++) {
            for (int i = 0; i < quantity[c].length; i++) {
                quantity[c][i] = 0;
            }
        }
        /**
         if (initScore==0) {
         for (int i = 0; i < moList.size(); i++) {
         MO mo = moList.get(i);
         PlanningCalendar startDate = mo.getStartDate();
         PlanningProcess planningProcess = mo.getPlanningProcess();
         if (startDate == null || planningProcess == null) {
         hardScore = hardScore - 1000;
         //System.out.println(mo.getMo()+":开始日期为空"+startDate+"或者产线为空"+planningProcess);
         //return HardMediumSoftScore.of(hardScore, mediumScore, softScore);
         } else {
         initScore = 1;
         }
         }
         }
         */
        for (int i = 0; i < moList.size(); i++) {

            MO mo = moList.get(i);
            PlanningCalendar startDate = mo.getPlanningCalendar();

            PlanningProcess planningProcess = mo.getPlanningProcess();
            if (startDate != null && planningProcess != null) {
                //System.out.println(startDate.getDateTime());
                int processID = planningProcess.getProcessID();
                //startDate>materialDate
                if (DateUtil.getTwoDay(mo.getMaterialDate(), startDate.getDateTime()) < 0) {
                    hardScore = hardScore - 10;
                    materialDateScore = materialDateScore - 10;
                }
                double waitDays = DateUtil.getTwoDay(startDate.getDateTime(), mo.getDeliveryDate()) - mo.getLt();
                if (waitDays < 0) {
                    hardScore = hardScore + (int) waitDays;
                    leadtimeScore = leadtimeScore + (int) waitDays;
                } else {
                    mediumScore = mediumScore - (int) waitDays;
                    earlyScore = earlyScore - (int) waitDays;
                }
                for (int j = 0; j < processModelPriorities.size(); j++) {
                    ProcessModelPriority processModelPriority = processModelPriorities.get(j);
                    if (processModelPriority.getProcessID() == mo.getPlanningProcess().getProcessID() && processModelPriority.getProductModelID() == mo.getModelID()) {
                        softScore = softScore - processModelPriority.getPriority() + 1;
                        processModelPriorityScore = processModelPriorityScore - processModelPriority.getPriority() + 1;
                        break;
                    }
                }
                int days = (int) DateUtil.getTwoDay(MOPlanningSolver.minDate, startDate.getDateTime());
                quantity[days][processID - 1]++;
                if (quantity[days][processID - 1] > 10) {
                    hardScore = hardScore - 5;
                    processMixScore = processMixScore - 5;
                }
//                for (int j = 0; j < planningCalendarList.size(); j++) {
//                    PlanningCalendar planningCalendar = planningCalendarList.get(j);
//                    if (planningCalendar.getDateTime().equals(startDate.getDateTime())) {
//                        List<PlanningProcess> planningProcesses = planningCalendar.getPlanningProcesses();
//                        for (int k = 0; k < planningProcesses.size(); k++) {
//                            PlanningProcess calendarPlanningProcess = planningProcesses.get(k);
//                            if (calendarPlanningProcess.getId() == mo.getPlanningProcess().getId()) {
//                                calendarPlanningProcess.setQuantity(calendarPlanningProcess.getQuantity() + 1);
//                                if (calendarPlanningProcess.getQuantity() > 10) {
//                                    hardScore = hardScore - 5;
//                                    processMixScore = processMixScore -5;
//                                }
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                }
            } else {
                initScore--;
            }
        }
        if (initScore != 0) {
            //System.out.println("初始化："+initScore);
            MOPlanningSolver.hardScore = HardMediumSoftScore.ofUninitialized(initScore, hardScore, mediumScore, softScore);
        }
        //System.out.println(":开始日期早于到料日期:" + materialDateScore + "产线单日排产大于10台：" + processMixScore + "制作周期不足:" + leadtimeScore + "产线优先级：" + processModelPriorityScore + "提前生产：" + earlyScore);
        return HardMediumSoftScore.of(hardScore, mediumScore, softScore);
    }
}
