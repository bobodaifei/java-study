package com.bobo.study.work.work;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientDemo {
  public static void main(String[] args) throws UnknownHostException, IOException {

    Socket socket = new Socket("127.0.0.1", 9999);
    // 读信息 输入
    InputStream in = socket.getInputStream();
    // 写信息 输出
    OutputStream out = socket.getOutputStream();
    while (true) {
      Scanner scanner = new Scanner(System.in);
      String str = scanner.next();
      out.write(str.getBytes());

      byte[] a = new byte[1024];
      int count = in.read(a);

      // 只读一次
      if (count != -1) {
        System.out.println("客户端收到了" + new String(a, 0, count));
      }

    }
    // socket.close();

  }
}
