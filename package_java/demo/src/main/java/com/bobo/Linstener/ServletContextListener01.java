package com.bobo.Linstener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener01 implements ServletContextListener {

  // 服务器启动时，预先创建20个Connection,
  // 在dao方法执行时将创建好的Connection交给add方法
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // 模拟线程池
    Map map = new HashMap<>();
    // 线程池容量为20
    for (int i = 0; i < 20; i++) {
      //true为空闲，false为繁忙
      map.put("Connection连接对象", true);
    }
    
    //为了全局都可以用map对象，需要保存到全局作用域对象
    ServletContext servletContext = sce.getServletContext();
    servletContext.setAttribute("connection", map);
  }

  //服务关闭时刻，需要销毁Connection
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();
    Map map = (Map)servletContext.getAttribute("connection");
    Iterator iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
      //如果Connection不为null
      //关闭Connection
    }

  }

}
