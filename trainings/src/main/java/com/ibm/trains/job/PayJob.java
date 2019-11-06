package com.ibm.trains.job;

import com.ibm.trains.service.TrainingsService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class PayJob extends QuartzJobBean {
    @Autowired
    TrainingsService trainingsService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("付款任务开始");
        trainingsService.execuPay();
        System.out.println("付款任务结束");
    }
}
