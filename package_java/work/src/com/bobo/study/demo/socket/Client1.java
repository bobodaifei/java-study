package com.bobo.study.demo.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {
  public static void main(String[] args) throws Exception {
    // 去对9999进行连接
    Socket socket = new Socket("127.0.0.1",9999);

    // 读信息 输入
    InputStream in = socket.getInputStream();
    // 写信息 输出
    OutputStream out = socket.getOutputStream();

    out.write("hello 我是client".getBytes());

    byte[] a = new byte[1024];
    int count = in.read(a);

    // 只读一次
    if (count != -1) {
      System.out.println("client 收到了" + new String(a, 0, count));
    }

    socket.close();
  }
}
