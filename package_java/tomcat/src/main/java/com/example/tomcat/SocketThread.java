package com.example.tomcat;

import java.io.InputStream;
import java.net.Socket;

import javax.servlet.Servlet;

import com.example.tomcat.entity.Request;
import com.example.tomcat.entity.Response;
import com.example.tomcat.utils.HttpUtils;
import com.example.tomcat.utils.StreamUtils;

public class SocketThread implements Runnable {
  private Socket socket;
  private Tomcat tomcat;

  public SocketThread(Socket socket, Tomcat tomcat) {
    this.socket = socket;
    this.tomcat = tomcat;
  }

  @Override
  public void run() {
    try {
      InputStream in = socket.getInputStream();
      byte[] bytes = StreamUtils.streamToByteArray(in);
      System.out.println(new String(bytes));
      if (bytes.length == 0) {
        System.out.println("请求为空");
        return;
      }

      // 解析请求行
      Request request = HttpUtils.parseHeader(bytes);
      request.setSocket(socket);
      // 此处需要使用request的socket的输出流、request的http协议版本
      Response response = new Response(socket.getOutputStream());

      // 对url进行分块：web应用名、serlvet名、参数
      String[] parts = request.getRequestURL().toString().substring(1).split("/");
      //如果只有web应用名，不做操作
      if (parts.length > 1) {
        // 获取要访问的应用名
        String appName = parts[0];
        // 返回应用下的全部servlet的Context的map集合
        Context context = tomcat.getContextMap().get(appName);
        String urlPattern = parts[1];
        Servlet servlet = context.getUrlPatternMapping("/" + urlPattern);
        //找不到servlet的时候，采取DefaultServlet的操作 比如404
        if (servlet == null) {
          DefaultServlet defaultServlet = new DefaultServlet();
          defaultServlet.service(request, response);
        }else{
          servlet.service(request, response);
        }
        // 真正的发送响应
        response.complete();
        
        /*
         * 1.自定义配置文件格式 properties xml
         * 2.服务器启动的时候就加载配置文件，然后创建容器将对象都创建出来。放到容器中
         * 3.根据请求协议的请求路径通过反射区调用，对应的对象的service方法。
         * //
         */
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void init() {

  }

}
