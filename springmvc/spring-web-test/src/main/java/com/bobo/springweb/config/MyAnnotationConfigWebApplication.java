package com.bobo.springweb.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyAnnotationConfigWebApplication extends AnnotationConfigWebApplicationContext {
    public MyAnnotationConfigWebApplication() {
        super();
        this.register(SpringConfig.class);
    }
}
