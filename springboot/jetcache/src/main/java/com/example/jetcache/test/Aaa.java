package com.example.jetcache.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Aaa {

//  @CreateCache(area = "bmb", name = "jetcache_", expire = 3600, cacheType = CacheType.LOCAL)
//  private Cache<String, String> jetCache2;
//
//  @CreateCache(name = "jetcache_", expire = 3600)
//  private Cache<String, String> jetCache;
//
//  @CreateCache(name = "jetcache_", expire = 3600)
//  private Cache<String, String> jetCache1;


  @Autowired
  aaService aa;

  @GetMapping("cache")
  public String cacheCode(String tele) {
    aa.sendSmsCode(tele);
    return "true";
  }

  @GetMapping("check")
  public boolean checkCode(String tele, String code) {
    return aa.checkSmsCode(tele, code);
  }

//  @GetMapping("cache1")
//  public String cacheCode1(String tele) {
//    jetCache.put(tele, tele + 222);
//    return tele + 222;
//  }
//
//  @GetMapping("check1")
//  public String checkCode1(String tele) {
//    return jetCache.get(tele);
//  }


}
