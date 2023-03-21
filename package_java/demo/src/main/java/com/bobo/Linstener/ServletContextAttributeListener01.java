package com.bobo.Linstener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextAttributeListener01 implements ServletContextAttributeListener {

  @Override
  public void attributeAdded(ServletContextAttributeEvent scae) {
    System.out.println("新增共享数据");
  }

  @Override
  public void attributeRemoved(ServletContextAttributeEvent scae) {
    System.out.println("删除共享数据");
  }

  @Override
  public void attributeReplaced(ServletContextAttributeEvent scae) {
    System.out.println("更新共享数据");
  }
  
}
