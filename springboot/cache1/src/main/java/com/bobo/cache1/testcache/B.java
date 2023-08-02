package com.bobo.cache1.testcache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RestController
public class B {

  @Autowired
  private MemcachedClient memcachedClient;

  @GetMapping("/cache1")
  public String a(String id) throws InterruptedException, TimeoutException, MemcachedException {
    String ss = id + "" + Math.random();
    memcachedClient.set(id, 0, ss);
    return ss;
  }

  @GetMapping("/check1")
  public String check(String id) throws InterruptedException, TimeoutException, MemcachedException {
    return memcachedClient.get(id);
  }

}
