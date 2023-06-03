package org.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class TransactionConfig {
  @Bean
  public DataSourceTransactionManager transactionManager(DataSource datasource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(datasource);
    return dataSourceTransactionManager;
  }
  // @Bean
  // public DataSourceTransactionManager dataSourceTransactionManager(DataSource datasource) {
  //   DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(datasource);
  //   return dataSourceTransactionManager;
  // }
}
