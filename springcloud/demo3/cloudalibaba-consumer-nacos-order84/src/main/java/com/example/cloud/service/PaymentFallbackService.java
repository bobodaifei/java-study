package com.example.cloud.service;

import com.example.cloud.component.base.Result;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public Result<?> paymentSQL(Long id) {
        return Result.error("44444","服务降级返回,---PaymentFallbackService");
    }
}

