package io.barogo.adjustment.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@EnableBatchProcessing
@Configuration
public class TestJobConfiguration {

//  @Bean
//  public JobLauncherTestUtils jobLauncherTestUtils() {
//    return new JobLauncherTestUtils();
//  }
}
