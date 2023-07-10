package com.bobo.sso.component.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource({"classpath:jedis.properties"})
public class JedisConfig {

  @Value("${jedis.host}")
  private String host;

  @Value("${jedis.port}")
  private int port;

  @Value("${jedis.timeout}")
  private int timeout;

  @Value("${jedis.maxActive}")
  private int maxActive;

  @Value("${jedis.maxIdle}")
  private int maxIdle;

  @Value("${jedis.minIdle}")
  private int minIdle;

  @Value("${jedis.maxWaitMillis}")
  private long maxWaitMillis;

  @Bean
  public JedisPool jedisPool() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    jedisPoolConfig.setMaxTotal(maxActive);
    jedisPoolConfig.setMinIdle(minIdle);
    return new JedisPool(jedisPoolConfig, host, port, timeout, null);
  }




}
