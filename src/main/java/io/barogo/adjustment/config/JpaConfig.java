package io.barogo.adjustment.config;

import java.util.HashMap;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages = "io.barogo.adjustment.persistence.adjustment.repository",
    entityManagerFactoryRef = "adjustmentEntityManager",
    transactionManagerRef = "adjustmentTransactionManager"
)
public class JpaConfig {

  private final Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean adjustmentEntityManager(@Qualifier("adjustmentDataSource") DataSource adjustmentDataSource) {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(adjustmentDataSource);

    //Entity 패키지 경로
    em.setPackagesToScan(new String[] { "io.barogo.adjustment.model.entity.adjustment.jpa" });

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    //Hibernate 설정
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
    em.setJpaPropertyMap(properties);
    return em;
  }

  @Bean
  public PlatformTransactionManager adjustmentTransactionManager(@Qualifier("adjustmentDataSource") DataSource adjustmentDataSource) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(adjustmentEntityManager(adjustmentDataSource).getObject());
    return transactionManager;
  }
}
