package com.bobo.service;

import java.util.List;

import com.bobo.entity.Emp;

public interface EmpService {

  public Emp selectOne(Emp emp);

  public List<Emp> selectPage(long begin, long pageSize);

  public Long selectCount();

  public int add(Emp emp);

  public int delete(int empno);

  public int update(Emp emp);

  public List<String> getJobList();

  public List<Emp> getMgrList();

  public Emp selectById(int empno);
  
}
