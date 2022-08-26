package com.example.spring_quartz_batch.config;

import static org.quartz.JobBuilder.newJob;

import com.example.spring_quartz_batch.job.TestJob1;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

  private final Scheduler scheduler;

  @PostConstruct
  public void start(){
    JobDetail jobDetail = buildJobDetail(TestJob1.class, new HashMap());

    try{
      scheduler.scheduleJob(jobDetail, buildJobTrigger("0/5 * * * * ?"));
    } catch(SchedulerException e){
      e.printStackTrace();
    }
  }

  public Trigger buildJobTrigger(String scheduleExp){
    return TriggerBuilder.newTrigger()
        .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
  }

  public JobDetail buildJobDetail(Class job, Map params){
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.putAll(params);

    return newJob(job).usingJobData(jobDataMap).build();
  }
}
