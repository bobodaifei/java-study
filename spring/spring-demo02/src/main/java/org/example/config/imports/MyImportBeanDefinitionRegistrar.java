package org.example.config.imports;

import org.example.config.DataSourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
      BeanNameGenerator bGenerator) {
    BeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setBeanClassName(DataSourceConfig.class.getName());
    registry.registerBeanDefinition("DataSourceConfig", beanDefinition);
  }

}
