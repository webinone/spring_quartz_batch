package com.example.spring_quartz_batch.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class AdjustmentDataBaseConfig {

  @Bean
  @ConfigurationProperties(prefix = "datasource.adjustment.hikari")
  public HikariConfig schedulerHikariConfig() {
    return new HikariConfig();
  }

  @Primary
  @Bean(name = "adjustmentDataSource")
  @ConfigurationProperties(prefix = "datasource.adjustment")
  @QuartzDataSource
  public DataSource adjustmentDataSource() {
    return new HikariDataSource(schedulerHikariConfig());
  }

}
