package com.bobo.study.chapter21.socket_TCP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPFileDownClient {
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("127.0.0.1", 9999);

    OutputStream outputStream = socket.getOutputStream();
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
    bufferedOutputStream.write("难却DJ".getBytes());
    bufferedOutputStream.flush();
    socket.shutdownOutput();

    InputStream inputStream = socket.getInputStream();
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream("E:/难却DJ-1.mp3"));
    bufferedOutputStream2.write(bytes);
    bufferedOutputStream2.close();
    bufferedInputStream.close();

    bufferedOutputStream.close();
    socket.close();
  }
}
