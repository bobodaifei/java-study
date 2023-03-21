package com.bobo.demo.qq_.qqClient.userServer;

import java.util.HashMap;
import java.util.Set;

public class ManageServerConnectClientThread {
  private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

  public static void addServerConnectClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
    hm.put(userId, serverConnectClientThread);
  }

  // 从中取出
  public static ServerConnectClientThread getServerConnectClientThread(String userId) {
    return hm.get(userId);
  }

  public static void removeServerConnectClientThread(String userId) {
    hm.remove(userId);
  }

  public static String onlineUsers() {
    Set<String> keySet = hm.keySet();
    StringBuffer stringBuffer = new StringBuffer();
    for (String string : keySet) {
      stringBuffer.append(string + " ");
    }
    return stringBuffer.toString();
  }

  public static HashMap<String, ServerConnectClientThread> getHm() {
    return hm;
  }

}
