package io.barogo.adjustment.jobs;

import io.barogo.adjustment.jobs.processor.BatchExampleProcessor;
import io.barogo.adjustment.listener.JobCompletionNotificationListener;
import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleWriterDto;
import io.barogo.adjustment.model.entity.o2o.BaromoneyHistoryOfHubDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchExampleConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final SqlSessionFactory adjustmentSqlSessionFactory;
  private final JobCompletionNotificationListener jobCompletionNotificationListener;

  @Bean
  public Job batchExampleJob (
      @Qualifier("o2oSqlSessionFactory") SqlSessionFactory o2oSqlSessionFactory
  ) {
    return jobBuilderFactory.get("batchExampleJob")
        .start(batchExampleStep(o2oSqlSessionFactory)).listener(jobCompletionNotificationListener).build();
  }

  @Bean
  @JobScope
  public Step batchExampleStep(
      @Qualifier("o2oSqlSessionFactory") SqlSessionFactory o2oSqlSessionFactory
  ) {
    return stepBuilderFactory.get("batchExampleStep")
        .<BaromoneyHistoryOfHubDTO, BatchExampleWriterDto>chunk(10)
        .reader(batchExampleReader(o2oSqlSessionFactory))
        .processor(batchExampleProcessor())
        .writer(batchExampleWriter())
        .build();
  }

  @Bean
  @StepScope
  public MyBatisPagingItemReader<BaromoneyHistoryOfHubDTO> batchExampleReader(
      @Qualifier("o2oSqlSessionFactory") SqlSessionFactory o2oSqlSessionFactory
  ) {

    return new MyBatisPagingItemReaderBuilder<BaromoneyHistoryOfHubDTO>()
        .sqlSessionFactory(o2oSqlSessionFactory)
        .queryId("io.barogo.adjustment.persistence.o2o.mapper.BaromoneyHistoryOfHubMapper.readHistory")
//        .pageSize(1)
        .build();
  }

  @Bean
  @StepScope
  public MyBatisBatchItemWriter<BatchExampleWriterDto> batchExampleWriter() {


    MyBatisBatchItemWriter<BatchExampleWriterDto> myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
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
