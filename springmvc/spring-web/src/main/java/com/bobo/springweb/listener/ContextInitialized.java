package com.bobo.springweb.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextInitialized implements ServletContextListener {

    private String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        //获取contextConfigLocation的值
        String contextConfigLocation = servletContext.getInitParameter(CONTEXT_CONFIG_LOCATION);
        //获取配置文件名称
        contextConfigLocation = contextConfigLocation.substring("classpath:".length());
        //创建Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextConfigLocation);
        //将容器存储到servletContext
        servletContext.setAttribute("applicationContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
