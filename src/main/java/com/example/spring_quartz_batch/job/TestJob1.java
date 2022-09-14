package com.example.spring_quartz_batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestJob1 extends QuartzJobBean {

  private final Job alphabetJob;
  private final JobLauncher jobLauncher;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    log.info(">>>> TestJob1 ");

    JobParameters jobParameters = new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters();
//    JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

    try {
      jobLauncher.run(alphabetJob, jobParameters);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
