package com.bobo.study.work.qq_.qqClient.userServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

import com.bobo.study.work.qq_.qqClient.utils.Utility;
import com.bobo.study.work.qq_.qqCommon.Message;
import com.bobo.study.work.qq_.qqCommon.MessageType;
import com.bobo.study.work.qq_.qqCommon.User;

public class QQserver {

  public static void main(String[] args) throws ClassNotFoundException, IOException {
    QQserver qQserver = new QQserver();
  }

  // 可以使用并发的集合ConcurrentHashMap,没有线程安全问题
  //模拟用户数据库
  static HashMap<String, User> userList = new HashMap<>();

  static {
    userList.put("100", new User("100", "123456"));
    userList.put("200", new User("200", "123456"));
    userList.put("300", new User("300", "123456"));
    userList.put("400", new User("400", "123456"));
  }

  private ServerSocket serverSocket;
  private boolean loop = true;
  private SendToAllService sendToAllService = new SendToAllService();

  public QQserver() throws IOException, ClassNotFoundException {
    System.out.println("服务端在9999进行监听");
    // 端口可以写在配置文件
    serverSocket = new ServerSocket(9999);
    //等待连接线程
    new Thread(new UserServiceThread(serverSocket)).start();
    while (loop) {
      //服务器额外功能
      System.out.println("服务器界面");
      System.out.println("1 新闻推送");
      String key = Utility.readString(50);
      switch (key) {
        case "1":
          sendToAllService.sendNewsToAllService();
          break;

        default:
          break;
      }
    }
  }
}
