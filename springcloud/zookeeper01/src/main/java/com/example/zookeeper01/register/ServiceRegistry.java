package com.example.zookeeper01.register;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.HashMap;

@Component
public class ServiceRegistry {

  @Value("${spring.cloud.zookeeper.connect-string}")
  private String ZOOKEEPER_ADDRESS;

  @Value("${spring.cloud.zookeeper.time-out}")
  private int SESSION_TIMEOUT;

  @Value("${spring.cloud.zookeeper.service-registry-path}")
  private String SERVICE_REGISTRY_PATH;

  private ZooKeeper zooKeeper;

  @PostConstruct
  public void init() throws IOException, InterruptedException, KeeperException {
    // 在构造器执行后进行属性的处理和初始化操作
    // 可以使用this.value访问被注入的属性值
    this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, new Watcher() {
      @Override
      public void process(WatchedEvent event) {
        // 处理Zookeeper事件
      }
    });
    zooKeeper.getChildren("/services", true);
  }

  @PreDestroy
  public void shutdown() throws InterruptedException, KeeperException {
    System.out.println("注销");
    // 在应用程序关闭前执行清理操作
    zooKeeper.close();
  }

  //CreateMode.EPHEMERAL临时节点
  public void registerService(String serviceName, String serviceAddress) throws Exception {
    if (zooKeeper.exists(SERVICE_REGISTRY_PATH, false) == null) {
      zooKeeper.create(SERVICE_REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    String servicePath = SERVICE_REGISTRY_PATH + "/" + serviceName;
    if (zooKeeper.exists(servicePath, false) == null) {
      zooKeeper.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    String addressPath = servicePath + "/" + serviceAddress;
    if (zooKeeper.exists(addressPath, false) == null){
      zooKeeper.create(addressPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    Stat stat = new Stat();
    byte[] currentData = zooKeeper.getData(addressPath, false, stat);
    int currentVersion = stat.getVersion();

    try {
      HashMap<String, String> map = new HashMap<>();
      map.put("name", serviceName);
      map.put("id", serviceAddress);
      map.put("address", serviceAddress.split(":")[0]);
      map.put("port", serviceAddress.split(":")[1]);
      String jsonString = JSON.toJSONString(map);
      zooKeeper.setData(addressPath, jsonString.getBytes(), currentVersion);
    } catch (KeeperException.BadVersionException e) {
      System.out.println("Failed to update node. Version mismatch.");
      // 处理版本号不匹配的情况，可以进行重试或其他操作
    }


  }
}