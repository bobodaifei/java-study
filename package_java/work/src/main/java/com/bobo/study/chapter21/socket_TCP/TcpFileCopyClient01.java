package com.bobo.study.chapter21.socket_TCP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TcpFileCopyClient01
 */
public class TcpFileCopyClient01 {

  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("127.0.0.1", 9999);

    //边读边写
    // BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("E:/诚信声明.png"));
    // OutputStream outputStream = socket.getOutputStream();
    // BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
    // byte[] buf_file = new byte[1024];
    // int readLength_file = 0;
    // while ((readLength_file = bufferedInputStream.read(buf_file)) != -1) {
    //   bufferedOutputStream.write(buf_file, 0, readLength_file);
    // }
    // socket.shutdownOutput();
    // bufferedInputStream.close();

    //一次性读写
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("E:/诚信声明.png"));
    //获取整个文件的byte数据
    byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
    OutputStream outputStream = socket.getOutputStream();
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
    bufferedOutputStream.write(bytes);
    bufferedOutputStream.flush();
    socket.shutdownOutput();
    bufferedInputStream.close();


    InputStream inputStream = socket.getInputStream();
    String streamToString = StreamUtils.streamToString(inputStream);
    System.out.println(streamToString);

    bufferedOutputStream.close();
    inputStream.close();
    socket.close();
  }
}