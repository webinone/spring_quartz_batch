package com.example.spring_quartz_batch.config;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.example.spring_quartz_batch.job.TestJob1;
import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {


  @Bean
  public JobDetail testJobDetail1() {
    return JobBuilder.newJob().ofType(TestJob1.class)
        .storeDurably()
        .withIdentity("test-job1")
        .build();
  }

  @Bean
  public Trigger testJob1Trigger (@Qualifier("testJobDetail1") JobDetail job) {
    return newTrigger()
        .withIdentity("test-job1-trigger")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(3)
            .repeatForever())
        .forJob(job)
        .build();
  }
}
