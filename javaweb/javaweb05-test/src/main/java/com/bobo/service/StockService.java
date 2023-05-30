package com.bobo.service;

import java.util.List;

import com.bobo.common.Page;
import com.bobo.entity.Good;
import com.bobo.entity.Stock;
import com.bobo.entity.StockVO;

public interface StockService {

  StockVO selectOne(String good_no);

  <T> Page<T> selectPage(long begin, long pageSize, String shop_no);

  long selectCount(String shop_no);

  int update(Stock stock_);

  int delete(String good_no);

  int insert(Stock stock_);

  List<Good> absentList(String shop_no);

  List<StockVO> selectList(String shop_no);
}
