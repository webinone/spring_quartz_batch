package com.example.spring_quartz_batch.config;

import com.example.spring_quartz_batch.job.alphabet.AlphabetReader;
import com.example.spring_quartz_batch.job.alphabet.AlphabetWriter;
import com.example.spring_quartz_batch.job.alphabet.UpperCaseProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean(name = "alphabetJob")
  public Job job() {
    return jobBuilderFactory.get("alphabetJob")
        .incrementer(new RunIdIncrementer())
        .start(stepAlphabet()).build();
  }

  @Bean
  public Step stepAlphabet() {
    return stepBuilderFactory.get("stepAlphabet")
        .<String, String> chunk(5)
        .reader(new AlphabetReader())
        .processor(new UpperCaseProcessor())
        .writer(new AlphabetWriter())
        .build();
  }


}
