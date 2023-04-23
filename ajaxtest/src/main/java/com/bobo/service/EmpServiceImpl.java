package com.bobo.service;

import java.util.List;

import com.bobo.dao.EmpDao;
import com.bobo.entity.Emp;

public class EmpServiceImpl implements EmpService {

  private EmpDao userDao = new EmpDao();

  @Override
  public Emp selectOne(Emp emp) {
    String sql = "select * from emp where ename = ? and empno = ?";
    return userDao.querySingle(sql, Emp.class, emp.getEname(), emp.getEmpno());
  }

  @Override
  public List<Emp> selectPage(int begin, int pageSize) {
    String sql = "SELECT e.empno,e.ename,e.job,e.mgr,e.hiredate,IFNULL(e.sal,0) AS sal,IFNULL(e.COMM,0) AS COMM,e.deptno,IFNULL(boss.ENAME,'') AS bossName,d.dname FROM	(	SELECT *	FROM	emp ) e	LEFT JOIN emp boss ON e.MGR = boss.EMPNO LEFT JOIN dept d ON e.deptno = d.deptno limit ?,?";
    return userDao.queryMulti(sql, Emp.class, begin, pageSize);
  }

  @Override
  public Long selectCount() {
    String sql = "SELECT count(*) as total FROM emp";
    return (Long) userDao.queryScalar(sql);
  }

  @Override
  public int add(Emp emp) {
    String sql = "insert into emp(ename,job,mgr,hiredate,sal,COMM,deptno) values(?,?,?,?,?,?,?)";
    return userDao.update(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(),
        emp.getCOMM(), emp.getDeptno());
  }

  @Override
  public int delete(int empno) {
    String sql = "delete from emp where empno = ?";
    return userDao.update(sql, empno);
  }

  @Override
  public int update(Emp emp) {
    String sql = "update emp set ename = ?,job = ?,mgr = ?,hiredate = ?,sal = ?,COMM = ?,deptno = ? where empno = ?";
    return userDao.update(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(),
        emp.getCOMM(), emp.getDeptno(), emp.getEmpno());
  }

  @Override
  public List<String> getJobList() {
    String sql = "SELECT DISTINCT job FROM emp";
    return userDao.queryColumn(sql, "job");
  }

  @Override
  public List<Emp> getMgrList() {
    String sql = "SELECT DISTINCT empno,ename FROM emp";
    return userDao.queryMulti(sql, Emp.class);
  }

  @Override
  public Emp selectById(int empno) {
    String sql = "select * from emp where empno = ?";
    return userDao.querySingle(sql, Emp.class, empno);
  }

}
