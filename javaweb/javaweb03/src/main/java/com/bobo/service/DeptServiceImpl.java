package com.bobo.service;

import java.util.List;

import com.bobo.dao.DeptDao;
import com.bobo.entity.Dept;

public class DeptServiceImpl implements DeptService {
  private DeptDao deptDao = new DeptDao();

  @Override
  public List<Dept> getDeptList() {
    return deptDao.getDeptList();
  }
}
