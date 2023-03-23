package com.bobo.study.chapter21.socket_TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client01 {
  public static void main(String[] args) throws UnknownHostException, IOException {
    // 1.连接服务端 （ip，端口）
    // 连接127.0.0.1(本机)的9999端口,如果连接成功则返回Socket对象
    Socket socket = new Socket("127.0.0.1", 9999);

    // 2.连接后，生成socket，通过socket.getOutputStream()
    // 得到和socket关联的输出流对象
    OutputStream outputStream = socket.getOutputStream();
    outputStream.write("hello".getBytes());
    // 注意：需要一个输出结束标记
    socket.shutdownOutput();

    InputStream inputStream = socket.getInputStream();
    byte[] buf = new byte[1024];
    int readLength = 0;
    while ((readLength = inputStream.read(buf)) != -1) {
      System.out.println(new String(buf, 0, readLength));
    }

    
    outputStream.close();
    inputStream.close();
    socket.close();
  }

}
