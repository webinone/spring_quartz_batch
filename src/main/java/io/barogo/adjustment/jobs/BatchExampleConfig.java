package io.barogo.adjustment.jobs;

import io.barogo.adjustment.jobs.processor.BatchExampleProcessor;
import io.barogo.adjustment.listener.JobCompletionNotificationListener;
import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleReader;
import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleWriter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchExampleConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final SqlSessionFactory adjustmentSqlSessionFactory;

  @Bean
  public Job batchExampleJob() {
    return jobBuilderFactory.get("batchExampleJob")
        .start(batchExampleStep()).build();
  }

  @Bean
  @JobScope
  public Step batchExampleStep() {
    return stepBuilderFactory.get("batchExampleStep")
        .<BatchExampleReader, BatchExampleWriter>chunk(10)
        .reader(batchExampleReader())
        .processor(batchExampleProcessor())
        .writer(batchExampleWriter())
        .build();
  }

  @Bean
  @StepScope
  public MyBatisPagingItemReader<BatchExampleReader> batchExampleReader() {

    return new MyBatisPagingItemReaderBuilder<BatchExampleReader>()
        .sqlSessionFactory(adjustmentSqlSessionFactory)
        .queryId("io.barogo.adjustment.persistence.adjustment.mapper.BatchExampleReaderMapper.readPaging")
        .build();
  }

  @Bean
  @StepScope
  public MyBatisBatchItemWriter<BatchExampleWriter> batchExampleWriter() {
    MyBatisBatchItemWriter<BatchExampleWriter> myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
    myBatisBatchItemWriter.setStatementId("io.barogo.adjustment.persistence.adjustment.mapper.BatchExampleWriterMapper.createBatchExampleWriter");
    myBatisBatchItemWriter.setSqlSessionFactory(adjustmentSqlSessionFactory);
    return myBatisBatchItemWriter;
  }

  @Bean
  @StepScope
  public BatchExampleProcessor batchExampleProcessor() {
    return new BatchExampleProcessor();
  }
}
