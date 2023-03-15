package com.bobo.study.chapter21.udp_;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpReceiverA {
  public static void main(String[] args) throws IOException {
    // 1.创建一个DatagramSocket对象，准备在端口9999接收数据
    DatagramSocket socket = new DatagramSocket(9999);
    // 2.构建一个DatagramPacket对象，准备接收数据
    // 最大64k
    byte[] b = new byte[1024];
    DatagramPacket packet = new DatagramPacket(b, b.length);
    // 3.调用接收方法，将DatagramPacket对象填充到packet中
    // 当有数据包发送到本机的9999端口，就会接收数据
    // 如果没有数据包发送，则会阻塞等待
    System.out.println("等待执行");
    socket.receive(packet);

    // 拆包，取出数据
    int length = packet.getLength();// 数据字节长度
    byte[] data = packet.getData();// 接收数据
    String string = new String(data, 0, length);
    System.out.println(string);

    byte[] data1 = "吃饭啦".getBytes();
    // 数据，数据长度，主机ip，端口
    DatagramPacket packet1 = new DatagramPacket(data1, data1.length, InetAddress.getByName("127.0.0.1"), 8888);
    socket.send(packet1);
    
    socket.close();
  }
}
