# InetAddress

## 相关方法

1. 获取本机InetAddress对象getLocalHost
2. 根据指定主机名/域名获取ip地址对象getByName获
3. InetAddress对象的主机名getHostName获
4. InetAddress对象的地址getHostAddress

```java
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

结果：
DESKTOP-JS55385/192.168.37.1
DESKTOP-JS55385/192.168.37.1
www.baidu.com/39.156.66.18
39.156.66.18
www.baidu.com
```
