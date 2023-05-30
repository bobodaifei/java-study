package org.example.config;

import org.example.config.imports.MyImportBeanDefinitionRegistrar;
import org.example.config.imports.MylmportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration // 标注当前类是一个配置类（替代配置文件）

// <context:component-scan base-package="org.example"></context:component-scan>
@ComponentScan(basePackages = { "org.example" })

// <context:property-placeholder location="classpath:db.properties" />
// @PropertySource({ "classpath:db.properties" })

// @Import(DataSourceConfig.class)
// @Import({ MylmportSelector.class})
@Import({ MyImportBeanDefinitionRegistrar.class })

public class SpringConfig {

}
