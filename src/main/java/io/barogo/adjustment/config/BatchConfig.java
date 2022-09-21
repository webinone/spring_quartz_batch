package io.barogo.adjustment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

//  private final JobBuilderFactory jobBuilderFactory;
//  private final StepBuilderFactory stepBuilderFactory;
//
//  /**
//   * alphabet ......
//   * */
//  @Bean(name = "alphabetJob")
//  public Job job() {
//    return jobBuilderFactory.get("alphabetJob")
//        .incrementer(new RunIdIncrementer())
//        .start(stepAlphabet()).build();
//  }
//
//  @Bean
//  public Step stepAlphabet() {
//    return stepBuilderFactory.get("stepAlphabet")
//        .<String, String> chunk(5)
//        .reader(new AlphabetReader())
//        .processor(new UpperCaseProcessor())
//        .writer(new AlphabetWriter())
//        .build();
//  }
//
//
//  //TODO 可以设定查询参数,使用com.example.batchprocessing.mapper.UserMapper.getUserSelective
//  //被 step1 使用
//  @Bean
//  @StepScope
//  public MyBatisCursorItemReader<BatchExampleDto> batchExampleReader(@Qualifier("adjustmentSqlSessionFactory") SqlSessionFactory adjustmentSqlSessionFactory) {
//    MyBatisCursorItemReader<BatchExampleDto> myBatisCursorItemReader = new MyBatisCursorItemReader();
//    try {
//      myBatisCursorItemReader.setQueryId("com.example.batchprocessing.mapper.UserMapper.getUserList");
//      //查询参数,这里不需要,如果有需要,可直接指定用户名称或密码不符合规则的user
//      myBatisCursorItemReader.setSqlSessionFactory(adjustmentSqlSessionFactory);
////            Map<String, Object> parameterValues = new HashMap<String, Object>();
////            parameterValues.put("id", 3);
////            myBatisCursorItemReader.setParameterValues(parameterValues);
////            myBatisCursorItemReader.open(new ExecutionContext());
////            parameterValues.put("username",null);
////            parameterValues.put("password",null);
//    } catch (Exception e) {
////            System.out.println("the lost user id is: ");
//      e.printStackTrace();
//    }
//    return myBatisCursorItemReader;
//  }
//
//  //被 step1 使用
//  @Bean
//  @StepScope
//  public MyBatisBatchItemWriter<BatchExampleDto> batchExampleWriter(@Qualifier("backupSqlSessionFactory") SqlSessionFactory backupSqlSessionFactory) {
//    MyBatisBatchItemWriter<User> myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
//    myBatisBatchItemWriter.setStatementId("com.example.batchprocessing.mapper.UserCpMapper.addOneUser");
//    myBatisBatchItemWriter.setSqlSessionFactory(backupSqlSessionFactory);
//    return myBatisBatchItemWriter;
//  }
//
//  //被 step1 使用
//  @Bean
//  public UserItemProcessor userStandardInfoProcessor() {
//    return new UserItemProcessor();
//  }
//
//  @Bean
//  public Job thisJob(JobCompletionNotificationListener listener, Step step1) {
//    return jobBuilderFactory.get("thisJob")
//        .incrementer(new RunIdIncrementer())
//        .listener(listener)
//        .flow(step1)
//        .end()
//        .build();
//  }
//
//  //TODO 配置可以一次操作的数据量 chunkSize ,可以从配置文件读入
//  @Bean
//  public Step step1(MyBatisBatchItemWriter<User> writer,@Qualifier("backupSqlSessionFactory") SqlSessionFactory backupSqlSessionFactory) {
//    return stepBuilderFactory.get("step1")
//        .<User, User>chunk(10)
//        .reader(batchExampleReader(backupSqlSessionFactory))
//        .processor(userStandardInfoProcessor())
//        .writer(userMybatisWriter(backupSqlSessionFactory))
//        .build();
//  }


}
