package com.bobo.cache1.testcache;

import com.bobo.cache1.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class A {

    @Autowired
    CodeUtils codeUtils;

    @GetMapping("/cache")
    //存+取
//    @Cacheable(value = "a", key = "#id")
    //只存
    @CachePut(value = "a", key = "#id")
    public String a(Integer id) {
        return id + "" + Math.random();
    }

    @GetMapping("/check")
    public String check(Integer id) {
        return codeUtils.geta(id);
    }
}
