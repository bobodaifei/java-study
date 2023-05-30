package org.example.config.imports;

import org.example.config.DataSourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

public class MylmportSelector implements ImportSelector{

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    // AnnotationMetadata叫做注解媒体数组
    //该对象内部封装的是当前类被@Import引用的的类上的其他注解的元信息
    //比如可以获取SpringConfig上的其他注解信息
    MultiValueMap<String,Object> allAnnotationAttributes = importingClassMetadata.getAllAnnotationAttributes(
        ComponentScan.class.getName());
    System.out.println(allAnnotationAttributes);




    //返回的数组封装的是需要被注册到spring容器中的Bean的全限定名
    // return new String[]{DataSourceConfig.class.getName()};
    return new String[0];
  }
  
}
