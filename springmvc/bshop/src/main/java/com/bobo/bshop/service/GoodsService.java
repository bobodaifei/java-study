package com.bobo.bshop.service;


import com.bobo.bshop.pojo.query.GoodsQuery;
import com.bobo.bshop.pojo.vo.GoodsVO;
import com.github.pagehelper.PageInfo;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface GoodsService {

  /**
   * 分页查询
   * @param query 参数
   * @return
   */
  public PageInfo<GoodsVO> pageList(GoodsQuery query);
}
