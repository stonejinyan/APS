package com.aps.util;

import com.aps.service.APSSolve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask {

    @Autowired
    APSSolve apsSolve;

    public static int flag = 1;
    //3.添加定时任务
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    /**
     * Cron表达式参数分别表示：
     *
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     * @Scheduled：除了支持灵活的参数表达式cron之外，还支持简单的延时操作，例如 fixedDelay ，fixedRate 填写相应的毫秒数即可。
     *
     * **/
    //@Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.out.println(new Date());
        if (flag==1){
            flag=0;
            apsSolve.solve();
        }
    }
}
