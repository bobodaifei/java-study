package com.bobo.service;

import java.util.List;

import com.bobo.dao.DeptDao;
import com.bobo.entity.Dept;
import com.bobo.utils.JdbcUtil;

public class DeptServiceImpl implements DeptService {
  private DeptDao deptDao = new DeptDao();

  @Override
  public List<Dept> getDeptList() {
    List<Dept> res = deptDao.getDeptList();
    JdbcUtil.close();
    return res;
  }
}
