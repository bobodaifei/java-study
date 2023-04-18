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
    String sql = "select gl.good_name,gl.price,gs.* FROM good gl RIGHT JOIN (select good_no,is_online,stock FROM stock where good_no=?) gs ON gl.good_no=gs.good_no";
    return stockDao.querySingle(sql, StockVO.class, good_no);
  }

  @Override
  public long selectCount(String shop_no) {
    String sql = "SELECT count(*) as total FROM stock where shop_no=?";
    return (Long) stockDao.queryScalar(sql, shop_no);
  }

  @Override
  public List<StockVO> selectPage(int begin, int pageSize, String shop_no) {
    String sql = "select gl.good_name,gl.price,gs.* FROM good gl RIGHT JOIN (select good_no,is_online,stock FROM stock where shop_no=?) gs ON gl.good_no=gs.good_no limit ?,?";
    return stockDao.queryMulti(sql, StockVO.class, shop_no, begin, pageSize);
  }

  @Override
  public int update(Stock stock_) {
    String sql = "update stock set is_online = ?, stock = ? where good_no = ?";
    return stockDao.update(sql, stock_.getIs_online(), stock_.getStock(), stock_.getGood_no());
  }

  @Override
  public int delete(String good_no) {
    String sql = "delete from stock where good_no = ?";
    return stockDao.update(sql, good_no);
  }

  @Override
  public int insert(Stock stock_) {
    String sql = "insert into stock values(?,? ,? ,? );";
    return stockDao.update(sql, stock_.getGood_no(), stock_.getShop_no(), stock_.getIs_online(), stock_.getStock());
  }

  @Override
  public List<Good> absentList(String shop_no) {
    String sql = "select good_no,good_name FROM good WHERE good_no NOT IN( select good_no FROM stock WHERE shop_no = ?)";
    return stockDao.queryMulti(sql, Good.class, shop_no);
  }

  @Override
  public List<StockVO> selectList(String shop_no) {
    String sql = "select gl.good_name,gl.price,gs.* FROM good gl RIGHT JOIN (select good_no,is_online,stock FROM stock where shop_no=?) gs ON gl.good_no=gs.good_no";
    return stockDao.queryMulti(sql, StockVO.class, shop_no);
  }
}
