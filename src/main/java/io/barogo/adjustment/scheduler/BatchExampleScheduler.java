package io.barogo.adjustment.scheduler;

import io.barogo.adjustment.common.log.CommonLog;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchExampleScheduler extends QuartzJobBean {

  private final Job batchExampleJob;
  private final JobLauncher jobLauncher;
  private final CommonLog commonLog;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    commonLog.info(">>>> executeInternal !!");

    JobParameters jobParameters = new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters();

    try {
      jobLauncher.run(batchExampleJob, jobParameters);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
