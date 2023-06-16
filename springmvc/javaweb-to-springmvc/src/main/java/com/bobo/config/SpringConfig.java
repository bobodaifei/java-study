package com.bobo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.bobo.dao","com.bobo.service","com.bobo.advice"})
//开启aop
@EnableAspectJAutoProxy
public class SpringConfig {
}
