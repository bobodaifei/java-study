package com.forgetting.reminder.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.forgetting.reminder.entity.Worker;

import java.util.List;

public class WorkerListTypeHandler extends FastjsonTypeHandler {

    public WorkerListTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected Object parse(String json) {
        return JSON.parseObject(json, new TypeReference<List<Worker>>() {
        });
    }
}