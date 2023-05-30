package com.bobo.service;

import java.util.List;

import com.bobo.common.Page;
import com.bobo.dao.StockDao;
import com.bobo.entity.Good;
import com.bobo.entity.Stock;
import com.bobo.entity.StockVO;

public class StockServiceImpl implements StockService {
  private StockDao stockDao = new StockDao();

  @Override
  public StockVO selectOne(String good_no) {
    return stockDao.selectOne(good_no);
  }

  @Override
  public long selectCount(String shop_no) {
    return stockDao.selectCount(shop_no);
  }

  @Override
  public Page<StockVO> selectPage(long currentPage, long pageSize, String shop_no) {
    long total = selectCount(shop_no);

    long begin = (currentPage - 1) * pageSize;
    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<StockVO> res = stockDao.selectPage(begin, pageSize, shop_no);
    
    return new Page<StockVO>(res, total, pageSize, currentPage);
  }

  @Override
  public int update(Stock stock_) {
    return stockDao.update(stock_);
  }

  @Override
  public int delete(String good_no) {
    return stockDao.delete(good_no);
  }

  @Override
  public int insert(Stock stock_) {
    return stockDao.insert(stock_);
  }

  @Override
  public List<Good> absentList(String shop_no) {
    return stockDao.absentList(shop_no);
  }

  @Override
  public List<StockVO> selectList(String shop_no) {
    return stockDao.selectList(shop_no);
  }
}
