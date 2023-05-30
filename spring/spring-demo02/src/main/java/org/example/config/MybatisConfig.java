package org.example.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Mybatis配置类*/
@Configuration
//此时就不需要MapperScannerConfigurer了
@MapperScan("org.example.mapper")
public class MybatisConfig {
  // 定义bean：SqlSessionFactoryBean，用于产生SqlSessionFactory对象
  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) {
    SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
    // 设置模型类的别名扫描
    ssfb.setTypeAliasesPackage("org.example.entity");
    // 设置数据源
    ssfb.setDataSource(dataSource);
    return ssfb;
  }

  // // 定义bean，返回MapperScannerConfigurer对象
  // @Bean
  // public MapperScannerConfigurer mapperScannerConfigurer() {
  //   MapperScannerConfigurer msc = new MapperScannerConfigurer();
  //   msc.setBasePackage("com.bighorn.dao");
  //   return msc;
  // }
}