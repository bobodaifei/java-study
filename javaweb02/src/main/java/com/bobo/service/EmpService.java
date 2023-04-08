package com.bobo.service;

import java.util.List;

import com.bobo.entity.Emp;

public interface EmpService {

  public Emp login(Emp emp);

  public List<Emp> selectAll();
  
}
