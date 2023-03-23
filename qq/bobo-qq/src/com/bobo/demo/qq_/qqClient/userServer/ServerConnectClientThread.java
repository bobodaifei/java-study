package com.bobo.demo.qq_.qqClient.userServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.bobo.demo.qq_.qqCommon.Message;
import com.bobo.demo.qq_.qqCommon.MessageType;

public class ServerConnectClientThread extends Thread {
  private Socket socket;
  private String userId;

  public ServerConnectClientThread(Socket socket, String userId) {
    this.socket = socket;
    this.userId = userId;
  }

  @Override
  public void run() {
    // 可以发送或接收消息
    while (true) { //主要负责用户与用户的请求分发
      try {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message msg = (Message) ois.readObject();

        // 用户在线列表信息
        if (msg.getMessageType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
          String onlineUsers = ManageServerConnectClientThread.onlineUsers();
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
          Message message_online = new Message(null, msg.getSender(), onlineUsers, null,
              MessageType.MESSAGE_RET_ONLINE_FRIEND);
          objectOutputStream.writeObject(message_online);
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {//无异常退出
          System.out.println(msg.getSender() + "将要退出系统");
          ManageServerConnectClientThread.removeServerConnectClientThread(msg.getSender());
          socket.close();
          break;
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_COMM_MES)) {//私发消息
          System.out.println(msg.getSender() + "发给" + msg.getGetter() + "的消息" + msg.getContent());
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageServerConnectClientThread
              .getServerConnectClientThread(msg.getGetter()).getSocket().getOutputStream());
          objectOutputStream.writeObject(msg);//
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_TOALL_MES)) {//群发消息
          HashMap<String, ServerConnectClientThread> hm = ManageServerConnectClientThread.getHm();
          Iterator<String> iterator = hm.keySet().iterator();
          while (iterator.hasNext()) {
            String userId = iterator.next().toString();
            if (!(userId.equals(msg.getSender()))) {
              ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageServerConnectClientThread
                  .getServerConnectClientThread(userId).getSocket().getOutputStream());
              objectOutputStream.writeObject(msg);
            }
          }
        } else if (msg.getMessageType().equals(MessageType.MESSAGE_FILE_MES)) {//用户间文件传输

          ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageServerConnectClientThread
              .getServerConnectClientThread(msg.getGetter()).getSocket().getOutputStream());
          objectOutputStream.writeObject(msg);

        }

        // 对msg处理
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        try {
          ManageServerConnectClientThread.removeServerConnectClientThread(userId);
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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
