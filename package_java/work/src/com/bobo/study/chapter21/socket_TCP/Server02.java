package com.bobo.study.chapter21.socket_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server02 {
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

    //3.5 字节流转字符流
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    String str = bufferedReader.readLine();
    System.out.println(str);

    //
    OutputStream outputStream = socket.getOutputStream();
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    bufferedWriter.write("hello 字符流");
    // 另一种结束标记，但需要另一端使用readLine()!!!来标识要结束
    bufferedWriter.newLine();
    // 需要刷新一下 否则不会写入
    bufferedWriter.flush();

    bufferedWriter.close();
    bufferedReader.close();
    socket.close();
    serverSocket.close();

  }
}
