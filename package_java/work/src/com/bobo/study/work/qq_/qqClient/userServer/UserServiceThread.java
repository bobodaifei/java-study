package com.bobo.study.work.qq_.qqClient.userServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.bobo.study.work.qq_.qqCommon.Message;
import com.bobo.study.work.qq_.qqCommon.MessageType;
import com.bobo.study.work.qq_.qqCommon.User;

public class UserServiceThread implements Runnable {
  private ServerSocket serverSocket;

  public UserServiceThread(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Socket socket = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        User user = (User) ois.readObject();

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(user.getUserId() + "尝试连接");

        // 验证用户是否有效

        if (checkUser(user.getUserId(), user.getPwd())) {
          // 有用户连接
          System.out.println(user.getUserId() + "已连接");
          oos.writeObject(new Message(null, null, null, new Date(), MessageType.MESSAGE_LOGIN_SUCCESSED));
          ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, user.getUserId());
          serverConnectClientThread.start();
          ManageServerConnectClientThread.addServerConnectClientThread(user.getUserId(), serverConnectClientThread);
        } else {
          oos.writeObject(new Message(null, null, null, new Date(), MessageType.MESSAGE_LOGIN_FAIL));
          socket.close();
        }

      }
    } catch (Exception e) {
    } finally {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  //模拟验证用户账号密码
  private static boolean checkUser(String userId, String pwd) {
    User user = QQserver.userList.get(userId);
    if (user == null) {
      return false;
    }
    if (!(user.getPwd().equals(pwd))) {
      return false;
    }
    return true;
  }
}
