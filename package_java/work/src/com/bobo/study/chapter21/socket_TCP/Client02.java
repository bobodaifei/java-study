package com.bobo.study.chapter21.socket_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client02 {
  public static void main(String[] args) throws UnknownHostException, IOException {
    // 1.连接服务端 （ip，端口）
    // 连接127.0.0.1(本机)的9999端口,如果连接成功则返回Socket对象
    Socket socket = new Socket("127.0.0.1", 9999);

    // 2.连接后，生成socket，通过socket.getOutputStream()
    // 得到和socket关联的输出流对象
    OutputStream outputStream = socket.getOutputStream();
    // 2.5 转成字符流
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    bufferedWriter.write("hello 字符流");
    // 另一种结束标记，但需要另一端使用readLine()!!!来标识要结束
    bufferedWriter.newLine();
    // 需要刷新一下 否则不会写入
    bufferedWriter.flush();

    // 注意：需要一个输出结束标记
    // socket.shutdownOutput();

    InputStream inputStream = socket.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    String str = bufferedReader.readLine();
    System.out.println(str);

    bufferedReader.close();
    bufferedWriter.close();
    socket.close();
  }

}
