package com.cml.framework.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @Auther: cml
 * @Date: 2018-12-12 14:53
 * @Description:
 */
public class QuartzTest {
    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();


        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity(JobKey.jobKey("test1")).build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000).repeatForever())
                .build();

        Date date = scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

        System.out.println("=====" + scheduler.getTriggerGroupNames() + "," + date);

        Thread.sleep(1000);

        System.out.println("exist==>" + scheduler.checkExists(JobKey.jobKey("test1")));

        boolean deleteJob = scheduler.deleteJob(JobKey.jobKey("test1"));
        System.out.println("deleteResult:" + deleteJob);

        if (deleteJob) {
            date = scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    /**
     * 任务类，需要为public类型的
     */
    public static class MyJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("--------------------");
            System.out.println(context.getFireTime());
            System.out.println("--------------->job");
        }
    }
}

