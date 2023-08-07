package com.example.cloud.component.config;

import com.example.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);//初始值为0

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();//得到初始值
            next = current >= 2147483647 ? 0 : current + 1;
            //比较，如果当前值current与期望值一致，就修改 返回true 取反 跳出循环
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("-----第几次访问，次数next-----:" + next);//
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstance) {

        int index = getAndIncrement() % serviceInstance.size();
        return serviceInstance.get(index);
    }
}
