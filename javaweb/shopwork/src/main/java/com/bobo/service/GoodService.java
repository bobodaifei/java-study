package com.bobo.service;

import java.util.List;

import com.bobo.entity.Good;

public interface GoodService {

  long selectCount();

  List<Good> selectPage(int begin, int pageSize);

  List<Good> selectPage(int begin, int pageSize, String shop_no);


  
}
