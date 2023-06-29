package com.bobo.service;


import com.bobo.pojo.query.GoodsQuery;
import com.bobo.pojo.vo.GoodsVO;
import com.github.pagehelper.PageInfo;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface GoodsService {

  /**
   * 根据修改库存
   * @param id 主键
   * @param reducedNum 减少的数量
   */
  public int modifyGoodsStock(Integer id, Integer reducedNum);

  /**
   * 根据id获取  详情
   * @param id 主键
   */
  public GoodsVO selectById(Integer id);

  /**
   * 分页查询
   * @param query 参数
   * @return
   */
  public PageInfo<GoodsVO> pageList(GoodsQuery query);
}
