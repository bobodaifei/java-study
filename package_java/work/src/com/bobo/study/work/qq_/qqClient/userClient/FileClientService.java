package com.bobo.study.work.qq_.qqClient.userClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.bobo.study.work.qq_.qqCommon.Message;
import com.bobo.study.work.qq_.qqCommon.MessageType;

public class FileClientService {
  public void sendFileToOne(String src, String fileName, String senderId, String getterId, String fileType) {
    Message msg = new Message();
    msg.setSender(senderId);
    msg.setGetter(getterId);
    msg.setFileName(fileName);
    msg.setFileType(fileType);
    msg.setSendTime(new Date());
    msg.setMessageType(MessageType.MESSAGE_FILE_MES);
    System.out.println(msg.getSender() + "发给" + msg.getGetter() + "的文件" + "路径为" + src);

    try {
      int len = (int) new File(src).length();
      byte[] b = new byte[len];
      FileInputStream fileInputStream = new FileInputStream(src);
      fileInputStream.read(b);
      msg.setFileBytes(b);
      msg.setFileLen(len);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
          ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
      objectOutputStream.writeObject(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
