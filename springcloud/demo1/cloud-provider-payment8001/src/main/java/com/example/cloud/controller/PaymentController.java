package com.example.cloud.controller;

import com.example.cloud.component.base.Result;
import com.example.cloud.entity.Payment;
import com.example.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

  @Resource
  private PaymentService paymentService;

  @PostMapping("/create")
  public Result<?> create(Payment payment){
    int result = paymentService.create(payment);
    log.info("插入结果:"+result);
    if(result>0){
      return Result.success(result);
    }
    return Result.error("-1","插入数据库失败");
  }

  @GetMapping("/get/{id}")
  public Result<?> getPaymentById(@PathVariable("id") Long id){
    Payment payment = paymentService.getPaymentById(id);
    log.info("查询结果:"+payment);
    if(payment!=null){
      return Result.success(payment);
    }
    return Result.error("-1","查询失败");
  }
}