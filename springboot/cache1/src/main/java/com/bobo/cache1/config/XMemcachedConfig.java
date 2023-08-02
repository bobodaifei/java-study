package com.bobo.cache1.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class XMemcachedConfig {

  @Bean
  public MemcachedClient memcachedClient() throws IOException {
    XMemcachedClientBuilder builder = new XMemcachedClientBuilder("127.0.0.1:11211");
    return builder.build();
  }


}
