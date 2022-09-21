package io.barogo.adjustment.scheduler;

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
public class BatchExampleScheduler extends QuartzJobBean {

  private final Job batchExampleJob;
  private final JobLauncher jobLauncher;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    log.info(">>>> BatchExampleScheduler ! ");

    JobParameters jobParameters = new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters();

    try {
      jobLauncher.run(batchExampleJob, jobParameters);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
