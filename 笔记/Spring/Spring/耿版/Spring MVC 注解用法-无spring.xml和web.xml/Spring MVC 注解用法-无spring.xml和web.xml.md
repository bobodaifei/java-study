

- 用注解演示springmvc用法，不用任何xml

- 在Spring MVC中，我们可以通过注解的方式实现零XML配置。以下是一个简单的Spring MVC应用示例，它不使用任何XML配置文件：
1. 创建Maven项目并添加依赖

在`pom.xml`文件中添加以下依赖：

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.10</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.3</version>
      <scope>provided</scope>
    </dependency>
</dependencies>
```


1. 创建Java配置类

创建一个名为`AppConfig`的Java配置类，用`@Configuration`和`@EnableWebMvc`注解进行标注，同时实现`WebMvcConfigurer`接口：

```java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    //    添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CustomInterceptor());
    }
//    配置静态资源处理：
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 //       registry.addResourceHandler("/resources/**").addResourceLocations("/static/");
    }
//    配置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true);
    }

}
```


1. 创建Web应用初始化类

创建一个名为`AppInitializer`的类，继承`AbstractAnnotationConfigDispatcherServletInitializer`类：

```java

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```


1. 创建控制器

创建一个名为`HelloController`的控制器类，并使用`@Controller`注解标注：

```java

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello Spring MVC!");
        return "hello";
    }
}
```


1. 创建视图

在`src/main/webapp/WEB-INF/views`目录下，创建一个名为`hello.jsp`的视图文件：

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" isErrorPage="true"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello Spring MVC</title>
</head>
<body>
    <h1>${message}</h1>
</body>
</html>
```


1. 运行应用

部署并运行应用，访问`http://localhost:8080/hello`，你将看到"Hello Spring MVC!"的输出。

Person: 
- 将上面的程序用内置容器启动

ChatGPT: 
- 要使用内置容器（例如Tomcat）启动Spring MVC应用程序，您需要添加内置容器的依赖项并创建一个启动类。以下是如何将上述Spring MVC示例应用程序与内置的Tomcat容器一起运行：
1. 添加内置Tomcat依赖项

在`pom.xml`文件中添加以下依赖项：

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.5.5</version>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <version>10.0.12</version>
</dependency>
```


1. 修改Java配置类

将`AppConfig`类更改为以下内容：

```java

import org.springframework.context.annotation
```

Person: 
- 不用springboot

ChatGPT: 
- 如果不使用Spring Boot，可以手动添加内嵌的Tomcat依赖并创建一个启动类。以下是如何将上述Spring MVC示例应用程序与内嵌的Tomcat容器一起运行：
1. 添加内嵌Tomcat依赖项

在`pom.xml`文件中添加以下依赖项：

```xml

<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-core</artifactId>
    <version>10.0.12</version>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <version>10.0.12</version>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-el</artifactId>
    <version>10.0.12</version>
</dependency>
```


1. 创建启动类

创建一个名为`Application`的启动类，用于启动内嵌的Tomcat服务器：

```java

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ServletDef;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletException;
import java.io.File;

public class Application {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);
        tomcat.setBaseDir(createTempDir().getAbsolutePath());

        Context ctx = tomcat.addWebapp("", createTempDir().getAbsolutePath());

        ServletDef servletDef = new ServletDef();
        servletDef.setServletClass(DispatcherServlet.class.getName());
        servletDef.addInitParameter(AbstractAnnotationConfigDispatcherServletInitializer.CONFIG_LOCATION_PARAM, AppConfig.class.getName());
        ctx.addServletDef("springmvc", servletDef);
        ctx.addServletMappingDecoded("/", "springmvc");

        tomcat.start();
        System.out.println("Tomcat started on port(s): " + PORT + "...");
        tomcat.getServer().await();
    }

    private static File createTempDir() {
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "tomcat-" + PORT);
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        return tempDir;
    }
}
```


1. 运行应用程序

运行`Application`类的`main`方法。访问`http://localhost:8080/hello`，你将看到"Hello Spring MVC!"的输出。



- 用WebMvcConfigurer都能 配置什么

- `WebMvcConfigurer`接口允许我们自定义Spring MVC的配置，如添加拦截器、设置路径匹配规则、配置跨域等。以下是一些`WebMvcConfigurer`接口的常见用法：
1. 配置视图解析器：

```java

@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("/WEB-INF/views/", ".jsp");
}
```


1. 添加拦截器：

```java

@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new CustomInterceptor());
}
```


1. 配置静态资源处理：

```java

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/static/");
}
```


1. 配置跨域：

```java

@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
}
```


1. 配置路径匹配规则：

```java

@Override
public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.setUseSuffixPatternMatch(false);
    configurer.setUseTrailingSlashMatch(false);
}
```


1. 配置异常处理：

您可以使用`WebMvcConfigurer`的`configureHandlerExceptionResolvers`方法来配置异常处理。但是，更推荐使用`@ControllerAdvice`与`@ExceptionHandler`来进行全局异常处理。这里是一个使用`@ControllerAdvice`的例子：

```java

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), "An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, status);
    }
}
```



在这个例子中，我们为`CustomException`创建了一个特定的处理器，并为其他所有异常创建了一个通用处理器。
