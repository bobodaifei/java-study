package com.bobo.study.work.work;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketDemo {
  public static void main(String[] args) throws IOException {
    ServerSocket server = new ServerSocket(9999);
    Socket socket = server.accept();
    while (true) {
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();
      byte[] a = new byte[1024];
      int count = in.read(a);
      if (count != -1) {
        System.out.println("服务端接收到了：" + new String(a, 0, count));
      }
      Scanner scanner = new Scanner(System.in);
      String str = scanner.next();
      out.write(str.getBytes());

      // socket.close();
    }

  }
}
