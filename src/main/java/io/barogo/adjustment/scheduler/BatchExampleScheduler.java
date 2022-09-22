package io.barogo.adjustment.scheduler;

import io.barogo.adjustment.common.log.CommonLog;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchExampleScheduler extends QuartzJobBean {

  private final Job batchExampleJob;
  private final JobLauncher jobLauncher;
  private final CommonLog commonLog;
  private final Environment environment;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    if (!Arrays.asList(environment.getActiveProfiles()).contains("localhost")) return;
    JobParameters jobParameters = new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters();

    try {
      jobLauncher.run(batchExampleJob, jobParameters);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
