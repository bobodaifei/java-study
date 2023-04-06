package com.bobo.study.work.work09;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketOutThread extends Thread {
  private Socket socket;

  public SocketOutThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    String strPath;
    FileInputStream in = null;
    OutputStream out = null;
    try {
      out = socket.getOutputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (true) {
      System.out.println("请输入要发送的文件路径：");
      Scanner scanner = new Scanner(System.in);
      strPath = scanner.next();
      try {
        in = new FileInputStream(new File(strPath));
        byte[] bytes = new byte[1024];
        int count;
        while ((count = in.read(bytes)) != -1) {
          out.write(bytes, 0, count);
        }
        out.flush();
        in.close();
        System.out.println("传输完毕");
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

}
