package com.mf.server.config;

import com.mf.dispatch.common.constants.Constants;
import com.mf.server.quartz.ScanProbeStatusJob;
import com.sun.javafx.binding.StringFormatter;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail scanProbeInfo(){
        return JobBuilder.newJob(ScanProbeStatusJob.class).withIdentity("scanProbeStatus").storeDurably(true).build();
    }

    /**
     * 设置扫描的机制
     * @return
     */
    @Bean
    public Trigger trigger () {
        return TriggerBuilder.newTrigger()
                .forJob(scanProbeInfo())
                .withIdentity("scanProbeStatus")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
                .build();
    }
}
