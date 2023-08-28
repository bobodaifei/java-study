package org.example.test;


import org.example.config.SpringConfig;
import org.example.service.ClassService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {
  public static void main(String[] args) throws InterruptedException {
    // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
//    ClassService object = (ClassService)applicationContext.getBean("classService");
    ClassService object = (ClassService)applicationContext.getBean(ClassService.class);
    object.show();

    
  }
}
