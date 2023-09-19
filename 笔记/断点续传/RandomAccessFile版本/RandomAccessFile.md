# RandomAccessFile

InputStream和OutputStream代表字节流，而Reader和Writer代表字符流，它们的共同特点是：只能按照数据的先后顺序读取数据源的数据，以及按照数据的先后顺序向数据汇写数据。

RandomAccessFile类不属于流，它具有随机读写文件的功能，能够从文件的任意位置开始执行读写操作。

RandomAccessFile类提供了用于定位文件位置的方法：

* getFilePointer()：返回当前读写指针所处的位置。
* seek(long pos)：设定读写指针的位置，与文件开头相隔pos个字节数。
* skipBytes(int n)：使读写指针从当前位置开始，跳过n个字节。
* length()：返回文件包含的字节数。

RandomAccessFile类实现了DataInput和DataOutput接口，因此能够读写格式化的数据。RandomAccessFile类具有以下构造方法：

```java
RandomAccessFile(File file, String mode) ：参数file指定被访问的文件。
RandomAccessFile(String name, String mode)：参数name指定被访问的文件的路径。
```

以上构造方法的mode参数指定访问模式，可选值包括“r”,“rw”，“rwd”，“rws”。 **“r”表示随机读模式。“rw”表示随机读写模式。** 如果程序仅仅读文件，那么选择“r”，如果程序需要同时读和写文件，那么选择“rw”。值得注意的是，RandomAccessFile不支持只写文件模式，因此把mode参数设为“w”是非法的。

以下例程1的RandomTester类演示了RandomAccessFile类的用法。

例程1 **RandomTester.java**

```java
import java.io.*;
public class RandomTester {
  public static void main(String args[])throws IOException{
    RandomAccessFile rf=new RandomAccessFile("D:\\test.dat","rw");
    for(int i=0;i<10;i++)
      rf.writeLong(i*1000);

    rf.seek(5*8);  //从文件开头开始，跳过第5个long数据，接下来写第6个long数据
    rf.writeLong(1234);

    rf.seek(0);  //把读写指针定位到文件开头
    for(int i=0;i<10;i++)
      System.out.println("Value"+i+":"+rf.readLong());

    rf.close();
  }
}
```

在以上main()方法中，先按照“rw”访问模式打开D:\test.dat文件，如果这个文件还不存在，RandomAccessFile的构造方法会创建该文件。

接下来向该文件中写入10个long数据，每个long数据占用8个字节。

**接着rf.seek**(5*8)方法使得读写指针从文件开头开始，跳过第5个long数据，接下来的rf.writeLong(1234)方法将覆盖原来的第6个long数据，把它改写为1234。

**rf.seek**(0)方法把读写指针定位到文件开头，接着读取文件中的所有long数据。

以上程序的打印结果如下：

```text
Value0:0 Value1:1000 Value2:2000 Value3:3000 Value4:4000 
Value5:1234 Value6:6000 Value7:7000 Value8:8000 Value9:9000
```
