package com.bobo.service;

import java.util.List;

import com.bobo.dao.EmpDao;
import com.bobo.entity.Emp;

public class EmpServiceImpl implements EmpService {

  private EmpDao userDao = new EmpDao();

  @Override
  public Emp selectOne(Emp emp) {
    return userDao.selectOne(emp);
  }

  @Override
  public List<Emp> selectPage(int begin, int pageSize) {
    return userDao.selectPage(begin, pageSize);
  }

  @Override
  public Long selectCount() {
    return userDao.selectCount();
  }

  @Override
  public int add(Emp emp) {
    return userDao.add(emp);
  }

  @Override
  public int delete(int empno) {
    int res = userDao.delete(empno);
    int i = 1 / 0;
    return res;
    
  }

  @Override
  public int update(Emp emp) {
    return userDao.update(emp);
  }

  @Override
  public List<String> getJobList() {
    return userDao.getJobList();
  }

  @Override
  public List<Emp> getMgrList() {
    return userDao.getMgrList();
  }

  @Override
  public Emp selectById(int empno) {
    return userDao.selectById(empno);
  }

}
