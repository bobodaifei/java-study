package com.bobo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionConfig {
  @Bean
  public DataSourceTransactionManager transactionManager(DataSource datasource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(datasource);
    return dataSourceTransactionManager;
  }

}
