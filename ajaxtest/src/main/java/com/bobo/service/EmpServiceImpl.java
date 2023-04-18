package com.bobo.service;

import java.util.List;

import com.bobo.dao.DeptDao;
import com.bobo.dao.EmpDao;
import com.bobo.entity.Dept;
import com.bobo.entity.Emp;

public class EmpServiceImpl implements EmpService {

  private EmpDao userDao = new EmpDao();
  private DeptDao deptDao = new DeptDao();

  @Override
  public Emp login(Emp emp) {
    String sql = "select * from emp where ename = ? and empno = ?";
    return userDao.querySingle(sql, Emp.class, emp.getEname(), emp.getEmpno());
  }

  @Override
  public List<Emp> selectAll(int begin, int pageSize) {
    String sql = "SELECT e.*,	boss.ENAME AS bossName FROM	(	SELECT *	FROM	emp ) e	LEFT JOIN emp boss ON e.MGR = boss.EMPNO limit ?,?";
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
  public int delete(String empno) {
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
  public List<Dept> getDeptList() {
    String sql = "select distinct deptno,dname from dept";
    return deptDao.queryMulti(sql, Dept.class);
  }

  @Override
  public Emp selectById(String empno) {
    String sql = "select * from emp where empno = ?";
    return userDao.querySingle(sql, Emp.class, empno);
  }

}
