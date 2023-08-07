package com.example.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloud.entity.Payment;
import com.example.cloud.mapper.PaymentMapper;
import com.example.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author frx
 * @version 1.0
 * @date 2022/8/12  17:40
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

  @Resource
  private PaymentMapper paymentMapper;

  public int create(Payment payment) {
    return paymentMapper.insert(payment);
  }

  public Payment getPaymentById(Long id) {
    return paymentMapper.selectById(id);
  }
}

