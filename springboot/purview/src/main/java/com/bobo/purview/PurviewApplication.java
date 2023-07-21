package com.bobo.purview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bobo.purview.mapper")
public class PurviewApplication {

  public static void main(String[] args) {
    SpringApplication.run(PurviewApplication.class, args);
  }

}
