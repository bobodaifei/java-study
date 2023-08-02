package com.bobo.cache1.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {

    @Cacheable(value = "a", key = "#id")
    public String geta(Integer id) {
        return null;
    }

}
