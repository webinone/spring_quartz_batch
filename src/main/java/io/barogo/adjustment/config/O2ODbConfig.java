package io.barogo.adjustment.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(value = "io.barogo.adjustment.persistence.o2o.mapper", sqlSessionFactoryRef = "o2oSqlSessionFactory")
public class O2ODbConfig {

  @Bean
  @ConfigurationProperties(prefix = "datasource.o2o.hikari")
  public HikariConfig o2oHikariConfig() {
    return new HikariConfig();
  }

  @Bean(name = "o2oDataSource")
  @ConfigurationProperties(prefix = "datasource.o2o")
  public DataSource o2oDataSource() {
    return new HikariDataSource(o2oHikariConfig());
  }


  // mybatis - o2o
  //------------------------------------------------------------------------------------
  @Bean(name = "o2oSqlSessionFactory")
  public SqlSessionFactory o2oSqlSessionFactory(@Qualifier("o2oDataSource") DataSource o2oDataSource, ApplicationContext applicationContext) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(o2oDataSource);
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/o2o/*.xml"));
    Resource mybatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml");
    sqlSessionFactoryBean.setConfigLocation(mybatisConfig);

    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate o2oSqlSessionTemplate(@Qualifier("o2oSqlSessionFactory") SqlSessionFactory o2oSqlSessionFactory) {
    return new SqlSessionTemplate(o2oSqlSessionFactory);
  }
  //------------------------------------------------------------------------------------

}
