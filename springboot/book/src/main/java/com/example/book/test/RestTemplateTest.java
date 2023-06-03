package com.example.book.test;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.book.entity.ResponseBean;

public class RestTemplateTest {

  private RestTemplate restTemplate = new RestTemplate();

  public static void main(String[] args) {
    RestTemplateTest restTemplateTest = new RestTemplateTest();
    restTemplateTest.simpleTest09();
  }

  public void simpleTest09() {
    String url = "http://localhost:8080/user/login01";

    ResponseBean request = new ResponseBean("1212","123123");
    
    restTemplate.delete(url);
    System.out.println("-----------");
  }
  public void simpleTest08() {
    String url = "http://localhost:8080/user/login01";

    ResponseBean request = new ResponseBean("1212","123123");
    
    restTemplate.put(url, request);
    System.out.println("-----------");
  }
  public void simpleTest07() {
    String url = "http://localhost:8080/user/login04";

    ResponseBean request = new ResponseBean("1212","123123");
    
    System.out.println(restTemplate.postForLocation(url, request));
    System.out.println("-----------");
  }
  public void simpleTest06() {
    String url = "http://localhost:8080/user/login03";

    ResponseBean request = new ResponseBean("1212","123123");
    
    ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
    System.out.println("-----------");
    System.out.println(responseBean.toString());
    System.out.println("-----------");
  }

  public void simpleTest05() {
    String url = "http://localhost:8080/user/login01";
    // 请求头设置,x-www-form-urlencoded格式的数据
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    // 提交参数设置
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("code", "唐三藏");
    map.add("msg", "唐三111藏");
    // 组装请求体
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

    ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
    System.out.println("-----------");
    System.out.println(responseBean.toString());
    System.out.println("-----------");
  }

  public void simpleTest04() {
    String url = "http://localhost:8080/user/login";
    HttpHeaders headers = new HttpHeaders();
    headers.add("token", "123456789");
    // 封装请求头
    HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(headers);

    ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, formEntity, Map.class);
    System.out.println("-----------");
    System.out.println(exchange);
    System.out.println("-----------");
  }

  public void simpleTest() {
    String url = "http://localhost:8080/user/login?username=zhangsan";
    String str = restTemplate.getForObject(url, String.class);
    System.out.println("-----------");
    System.out.println(str);
    System.out.println("-----------");
  }

  public void simpleTest02() {
    String url = "http://localhost:8080/user/login02/{1}";
    String str = restTemplate.getForObject(url, String.class, "zhangsan");
    System.out.println("-----------");
    System.out.println(str);
    System.out.println("-----------");
  }

  public void simpleTest01() {
    String url = "http://localhost:8080/user/login01?username={username}";
    Map<String, String> map = new HashMap<>();
    map.put("username", "埃里给");
    String str = restTemplate.getForObject(url, String.class, map);
    System.out.println("-----------");
    System.out.println(str);
    System.out.println("-----------");
  }

  public void simpleTest03() {
    String url = "http://localhost:8080/user/login01?username={username}";
    Map<String, String> map = new HashMap<>();
    map.put("username", "埃里给");
    ResponseEntity<ResponseBean> response = restTemplate.getForEntity(url, ResponseBean.class, map);

    System.out.println("-----------");
    // 获取响应体
    System.out.println("HTTP 响应body：" + response.getBody().toString());
    System.out.println("-----------");

    // 以下是getForEntity比getForObject多出来的内容
    HttpStatus statusCode = response.getStatusCode();
    int statusCodeValue = response.getStatusCodeValue();
    HttpHeaders headers = response.getHeaders();
    System.out.println("HTTP 响应状态：" + statusCode);
    System.out.println("HTTP 响应状态码：" + statusCodeValue);
    System.out.println("HTTP Headers信息：" + headers);
  }

}
