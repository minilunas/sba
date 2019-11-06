package com.ibm.trains.config;

import com.ibm.trains.job.PayJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(PayJob.class).withIdentity("payjob").storeDurably().build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */2 * * * ?");
        return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
                .withIdentity("payjob")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
