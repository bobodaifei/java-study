package com.example.cloud.controller;

import com.example.cloud.component.base.Result;
import com.example.cloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("{server.port}")
    private String serverPort;

    //模拟数据库
    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setSerial("sadasdasda");
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setSerial("sadasdasda");
        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setSerial("sadasdasda");

        hashMap.put(1L, payment);
        hashMap.put(2L, payment1);
        hashMap.put(3L, payment2);
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public Result<?> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return Result.success(payment);
    }
}

