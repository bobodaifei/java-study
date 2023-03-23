package com.bobo.demo.qq_.qqClient.userServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.bobo.demo.qq_.qqClient.utils.Utility;
import com.bobo.demo.qq_.qqCommon.Message;
import com.bobo.demo.qq_.qqCommon.MessageType;

public class SendToAllService{

  public void sendNewsToAllService() {
    while (true) {
      System.out.println("请输入需要推出的新闻,exit退出");
      String news = Utility.readString(50);
      if ("exit".equals(news)) {
        break;
      }
      Message msg = new Message();
      msg.setContent(news);
      msg.setSendTime(new Date());
      msg.setSender("服务器");
      msg.setMessageType(MessageType.MESSAGE_TOALL_MES);

      HashMap<String, ServerConnectClientThread> hm = ManageServerConnectClientThread.getHm();
      Iterator<String> iterator = hm.keySet().iterator();
      while (iterator.hasNext()) {
        String userId = iterator.next().toString();
          try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageServerConnectClientThread
                .getServerConnectClientThread(userId).getSocket().getOutputStream());
            objectOutputStream.writeObject(msg);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
      }
    }
  }
}
