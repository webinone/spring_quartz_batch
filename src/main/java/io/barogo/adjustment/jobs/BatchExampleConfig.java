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
  @StepScope
  public MyBatisCursorItemReader<BatchExampleReader> batchExampleReader() {
    MyBatisCursorItemReader<BatchExampleReader> myBatisCursorItemReader = new MyBatisCursorItemReader();
    try {
      myBatisCursorItemReader.setQueryId("io.barogo.adjustment.persistence.adjustment.mapper.BatchExampleReaderMapper.readAll");
      //查询参数,这里不需要,如果有需要,可直接指定用户名称或密码不符合规则的user
      myBatisCursorItemReader.setSqlSessionFactory(adjustmentSqlSessionFactory);
//            Map<String, Object> parameterValues = new HashMap<String, Object>();
//            parameterValues.put("id", 3);
//            myBatisCursorItemReader.setParameterValues(parameterValues);
//            myBatisCursorItemReader.open(new ExecutionContext());
//            parameterValues.put("username",null);
//            parameterValues.put("password",null);
    } catch (Exception e) {
//            System.out.println("the lost user id is: ");
      e.printStackTrace();
    }
    return myBatisCursorItemReader;
  }

  //被 step1 使用
  @Bean
  @StepScope
  public MyBatisBatchItemWriter<BatchExampleWriter> batchExampleWriter() {
    MyBatisBatchItemWriter<BatchExampleWriter> myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
    myBatisBatchItemWriter.setStatementId("io.barogo.adjustment.persistence.adjustment.mapper.BatchExampleWriterMapper.createBatchExampleWriter");
    myBatisBatchItemWriter.setSqlSessionFactory(adjustmentSqlSessionFactory);
    return myBatisBatchItemWriter;
  }

  //被 step1 使用
  @Bean
  @StepScope
  public BatchExampleProcessor batchExampleProcessor() {
    return new BatchExampleProcessor();
  }

  @Bean
  public Job batchExampleJob() {
//    return jobBuilderFactory.get("batchExampleJob")
////        .incrementer(new RunIdIncrementer())
////          .listener(listener)
//          .flow(step1)
//          .end()
//          .build();
    return jobBuilderFactory.get("batchExampleJob")
        .start(batchExampleStep()).build();
  }

  //TODO 配置可以一次操作的数据量 chunkSize ,可以从配置文件读入
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
}
