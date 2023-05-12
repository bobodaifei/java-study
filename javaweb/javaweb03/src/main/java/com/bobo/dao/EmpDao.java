package com.bobo.dao;

import java.util.List;

import com.bobo.dao.EmpDao;
import com.bobo.entity.Emp;

public class EmpDao extends BasicDao<Emp>{

  public Emp selectOne(Emp emp) {
    String sql = "select * from emp where ename = ? and empno = ?";
    return querySingle(sql, Emp.class, emp.getEname(), emp.getEmpno());
  }

  public List<Emp> selectPage(long begin, long pageSize) {
    String sql = "SELECT e.empno,e.ename,e.job,e.mgr,e.hiredate,IFNULL(e.sal,0) AS sal,IFNULL(e.COMM,0) AS COMM,e.deptno,IFNULL(boss.ENAME,'') AS bossName,d.dname FROM	(	SELECT *	FROM	emp ) e	LEFT JOIN emp boss ON e.MGR = boss.EMPNO LEFT JOIN dept d ON e.deptno = d.deptno limit ?,?";
    return queryMulti(sql, Emp.class, begin, pageSize);
  }

  public Long selectCount() {
    String sql = "SELECT count(*) as total FROM emp";
    return (Long) queryScalar(sql);
  }

  public int add(Emp emp) {
    String sql = "insert into emp(ename,job,mgr,hiredate,sal,COMM,deptno) values(?,?,?,?,?,?,?)";
    return update(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(),
        emp.getCOMM(), emp.getDeptno());
  }

  public int delete(int empno) {
    String sql = "delete from emp where empno = ?";
    return update(sql, empno);
  }

  public int update(Emp emp) {
    String sql = "update emp set ename = ?,job = ?,mgr = ?,hiredate = ?,sal = ?,COMM = ?,deptno = ? where empno = ?";
    return update(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(),
        emp.getCOMM(), emp.getDeptno(), emp.getEmpno());
  }

  public List<String> getJobList() {
    String sql = "SELECT DISTINCT job FROM emp";
    return queryColumn(sql, "job");
  }

  public List<Emp> getMgrList() {
    String sql = "SELECT DISTINCT empno,ename FROM emp";
    return queryMulti(sql, Emp.class);
  }

  public Emp selectById(int empno) {
    String sql = "select * from emp where empno = ?";
    return querySingle(sql, Emp.class, empno);
  }
}
