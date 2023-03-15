package com.bobo.study.work.tomcat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

import com.bobo.study.work.tomcat.utils.StreamUtils;

public class SocketThread implements Runnable {
  private Socket socket;

  public SocketThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
      byte[] bytes = StreamUtils.streamToByteArray(bis);

      //数据的解析字节流
      
      for (byte b : bytes) {
        System.out.print((char)b);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
