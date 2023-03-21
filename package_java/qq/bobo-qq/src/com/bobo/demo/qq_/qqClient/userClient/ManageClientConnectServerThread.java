package com.bobo.demo.qq_.qqClient.userClient;

import java.net.Socket;
import java.util.HashMap;

public class ManageClientConnectServerThread {
  // key 用户id
  // 存放线程,目的是为了实现多用户登录
  private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

  // 添加到hashmap
  public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
    hm.put(userId, clientConnectServerThread);
  }

  // 从中取出
  public static ClientConnectServerThread getClientConnectServerThread(String userId) {
    return hm.get(userId);
  }

  public static void removeClientConnectServerThread(String userId) {
    hm.remove(userId);
  }

}
