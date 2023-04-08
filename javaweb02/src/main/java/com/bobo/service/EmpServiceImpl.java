package com.bobo.service;

import java.util.List;
import java.util.Map;

import com.bobo.dao.EmpDao;
import com.bobo.entity.Emp;

public class EmpServiceImpl implements EmpService {

  private EmpDao userDao = new EmpDao();

  @Override
  public Emp login(Emp emp) {
    String sql = "select * from emp where ename = ? and empno = ?";
    return userDao.querySingle(sql, Emp.class, emp.getEname(), emp.getEmpno());
  }

  @Override
  public List<Emp> selectAll() {
    String sql = "SELECT e.*,	boss.ENAME AS bossName FROM	(	SELECT *	FROM	emp ) e	LEFT JOIN emp boss ON e.MGR = boss.EMPNO";
    return userDao.queryMulti(sql, Emp.class);
  }

}
