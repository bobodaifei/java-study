package com.bobo.study.work.qq_.qqClient.userClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.bobo.study.work.qq_.qqCommon.Message;
import com.bobo.study.work.qq_.qqCommon.MessageType;

public class MessageClientService {

  //发送消息给某人
  public void sendMessageToOne(String content, String senderId, String getterId) {
    Message msg = new Message();
    msg.setSender(senderId);
    msg.setGetter(getterId);
    msg.setContent(content);
    msg.setSendTime(new Date());
    msg.setMessageType(MessageType.MESSAGE_COMM_MES);
    System.out.println(msg.getSender() + "发给" + msg.getGetter() + "的消息" + msg.getContent());

    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
          ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
      objectOutputStream.writeObject(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //群发消息
  public void sendMessageToAll(String content, String senderId) {
    Message msg = new Message();
    msg.setSender(senderId);
    msg.setContent(content);
    msg.setSendTime(new Date());
    msg.setMessageType(MessageType.MESSAGE_TOALL_MES);

    System.out.println(msg.getSender() + "群发的消息" + msg.getContent());
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
          ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
      objectOutputStream.writeObject(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
