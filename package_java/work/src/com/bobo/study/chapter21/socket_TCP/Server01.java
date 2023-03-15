package com.bobo.study.chapter21.socket_TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server01 {
  public static void main(String[] args) throws IOException {
    // 1.在本机的9999端口监听，等待连接
    // 要求没有被占用端口
    // serverSocket可以创建多个socket[多客户端连接]
    ServerSocket serverSocket = new ServerSocket(9999);
    // 2.当没有客户端连接9999端口，程序会阻塞，等待连接
    // 如果有客户端连接，则会返回Socket对象，程序继续
    Socket socket = serverSocket.accept();

    System.out.println("有客户端连接");

    // 3.通过socket.getInputStream() 读取客户端写入到数据通道的数据，显示
    
    InputStream inputStream = socket.getInputStream();
    byte[] buf = new byte[1024];
    int readLength = 0;
    while ((readLength = inputStream.read(buf)) != -1) {
      System.out.println(new String(buf, 0, readLength));
    }

    
    OutputStream outputStream = socket.getOutputStream();
    outputStream.write("hello".getBytes());
    // 注意：需要一个输出结束标记
    socket.shutdownOutput();

    outputStream.close();
    inputStream.close();
    socket.close();
    serverSocket.close();

  }
}
