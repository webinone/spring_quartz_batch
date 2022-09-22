package io.barogo.adjustment.listener;

import io.barogo.adjustment.common.log.CommonLog;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private final CommonLog commonLog;

  @Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {

      String jobName = jobExecution.getJobInstance().getJobName();
      Long jobId = jobExecution.getJobId();

      commonLog.info("jobName: " + jobName + " - jobId" + jobId + "!!! JOB FINISHED! Time to verify the results");
    }
  }
}
