package com.bobo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bobo.entity.Class_;

public interface ClassMapper {
  List<Class_> findAll();
  List<Class_> findIn(@Param("listT") List<String> list);

}
