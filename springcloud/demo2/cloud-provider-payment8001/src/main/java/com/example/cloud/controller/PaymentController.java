package com.example.cloud.controller;

import com.example.cloud.component.base.Result;
import com.example.cloud.entity.Payment;
import com.example.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//@RequestMapping("/payment")
public class PaymentController {

  @Resource
  private PaymentService paymentService;

  @Value("${server.port}")
  private String serverPort;//添加serverPort

  @Resource
  private DiscoveryClient discoveryClient;

  @PostMapping("/payment/create")
  public Result<?> create(@RequestBody Payment payment){
    int result = paymentService.create(payment);
    log.info("插入结果:"+result);
    if(result>0){
      return Result.success(result + serverPort);
    }
    return Result.error("-1","插入数据库失败");
  }



  @GetMapping("/payment/get/{id}")
  public Result<?> getPaymentById(@PathVariable("id") Long id) throws InterruptedException {
    Payment payment = paymentService.getPaymentById(id);
    log.info("查询结果:"+payment);
    if(payment!=null){
      return Result.success(payment);
    }
    return Result.error("-1","查询失败");
  }


  @GetMapping(value = "/payment/lb")
  public String getPaymentLB() {
    return serverPort;//返回服务接口
  }


  @GetMapping(value = "/payment/discovery")
  public Object discovery(){
    List<String> services = discoveryClient.getServices();
    for (String element : services) {
      log.info("element:"+element);
    }

    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
      log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
    }
    return this.discoveryClient;
  }

  @GetMapping(value = "/payment/feign/timeout")
  public String paymentFeignTimeout()
  {
    // 业务逻辑处理正确，但是需要耗费3秒钟
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return serverPort;
  }

  @GetMapping("/payment/zipkin")
  public String paymentZipkin() {
    return "hi ,i'am paymentzipkin server fall back，welcome to here, O(∩_∩)O哈哈~";
  }
}