package com.aps.controller;

import com.aps.bean.DataBox;
import com.aps.bean.MO;
import com.aps.bean.MainDashboard;
import com.aps.dao.TestMODao;
import com.aps.solver.MOPlanningSolver;
import com.aps.util.DateUtil;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/moTableRest")
public class MOTableRestController {

    @Autowired
    TestMODao testMODao;

    @RequestMapping(value = "/mainDashboard")
    public MainDashboard MainDashboard(){
        if (MOPlanningSolver.maxDate==null||MOPlanningSolver.minDate==null){
            MOPlanningSolver.maxDate = testMODao.getMaxDate();
            MOPlanningSolver.minDate = testMODao.getMinDate();
        }
        if (MOPlanningSolver.maxDate==null){
            MOPlanningSolver.maxDate = new Date();
        }
        if (MOPlanningSolver.minDate==null){
            MOPlanningSolver.minDate = MOPlanningSolver.maxDate;
        }
        MainDashboard mainDashboard = new MainDashboard();
        int days = (int) DateUtil.getTwoDay(MOPlanningSolver.minDate,MOPlanningSolver.maxDate);
        Date currentDate = MOPlanningSolver.minDate;;
        mainDashboard.setDates(new String[days+1]);
        mainDashboard.setSeries(new DataBox[38*6]);
        String[] types = new String[]{"115(1MT)","115-S","115(2MT)","115(3MT)","115-3","115-70","230", "PFC", "ATS", "VSD/SS", "70-2P", "70-2M",
                "70-2IM", "70-MP", "70-MM", "70-MIM", "70-F", "APF","SPE",
                "Da(1MT)","Da(2MT)", "Da(4K)", "Da(5K)", "Df", "Daf", "Mf", "Dc", "ATS","VSD/SS", "MxP", "MxM",
                "MxIM", "UP", "UM", "UIM","Corner","APF","SPE"};
        //115
        String[] colors = new String[]{"#0099FF","#0099CC","#009999","#0066FF","#0066CC","#006699",
                //非标
                "#FFCC00", "#FFCC33", "#FFCC66", "#FFFF00",
                //70
                "#00bb52", "#33CC00","#33CC33", "#33CC66", "#33FF00","#33FF33", "#33FF66",
                //非标
                "#FFFF33","#FFFF66",
                //Blokset
                "#6633CC","#666633", "#666666", "#666699", "#6666CC", "#669933", "#669966", "#669999", "#6699CC","#66CC33", "#66CC66", "#66CC99",
                "#66CCCC", "#993333", "#993366", "#993399","#9933CC","#996633","#996666"};
        String[] lines = new String[]{"70","115","Blokset","PLC","非标","核电"};
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                DataBox dataBox = new DataBox();
                dataBox.setType("bar");
                dataBox.setName(lines[j]+"线："+types[i]);
                dataBox.setStack(lines[j]);
                dataBox.setData(new double[days+1]);
                dataBox.setColor(colors[i]);
                mainDashboard.getSeries()[i*6+j] = dataBox;
            }
        }
        for (int i = 0; i <= days; i++) {
            mainDashboard.getDates()[i]=DateUtil.dateToStr(currentDate);
            currentDate = DateUtil.getTomorrowDate(currentDate);
        }
        if(MOPlanningSolver.mos==null){
            MOPlanningSolver.mos = testMODao.findList(new MO());
        }
        for (int i = 0; i < MOPlanningSolver.mos.size(); i++) {
            MO mo = MOPlanningSolver.mos.get(i);
            //System.out.println("MO StartDate:"+mo.getMostartDate());
            int position = (int) DateUtil.getTwoDay(MOPlanningSolver.minDate,mo.getMoStartDate());
            mainDashboard.getSeries()[(mo.getModelID()-1)*6+mo.getProcessID()-1].getData()[position]++;
        }
        return mainDashboard;
    }

    @RequestMapping(value = "/hardMediumSoftScore")
    public HardMediumSoftScore getHardMediumSoftScore(){
        return MOPlanningSolver.hardScore;
    }

    @RequestMapping(value = "/list")
    public List<MO> list(){
        return testMODao.findList(new MO());
    }
}
