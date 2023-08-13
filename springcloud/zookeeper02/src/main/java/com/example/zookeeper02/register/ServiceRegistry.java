package com.example.zookeeper02.register;

import com.alibaba.fastjson.JSON;
import com.example.zookeeper02.utils.ZKRestTemplate;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceRegistry {

  @Value("${spring.cloud.zookeeper.connect-string}")
  private String ZOOKEEPER_ADDRESS;

  @Value("${spring.cloud.zookeeper.time-out}")
  private int SESSION_TIMEOUT;

  @Value("${spring.cloud.zookeeper.service-registry-path}")
  private String SERVICE_REGISTRY_PATH;

  @Value("${spring.application.name}")
  private String serviceName;

  @Autowired
  ZKRestTemplate zkRestTemplate;

  private ZooKeeper zooKeeper;

  @PostConstruct
  public void init() throws IOException, InterruptedException, KeeperException {
    this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, new Watcher() {
      @Override
      public void process(WatchedEvent event) {
        System.out.println("进入 process 。。。。。event = " + event);
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (event == null) {
          return;
        }
        // 连接状态
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();

        System.out.println("a: " + keeperState);
        System.out.println("b: " + eventType);
        // 受影响的path
        String path = event.getPath();

        if (Event.KeeperState.SyncConnected == keeperState) {
          // 成功连接上ZK服务器
          if (Event.EventType.None == eventType) {
            System.out.println("成功连接上ZK服务器");
          }
          // 创建节点、更新节点、删除节点
          else if (Event.EventType.NodeCreated == eventType || Event.EventType.NodeDataChanged == eventType || Event.EventType.NodeDeleted == eventType) {
            System.out.println("更新");
            String operation = "";
            if (Event.EventType.NodeCreated == eventType) {
              operation = "节点创建";
            } else if (Event.EventType.NodeDataChanged == eventType) {
              operation = "节点数据更新";
            } else if (Event.EventType.NodeDeleted == eventType) {
              operation = "节点被删除";
            }
            System.out.println(operation);
            try {
//              zooKeeper.getData(path, true, null);
              Thread.sleep(100);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          // 更新子节点
          else if (Event.EventType.NodeChildrenChanged == eventType) {
            String path1 = "/services";
            System.out.println("子节点变更");
            try {
              List<String> children = zooKeeper.getChildren(path1, true);
              System.out.println(children);
              Map<String, List<String>> map = new HashMap();
              for (String p : children) {
                List<String> children1 = zooKeeper.getChildren(path1 + "/" + p, true);
                ArrayList<String> list = new ArrayList<>();
                for (String p1 : children1) {
                  Stat stat = new Stat();
                  byte[] currentData = zooKeeper.getData(path1 + "/" + p + "/" + p1, false, stat);
                  String s = new String(currentData);
                  Map<String, String> map1 = JSON.parseObject(s, Map.class);
                  String address = map1.get("address");
                  String port = map1.get("port");
                  list.add(address + ":" + port);
                }
                map.put(p, list);
              }
              zkRestTemplate.setMap(map);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } else if (Event.KeeperState.Disconnected == keeperState) {
          System.out.println("与ZK服务器断开连接");
        } else if (Event.KeeperState.AuthFailed == keeperState) {
          System.out.println("权限检查失败");
        } else if (Event.KeeperState.Expired == keeperState) {
          System.out.println("会话失效");
        }
        System.out.println("--------------------------------------------");
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

  public void registerService(String serviceName, String serviceAddress) throws Exception {
    if (zooKeeper.exists(SERVICE_REGISTRY_PATH, false) == null) {
      zooKeeper.create(SERVICE_REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    String servicePath = SERVICE_REGISTRY_PATH + "/" + serviceName;
    if (zooKeeper.exists(servicePath, false) == null) {
      zooKeeper.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    String addressPath = servicePath + "/" + serviceAddress;
    if (zooKeeper.exists(addressPath, false) == null) {
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