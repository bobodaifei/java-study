package com.bobo.service;

import java.util.List;

import com.bobo.dao.EmpDao;
import com.bobo.entity.Emp;
import com.bobo.utils.JdbcUtil;

public class EmpServiceImpl implements EmpService {

  private EmpDao userDao = new EmpDao();

  @Override
  public Emp selectOne(Emp emp) {
    Emp res = userDao.selectOne(emp);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<Emp> selectPage(int begin, int pageSize) {
    List<Emp> res = userDao.selectPage(begin, pageSize);
    JdbcUtil.close();
    return res;
  }

  @Override
  public Long selectCount() {
    Long res = userDao.selectCount();
    JdbcUtil.close();
    return res;
  }

  @Override
  public int add(Emp emp) {
    int res = userDao.add(emp);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int delete(int empno) {
    int res = userDao.delete(empno);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int update(Emp emp) {
    int res = userDao.update(emp);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<String> getJobList() {
    List<String> res = userDao.getJobList();
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<Emp> getMgrList() {
    List<Emp> res = userDao.getMgrList();
    JdbcUtil.close();
    return res;
  }

  @Override
  public Emp selectById(int empno) {
    Emp res = userDao.selectById(empno);
    JdbcUtil.close();
    return res;
  }

}
