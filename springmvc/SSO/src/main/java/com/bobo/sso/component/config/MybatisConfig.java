package com.bobo.sso.component.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/*Mybatis配置类*/
@Configuration
//此时就不需要MapperScannerConfigurer了
@MapperScan("com.bobo.sso.mapper")
public class MybatisConfig {


  // 定义bean：SqlSessionFactoryBean，用于产生SqlSessionFactory对象
  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) throws IOException {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
    // 设置模型类的别名扫描
    sessionFactory.setTypeAliasesPackage("com.bobo.sso.entity");
    // 设置数据源
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
    return sessionFactory;
  }

}