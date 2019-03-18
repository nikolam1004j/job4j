package main;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloJobTest {
    @Test
    public void testJob() throws SchedulerException, InterruptedException {
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("dummyJobName", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Joba")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .forJob(job)
                .build();

        JobDetail jobBye = JobBuilder.newJob(ByeByeJob.class)
                .withIdentity("dummyBye", "group1")
                .build();
        Trigger byebyeTrigger = TriggerBuilder.newTrigger()
                .withIdentity("ByeTrigger")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3))
                .forJob(jobBye)
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(jobBye, byebyeTrigger);

        Thread.sleep(500000);
    }
}