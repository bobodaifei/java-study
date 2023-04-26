package com.bobo.dao;

import java.util.List;

import com.bobo.entity.Dept;

public class DeptDao extends BasicDao<Dept>{

  public List<Dept> getDeptList() {
    String sql = "select distinct deptno,dname from dept";
    return queryMulti(sql, Dept.class);
  }

}
