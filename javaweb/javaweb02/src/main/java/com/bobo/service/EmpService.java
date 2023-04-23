package com.bobo.service;

import java.util.List;

import com.bobo.entity.Dept;
import com.bobo.entity.Emp;

public interface EmpService {

  public Emp login(Emp emp);

  public List<Emp> selectAll(int begin, int pageSize);

  public Long selectCount();

  public int add(Emp emp);

  public int delete(String empno);

  public int update(Emp emp);

  public List<String> getJobList();

  public List<Emp> getMgrList();

  public List<Dept> getDeptList();

  public Emp selectById(String empno);
  
}
