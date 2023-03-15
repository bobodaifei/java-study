package com.bobo.study.chapter21.intetAddress_;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class api_ {
  public static void main(String[] args) throws UnknownHostException {
    //获取本机InetAddress对象
    InetAddress localHost = InetAddress.getLocalHost();
    System.out.println(localHost);

    //指定主机名 获取InetAddress对象
    InetAddress byName = InetAddress.getByName("DESKTOP-JS55385");
    System.out.println(byName);

    //根据域名返回InetAddress对象
    InetAddress byName2 = InetAddress.getByName("www.baidu.com");
    System.out.println(byName2);

    //通过InetAddress对象获取对应地址
    String hostString = byName2.getHostAddress();
    System.out.println(hostString);

    //通过InetAddress对象获取主机名或域名
    String hostName = byName2.getHostName();
    System.out.println(hostName);

  }
}
