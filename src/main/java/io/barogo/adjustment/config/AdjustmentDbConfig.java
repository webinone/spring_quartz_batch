package io.barogo.adjustment.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class AdjustmentDbConfig {

  private final ApplicationContext applicationContext;

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


  // mybatis - adjustment
  //------------------------------------------------------------------------------------
  @Primary
  @Bean(name = "adjustmentSqlSessionFactory")
  public SqlSessionFactory adjustmentSqlSessionFactory(@Qualifier("adjustmentDataSource") DataSource adjustmentDataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(adjustmentDataSource);
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/adjustment/*.xml"));
    Resource mybatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml");
    sqlSessionFactoryBean.setConfigLocation(mybatisConfig);

    return sqlSessionFactoryBean.getObject();
  }

  @Primary
  @Bean
  public SqlSessionTemplate adjustmentSqlSessionTemplate(@Qualifier("adjustmentSqlSessionFactory") SqlSessionFactory adjustmentSqlSessionFactory) {
    return new SqlSessionTemplate(adjustmentSqlSessionFactory);
  }
  //------------------------------------------------------------------------------------

}
