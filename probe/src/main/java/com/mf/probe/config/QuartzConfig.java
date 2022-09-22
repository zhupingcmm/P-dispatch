package com.mf.probe.config;


import com.mf.dispatch.common.constants.Constants;
import com.mf.probe.quartz.HeartbeatJob;
import com.sun.javafx.binding.StringFormatter;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail heartbeat () {
        return JobBuilder.newJob(HeartbeatJob.class).withIdentity("heartbeat").storeDurably(true).build();
    }

    /**
     * 心跳的设置
     */
    @Bean
    public Trigger trigger () {
        return TriggerBuilder.newTrigger()
                .forJob(heartbeat())
                .withIdentity("heartbeat")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }

}
