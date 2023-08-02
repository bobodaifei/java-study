package com.bobo.purview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.purview.entity.Emp;
import com.bobo.purview.mapper.EmpMapper;
import com.bobo.purview.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

  @Autowired
  EmpMapper empMapper;

  @Override
  public List<Emp> tree(Integer mgr) {
    return empMapper.selectEmpTreeByPid(mgr);
  }
}
