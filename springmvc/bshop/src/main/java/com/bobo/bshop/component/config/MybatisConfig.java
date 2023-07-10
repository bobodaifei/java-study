package com.bobo.bshop.component.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/*Mybatis配置类*/
@Configuration
//此时就不需要MapperScannerConfigurer了
@MapperScan("com.bobo.bshop.mapper")
public class MybatisConfig {

  @Autowired
  PageInterceptor pageInterceptor;

  // 定义bean：SqlSessionFactoryBean，用于产生SqlSessionFactory对象
  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) throws IOException {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
    // 设置模型类的别名扫描
    sessionFactory.setTypeAliasesPackage("com.bobo.bshop.entity");
    // 设置数据源
    sessionFactory.setDataSource(dataSource);
    //分页插件
    sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
    return sessionFactory;
  }

  @Bean
  public PageInterceptor pageInterceptor() {
    PageInterceptor interceptor = new PageInterceptor();
    Properties properties = new Properties();
    properties.setProperty("helperDialect", "mysql");
    interceptor.setProperties(properties);
    return interceptor;
  }

}