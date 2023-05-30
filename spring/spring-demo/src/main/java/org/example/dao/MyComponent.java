package org.example.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//类上使用
@Target(ElementType.TYPE)
//存活范围(运行时可见)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyComponent {

  String value();

}
