package io.barogo.adjustment.config;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


import io.barogo.adjustment.scheduler.BatchExampleScheduler;
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

  // batch example
  @Bean
  public JobDetail batchExampleJobDetail() {
    return JobBuilder.newJob().ofType(BatchExampleScheduler.class)
        .storeDurably()
        .withIdentity("batch_example-job")
        .build();
  }

  @Bean
  public Trigger batchExampleJobTrigger (@Qualifier("batchExampleJobDetail") JobDetail job) {
    return newTrigger()
        .withIdentity("batch_example-job")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(10)
            .repeatForever())
        .forJob(job)
        .build();
  }
}
