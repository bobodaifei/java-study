package com.bobo.utils.aop;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import com.bobo.dao.LogDao;
import com.bobo.entity.Log_;
import com.bobo.utils.JdbcUtil;

public class MyAdvice implements Advice {

  private Log_ log_;
  private static LogDao logDao = new LogDao();

  public MyAdvice() {
  }

  public MyAdvice(int empno) {
    this.log_ = new Log_(empno);
  }

  @Override
  public void before() {
    System.out.println("前置");
    JdbcUtil.begin();
  }

  @Override
  public void after() {
    System.out.println("后置");
    // JdbcUtil.commit();
    log_.setCode("success");
  }

  @Override
  public void afterThrowing(String message) {
    System.out.println("异常");
    JdbcUtil.rollback();
    log_.setCode("error");
    log_.setMessage(message);
  }

  @Override
  public void afterReturning() {
    System.out.println("最终");
    logDao.add(log_);
    JdbcUtil.commit();
    JdbcUtil.close();
  }

  @Override
  public Object around(Object target, Method method, Object[] args) throws Throwable {
    // 环绕前
    System.out.println("环绕前");
    log_.setCreate_time(new Timestamp(System.currentTimeMillis()));
    log_.setOpe_type(method.getName());
    Object result = method.invoke(target, args);
    // 环绕后
    System.out.println("环绕后");
    return result;
  }

}
