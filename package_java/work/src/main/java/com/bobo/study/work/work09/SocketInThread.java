package com.bobo.study.work.work09;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

public class SocketInThread extends Thread {
  private Socket socket;
  final String STR_PATH = "E:\\新建文件夹 (2)\\";

  public SocketInThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    FileOutputStream out = null;
    InputStream in = null;

    try {
      in = socket.getInputStream();
      byte[] bytes = new byte[1024];
      int count;
      while (true) {
        out = new FileOutputStream(new File(STR_PATH + new Date().getTime() + ".png"));
        while ((count = in.read(bytes)) != -1) {
          out.write(bytes, 0, count);
          if (count < 1024) {
            break;
          }
        }
        out.flush();
        out.close();
        System.out.println("接收完毕，存放于" + STR_PATH);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }
}
