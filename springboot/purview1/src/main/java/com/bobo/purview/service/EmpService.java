package com.bobo.purview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.purview.entity.Emp;

import java.util.List;

public interface EmpService extends IService<Emp> {

  public List<Emp> tree(Integer mgr);

}
