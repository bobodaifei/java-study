package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // 标注当前类是一个配置类（替代配置文件）

// <context:component-scan base-package="org.example"></context:component-scan>
@ComponentScan(basePackages = { "org.example" })

// <context:property-placeholder location="classpath:db.properties" />
// @PropertySource({ "classpath:db.properties" })

// @Import(DataSourceConfig.class)

//开启aop
@EnableAspectJAutoProxy
//事务的自动代理
@EnableTransactionManagement

public class SpringConfig {

}
