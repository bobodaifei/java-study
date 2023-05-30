package org.example.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class HaohaoNamespaceHandler extends NamespaceHandlerSupport{

  @Override
  public void init() {
    registerBeanDefinitionParser("annotation-driven", new HaohaoBeanDefinitionParser());
  }
  
}
