//package com.bobo.init;
//
//import com.bobo.config.SpringConfig;
//import com.bobo.config.SpringMVCConfig;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//public class MyAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//  /**
//   * 获取根容器的配置(类似于之前监听器配置spring的配置文件)
//   *    <listener>
//   *      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//   *     </listener>
//   *
//   *<context-param>
//   *     <param-name>contextConfigLocation</param-name>
//   *     <param-value>classpath:spring/applicationContext-*.xml</param-value> 。
//   * </context-param>
//   *
//   *
//   *
//   **/
//
//  @Override
//  protected Class<?>[] getRootConfigClasses() {
//    return new Class[]{SpringConfig.class};
//  }
//
//  /**
//   * 获取web容器的配置类(类似于springmvc的配置文件)(前端控制器)子容器
//   * @return
//   */
//  @Override
//  protected Class<?>[] getServletConfigClasses() {
//    return new Class[]{SpringMVCConfig.class};
//  }
//
//  /**
//   * 获取DispatcherServlet的映射信息
//   *
//   * {"/"}:拦截所有请求包括静态资源(xx.js,xx.png),不包括*.jsp
//   * {"/*"}:拦截所有请求，包括*.jsp,jsp页面是tomcat的jsp引擎解析的
//   * @return
//   */
//  @Override
//  protected String[] getServletMappings() {
//    return new String[]{"/"};
//  }
//}
