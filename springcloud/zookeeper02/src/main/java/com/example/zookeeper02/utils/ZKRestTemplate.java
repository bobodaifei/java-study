package com.example.zookeeper02.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ZKRestTemplate {

  @Autowired
  private RestTemplate restTemplate;

  public void setMap(Map<String, List<String>> map) {
    this.map = map;
    System.out.println(JSON.toJSONString(map));
  }

  private Map<String, List<String>> map = new HashMap<>();

  public String getJson(String url) {
    int firstIndex = url.indexOf("/");
    int secondIndex = url.indexOf("/", firstIndex + 1);
    int thirdIndex = url.indexOf("/", secondIndex + 1);
    String result = url.substring(secondIndex + 1, thirdIndex);
    String result1 = url.substring(thirdIndex);
    List<String> list = map.get(result);
    String realUrl = list.get(0);
    ResponseEntity<String> entity = restTemplate.getForEntity("http://"+realUrl + result1, String.class);
    return entity.getBody();
  }
}
