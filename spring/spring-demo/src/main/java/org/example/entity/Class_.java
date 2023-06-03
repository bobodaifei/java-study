package org.example.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class_ implements BeanPostProcessor {
  private String class_no;
  private String class_name;
  private String leader_teacher;
  private List<Student> students;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("55555555555555555");
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("55555555556666666666666666");
    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
