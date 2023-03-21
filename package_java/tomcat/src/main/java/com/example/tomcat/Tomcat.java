package com.example.tomcat;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Tomcat {
  // web名 以及其全部的Servlet
  private Map<String, Context> contextMap = new HashMap<>();

  public static void main(String[] args) {
    Tomcat tomcat = new Tomcat();
    // 以注解形式加载servlet，此处提前加载了所有的servlet
    // tomcat.deployApps();
    //以xml形式扫描加载Servlet
    tomcat.initApps();
    tomcat.start();
  }

  public void start() {

    try {
      // 创建20个连接的线程池
      ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);
      ServerSocket serverSocket = new ServerSocket(8888);
      while (true) {
        System.out.println("等待连接");
        Socket socket = serverSocket.accept();
        newFixedThreadPool.execute(new SocketThread(socket, this));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initApps() {
    // 通过dom4j的方式读取xml
    try {
      File file = new File(System.getProperty("user.dir"), "webapps");
      // 扫描全部web应用
      for (String app : file.list()) {
        initApp(file, app);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void initApp(File webapps, String appName) throws DocumentException {
    // 获取web应用的文件夹
    File appDirectory = new File(webapps, appName);
    // 获取xml所在目录
    File webInfDirectory = new File(appDirectory, "WEB-INF");
    // 获取classes目录
    File classesDirectory = new File(webInfDirectory, "classes");
    // 每一个web应用的context对象
    Context context = new Context(appName);
    // 获取读取器
    SAXReader saxReader = new SAXReader();
    // xml文档总模型
    Document document = saxReader.read(webInfDirectory.getAbsolutePath() + "/web.xml");
    // 获取根元素
    Element rootElement = document.getRootElement();
    // 根节点下的每一个元素
    List<Element> elements = rootElement.elements();
    // 获取servlet-mapping节点
    for (Element elementServletMapping : elements) {
      // 不区分大小写
      if ("servlet-mapping".equalsIgnoreCase(elementServletMapping.getName())) {
        Element servletName = elementServletMapping.element("servlet-name");
        Element urlPattern = elementServletMapping.element("url-pattern");
        // 获取servlet-mapping节点对应的class文件进行反射实例化
        for (Element elementServlet : elements) {
          if ("servlet".equalsIgnoreCase(elementServlet.getName())) {
            Element name_ = elementServlet.element("servlet-name");
            if (servletName.getText().equals(name_.getText())) {
              Element class_ = elementServlet.element("servlet-class");
              try {
                // 自定义路径的类加载器
                WebappClassLoader webappClassLoader = new WebappClassLoader(new URL[] { classesDirectory.toURL() });
                Class<?> servletClass = webappClassLoader.loadClass(class_.getText());
                context.addUrlPatternMapping(urlPattern.getText(),
                    (Servlet) servletClass.getConstructor().newInstance());
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    }
    System.out.println("app:" + appName + "准备就绪");
    // 维护全部的web应用
    contextMap.put(appName, context);
  }

  private void deployApps() {
    File file = new File(System.getProperty("user.dir"), "webapps");
    // 扫描全部web应用
    for (String app : file.list()) {
      deployApp(file, app);
    }
  }

  // 基于注解形式的Servlet扫描
  private void deployApp(File webapps, String appName) {
    // 获取web应用的文件夹
    File appDirectory = new File(webapps, appName);
    // 获取web应用的WEB-INF文件夹
    File webInfDirectory = new File(appDirectory, "WEB-INF");
    // 获取classes目录
    File classesDirectory = new File(webInfDirectory, "classes");
    // 每一个web应用的context对象
    Context context = new Context(appName);

    // 获取classes目录下的全部文件
    List<File> files = getAllFilePath(classesDirectory);
    // 转换为com.example.BoboServlet格式，进行自定义类加载
    for (File clazz : files) {
      String name = clazz.getPath();
      // 得到包名格式
      name = name.replace(classesDirectory.getPath() + "\\", "");
      name = name.replace(".class", "");
      name = name.replace("\\", ".");
      try {
        // 自定义路径的类加载器
        WebappClassLoader webappClassLoader = new WebappClassLoader(new URL[] { classesDirectory.toURL() });
        Class<?> servletClass = webappClassLoader.loadClass(name);
        // 如果继承了HttpServlet
        if (HttpServlet.class.isAssignableFrom(servletClass)) {
          // 如果有@WebServlet注解
          if (servletClass.isAnnotationPresent(WebServlet.class)) {
            WebServlet annotation = servletClass.getAnnotation(WebServlet.class);
            // 获取WebServlet注解注解的urlPatterns数组对象
            String[] urlPatterns = annotation.urlPatterns();
            // 反射实例化 维护web的servlet
            for (String urlPattern : urlPatterns) {
              context.addUrlPatternMapping(urlPattern, (Servlet) servletClass.getConstructor().newInstance());
            }
          }
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println("app:" + appName + "准备就绪");
    // 维护全部的web应用
    contextMap.put(appName, context);

  }

  private List<File> getAllFilePath(File srcFile) {
    List<File> res = new ArrayList<>();
    File[] files = srcFile.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          res.addAll(getAllFilePath(file));
        } else {
          res.add(file);
        }
      }
    }
    return res;
  }

  public Map<String, Context> getContextMap() {
    return contextMap;
  }

  // // （待定）
  // public void start() {

  // try {
  // // 创建Nio，ServerSocketChannel
  // ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
  // serverSocketChannel.socket().bind(new InetSocketAddress(9000));
  // // 设置serverSocketChannel为非阻塞
  // serverSocketChannel.configureBlocking(false);
  // // 打开Selector处理Channel，即创建epoll
  // Selector selector = Selector.open();
  // // 将serverSocketChannel注册到selector上，并且设置selector对客户端accept连接事件进行监听
  // serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
  // System.out.println("服务器启动成功");

  // while (true) {
  // // 阻塞需要处理的事件发生
  // selector.select();

  // // 获取selector中注册的全部事件的SelectionKey实例
  // Set<SelectionKey> selectedKeys = selector.selectedKeys();
  // Iterator<SelectionKey> iterator = selectedKeys.iterator();
  // while (iterator.hasNext()) {
  // SelectionKey key = iterator.next();
  // // 如果是OP_ACCEPT事件，则进行连接获取和事件注册
  // if (key.isAcceptable()) {
  // ServerSocketChannel server = (ServerSocketChannel) key.channel();
  // SocketChannel socketChannel = server.accept();
  // socketChannel.configureBlocking(false);
  // // 这里只注册了读事件，如果需要给客户端发数据可以注册写事件
  // socketChannel.register(selector, SelectionKey.OP_READ);
  // System.out.println("客户端连接成功");
  // } else if (key.isReadable()) {// 如果是OP_READ事件，则读取和打印
  // SocketChannel socketChannel = (SocketChannel) key.channel();
  // ByteBuffer byteBuffer = ByteBuffer.allocate(6);
  // int len = socketChannel.read(byteBuffer);
  // // 如果有数据，进行线程操作
  // if (len > 0) {
  // System.out.println(new String(byteBuffer.array()));
  // }
  // }
  // }
  // iterator.remove();
  // }

  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

}
