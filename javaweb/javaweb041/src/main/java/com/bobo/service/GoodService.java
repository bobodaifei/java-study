package com.bobo.service;

import java.util.List;

import com.bobo.entity.Good;

public interface GoodService {

  List<Good> selectPage(long begin, long pageSize, String shop_no);

  Good selectById(String good_no);

}
