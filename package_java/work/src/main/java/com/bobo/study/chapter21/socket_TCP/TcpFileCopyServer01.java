package com.bobo.study.chapter21.socket_TCP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileCopyServer01 {
  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(9999);
    Socket socket = serverSocket.accept();

    System.out.println("有客户端连接");

    //边读边写
    // InputStream inputStream = socket.getInputStream();
    // BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    // BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("E:/诚信声明-1.png"));
    // byte[] buf = new byte[1024];
    // int readLength = 0;
    // while ((readLength = inputStream.read(buf)) != -1) {
    //   bufferedOutputStream.write(buf, 0, readLength);
    // }
    // bufferedOutputStream.close();

    //一次性读写
    InputStream inputStream = socket.getInputStream();
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    
    byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("E:/诚信声明-1.png"));
    bufferedOutputStream.write(bytes);
    bufferedOutputStream.close();

    OutputStream outputStream = socket.getOutputStream();
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    bufferedWriter.write("收到图片");
    bufferedWriter.flush();

    socket.shutdownOutput();

    bufferedWriter.close();
    bufferedInputStream.close();
    socket.close();
    serverSocket.close();
  }
}
