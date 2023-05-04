package com.bobo.service;

import java.util.List;

import com.bobo.dao.StockDao;
import com.bobo.entity.Good;
import com.bobo.entity.Stock;
import com.bobo.entity.StockVO;
import com.bobo.utils.JdbcUtil;

public class StockServiceImpl implements StockService {
  private StockDao stockDao = new StockDao();

  @Override
  public StockVO selectOne(String good_no) {
    StockVO res = stockDao.selectOne(good_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCount(String shop_no) {
    long res = stockDao.selectCount(shop_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<StockVO> selectPage(int begin, int pageSize, String shop_no) {
    List<StockVO> res = stockDao.selectPage(begin, pageSize,shop_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int update(Stock stock_) {
    int res = stockDao.update(stock_);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int delete(String good_no) {
    int res = stockDao.delete(good_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int insert(Stock stock_) {
    int res = stockDao.insert(stock_);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<Good> absentList(String shop_no) {
    List<Good> res = stockDao.absentList(shop_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<StockVO> selectList(String shop_no) {
    List<StockVO> res = stockDao.selectList(shop_no);
    JdbcUtil.close();
    return res;
  }
}
