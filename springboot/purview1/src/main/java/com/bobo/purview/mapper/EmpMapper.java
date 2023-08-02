package com.bobo.purview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.purview.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper extends BaseMapper<Emp> {

  public List<Emp> selectEmpTreeByPid(@Param("mgr") Integer mgr);

}
