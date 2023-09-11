package com.bobo.purview;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;

@SpringBootApplication
@MapperScan("com.bobo.purview.mapper")
public class PurviewApplication {

  public static void main(String[] args) {
    SpringApplication.run(PurviewApplication.class, args);
  }



}

class AA{
  public static void main(String[] args) {

  }
}
