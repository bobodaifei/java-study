package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;


public class MyDataSourceConfig {
  /**
   *  @ConfigurationProperties 注解让这个Bean与配置文件进行绑定
   *  具体绑定到前缀为spring.datasource下的属性
   *  简单说，spring.datasource配了啥，他的setter就跟着配啥
   */
  @ConfigurationProperties("spring.datasource")
  @Bean
  public DataSource dataSource() throws SQLException {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setFilters("stat");
    return dataSource;
  }

  //配置Druid的监控页功能
  @Bean
  public ServletRegistrationBean statViewServlet(){
    StatViewServlet servlet = new StatViewServlet();
    ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(servlet, "/druid/*");
    return servletRegistrationBean;
  }
}
