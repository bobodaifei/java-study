package com.example.config;

import com.example.converter.BoboMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false, value = "webMvcConfigurer")
public class WebMvcConfigurerConfig implements WebMvcConfigurer {

  @Autowired
  private BoboMessageConverter boboMessageConverter;

  //开启矩阵参数解析方法1
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    UrlPathHelper urlPathHelper = new UrlPathHelper();
    //不移除分号后的内容
    urlPathHelper.setRemoveSemicolonContent(false);
    configurer.setUrlPathHelper(urlPathHelper);
  }

//  //开启矩阵参数解析方法2
//  @Bean
//  public WebMvcConfigurer webMvcConfigurer(){
//    return new WebMvcConfigurer() {
//      @Override
//      public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//      }
//    };
//  }

  //自定义 Converter 类型转换器
//  @Override
//  public void addFormatters(FormatterRegistry registry) {
//    registry.addConverter(new Converter<String, User>() {
//
//      @Override
//      public User convert(String source) {
//        // 啊猫,3
//        if (!StringUtils.isEmpty(source)) {
//          User pet = new User();
//          String[] split = source.split(",");
//          pet.setName(split[0]);
//          pet.setAge(Integer.parseInt(split[1]));
//          return pet;
//        }
//        return null;
//      }
//    });
//  }

  //扩展自定义MessageConverter 消息转换器
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(boboMessageConverter);
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    Map<String, MediaType> map = new HashMap<>();
    map.put("json", MediaType.APPLICATION_JSON);
    map.put("xml", MediaType.APPLICATION_XML);
    map.put("bobo", MediaType.parseMediaType("application/x-bobo"));
    //基于参数的内容协商
    ParameterContentNegotiationStrategy strategy = new ParameterContentNegotiationStrategy(map);
    //基于请求头的内容协商(防止请求头的内容协商失效)
    HeaderContentNegotiationStrategy strategy1 = new HeaderContentNegotiationStrategy();
    configurer.strategies(Arrays.asList(strategy, strategy1));

  }
}
