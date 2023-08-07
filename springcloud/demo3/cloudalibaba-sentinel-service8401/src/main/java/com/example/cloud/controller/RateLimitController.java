package com.example.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.cloud.component.base.Result;
import com.example.cloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public Result<?> byResource() {
        Payment payment = new Payment();
        payment.setId(1321L);
        return Result.success(payment);
    }

    public Result<?> handleException(BlockException exception) {
        return Result.error("444", exception.getClass().getCanonicalName() + "\t服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public Result<?> byUrl() {
        Payment payment = new Payment();
        payment.setId(1321L);
        return Result.success(payment);
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public Result<?> customerBlockHandler() {
        return Result.success("qewqe");
    }
}
