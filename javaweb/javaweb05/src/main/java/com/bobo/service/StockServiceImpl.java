package com.bobo.service;

import java.util.List;

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
  public List<StockVO> selectPage(int begin, int pageSize, String shop_no) {
    return stockDao.selectPage(begin, pageSize, shop_no);
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
