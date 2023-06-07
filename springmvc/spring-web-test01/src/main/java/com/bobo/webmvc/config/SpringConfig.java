package com.bobo.webmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.bobo.webmvc.service","com.bobo.webmvc.dao"})
public class SpringConfig {

}
