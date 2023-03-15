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

public class TCPFileDownServer {
  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(9999);
    Socket socket = serverSocket.accept();

    System.out.println("有客户端连接");

    // 一次性读写
    InputStream inputStream = socket.getInputStream();
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
    String str = new String(bytes, 0, bytes.length);
    String resFileName = "";
    if ("难却DJ".equals(str)) {
      resFileName = "E:/难却DJ.mp3";
    }else{
      resFileName = "E:/诚信声明-1.mp3";
    }


    OutputStream outputStream = socket.getOutputStream();
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(resFileName));
    byte[] bytes1 = StreamUtils.streamToByteArray(bufferedInputStream2);
    bufferedOutputStream.write(bytes1);
    bufferedOutputStream.flush();

    socket.shutdownOutput();

    bufferedOutputStream.close();
    bufferedInputStream.close();
    socket.close();
    serverSocket.close();
  }
}
