package com.bobo.demo.qq_.qqClient.userClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.bobo.demo.qq_.qqCommon.Message;
import com.bobo.demo.qq_.qqCommon.MessageType;

public class ClientConnectServerThread extends Thread {
  private Socket socket;
  private String userId;

  // public ClientConnectServerThread(Socket socket) {
  // this.socket = socket;
  // }

  public ClientConnectServerThread(Socket socket, String userId) {
    this.socket = socket;
    this.userId = userId;
  }

  @Override
  public void run() {
    while (true) {
      // 接收做多线程 因为要一直和等待接收信息，则需要while循环
      try {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message msg = (Message) ois.readObject();

        // 服务器返回的在线用户列表
        if (msg.getMessageType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
          String[] onlineUsers = msg.getContent().split(" ");
          System.out.println("==========当前用户列表===========");
          for (String string : onlineUsers) {
            System.out.println("用户：" + string);
          }
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_COMM_MES)) {//接收私法消息
          System.out.println(msg.getSender() + "向你发来了一条" + msg.getContent() + "信息。日期为" + msg.getSendTime());
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_TOALL_MES)) {//接收群发消息
          System.out.println(msg.getSender() + "向你群发来了一条" + msg.getContent() + "信息。日期为" + msg.getSendTime());
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_FILE_MES)) {//接收文件
          String path = "E:/";
          FileOutputStream fileOutputStream = new FileOutputStream(path + msg.getFileName() + "." + msg.getFileType());
          fileOutputStream.write(msg.getFileBytes());
          fileOutputStream.flush();
          fileOutputStream.close();
          System.out.println("接收文件成功");
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        try {
          ManageClientConnectServerThread.removeClientConnectServerThread(userId);
          socket.close();
          break;
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

}
