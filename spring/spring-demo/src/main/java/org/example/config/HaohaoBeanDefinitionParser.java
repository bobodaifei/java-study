package org.example.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

public class HaohaoBeanDefinitionParser implements BeanDefinitionParser {

  @Override
  @Nullable
  public BeanDefinition parse(Element element, ParserContext parserContext) {
    // 执行逻辑，注入一个beanPostProcessor
    BeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setBeanClassName("org.example.processor.HaohaoBeanPostProcessor");
    parserContext.getRegistry().registerBeanDefinition("haohaoBeanPostProcessor", beanDefinition);
    return beanDefinition;
  }

}
