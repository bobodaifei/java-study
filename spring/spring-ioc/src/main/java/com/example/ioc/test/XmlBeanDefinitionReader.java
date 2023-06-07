package com.example.ioc.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class XmlBeanDefinitionReader implements BeanDefinitionReader {
  private BeanDefinitionRegistry beanDefinitionRegistry;

  public XmlBeanDefinitionReader() {
    this.beanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
  }

  @Override
  public BeanDefinitionRegistry getRegistry() {
    return beanDefinitionRegistry;
  }

  @Override
  public void loadBeanDefinitions(String configLocation) throws Exception {
    SAXReader saxReader = new SAXReader();
    Document document = saxReader.read(configLocation);
    Element root = document.getRootElement();
    List<Element> beanElementList = root.elements("bean");
    for (Element beanElement : beanElementList) {
      BeanDefinition beanDefinition = new BeanDefinition();
      String id = null;
      String clazz = beanElement.attributeValue("class");
      if ((id = beanElement.attributeValue("id")) == null) {
        id = clazz;
      }
      beanDefinition.setBeanName(id);
      beanDefinition.setBeanClass(clazz);
      List<Element> propertiesElementList = beanElement.elements("property");
      for (Element propertyElement : propertiesElementList) {
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        beanDefinition.setProperties(name, value);
      }
      beanDefinitionRegistry.registryBeanDefinition(id, beanDefinition);
    }
  }
}
