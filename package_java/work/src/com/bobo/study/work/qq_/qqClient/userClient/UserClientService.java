package com.bobo.study.work.qq_.qqClient.userClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.bobo.study.work.qq_.qqCommon.Message;
import com.bobo.study.work.qq_.qqCommon.MessageType;
import com.bobo.study.work.qq_.qqCommon.User;

public class UserClientService {
  private User user = new User();

  private Socket socket;

  public boolean checkUser(String userId, String pwd) throws UnknownHostException, IOException, ClassNotFoundException {
    boolean b = false;
    user.setUserId(userId);
    user.setPwd(pwd);
    socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    oos.writeObject(user);

    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    Message msg = (Message) ois.readObject();
    if (msg.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCESSED)) {// 登陆成功
      
      // 创建一个和服务端保持通信的线程
      ClientConnectServerThread clientConnectServer = new ClientConnectServerThread(socket, user.getUserId());
      clientConnectServer.start();
      ManageClientConnectServerThread.addClientConnectServerThread(user.getUserId(), clientConnectServer);
      b = true;

    }else{
      //登陆失败
      socket.close();
    }
    return b;
  }

  // 获取在线用户列表
  public void onlineFriendList() {
    //发送一个message MESSAGE_GET_ONLINE_FRIEND 
    Message msg = new Message(user.getUserId(), null, null, null, MessageType.MESSAGE_GET_ONLINE_FRIEND);
    try {
      //可以从当前对象拿取socket.getOutputStream()
      // ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      //也可以从线程Hashmap拿取
      ObjectOutputStream oos = new ObjectOutputStream(
          ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
      oos.writeObject(msg);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //无异常退出
  public void clientExit(){
    Message msg = new Message(user.getUserId(), null, null, null, MessageType.MESSAGE_CLIENT_EXIT);
    try {
      ObjectOutputStream oos = new ObjectOutputStream(
          ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
      oos.writeObject(msg);
      System.out.println("结束进程");
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
