package com.example.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService extends IService<Payment> {
  public int create(Payment payment);

  public Payment getPaymentById(@Param("id") Long id);
}