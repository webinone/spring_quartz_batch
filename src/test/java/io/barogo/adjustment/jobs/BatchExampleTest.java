package io.barogo.adjustment.jobs;

import static org.assertj.core.api.Assertions.assertThat;

import io.barogo.adjustment.config.AdjustmentDbConfig;
import io.barogo.adjustment.config.O2ODbConfig;
import io.barogo.adjustment.config.TestJobConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestJobConfiguration.class, BatchExampleConfig.class, O2ODbConfig.class, AdjustmentDbConfig.class})
@SpringBatchTest
public class BatchExampleTest {

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  @DisplayName("실행이 잘 되는지만 확인")
  public void success() throws Exception {
    // 실행이 잘되는지 확인

    // given
    JobParameters jobParameters = new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters();

    // when
    JobExecution execution = jobLauncherTestUtils.launchJob(jobParameters);

    // thena
    assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
  }




}
