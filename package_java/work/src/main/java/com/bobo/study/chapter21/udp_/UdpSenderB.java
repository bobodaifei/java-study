package com.bobo.study.chapter21.udp_;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpSenderB {
  public static void main(String[] args) throws IOException {
    // 创建一个DatagramSocket 对象，准备在8888端口接收数据
    DatagramSocket socket = new DatagramSocket(8888);
    //2.需要发送的数据
    byte[] data = "吃饭啦".getBytes();
    //数据，数据长度，主机ip，端口
    DatagramPacket packet = new DatagramPacket(data, data.length,InetAddress.getByName("127.0.0.1"), 9999);
    socket.send(packet);

    byte[] b = new byte[1024];
    DatagramPacket packet1 = new DatagramPacket(b, b.length);
    System.out.println("等待接收");
    socket.receive(packet);

    // 拆包，取出数据
    int length = packet.getLength();// 数据字节长度
    byte[] data1 = packet.getData();// 接收数据
    String string = new String(data1, 0, length);
    System.out.println(string);

    socket.close();
  }
}
