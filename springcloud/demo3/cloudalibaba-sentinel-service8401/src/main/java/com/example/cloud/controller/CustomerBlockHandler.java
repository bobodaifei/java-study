package com.example.cloud.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.cloud.component.base.Result;

public class CustomerBlockHandler {

    public static Result<?> handlerException1(BlockException exception){
        return Result.error("4444","按客户自定义,global handlerException----1");
    }

    public static Result<?> handlerException2(BlockException exception){
        return Result.error("4444","按客户自定义,global handlerException----2");
    }
}

