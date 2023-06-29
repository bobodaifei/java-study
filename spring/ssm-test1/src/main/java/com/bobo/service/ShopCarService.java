package com.bobo.service;

import com.bobo.pojo.dto.ShopCarDTO;
import com.bobo.pojo.query.ShopCarQuery;
import com.bobo.pojo.vo.ShopCarVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShopCarService {

  /**
   * 新增
   *
   * @param dto 参数
   */
  public int addShopCar(ShopCarDTO dto);

  /**
   * 修改
   *
   * @param dto 参数
   */
  public int modifyShopCar(ShopCarDTO dto);

  /**
   * 删除
   *
   * @param dto 主键
   */
  public int delete(ShopCarDTO dto);

  /**
   * 多个id查询
   *
   * @param idArr 参数
   * @return
   */
  List<ShopCarVO> selectByIds(Integer userId, String[] idArr);

  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  public PageInfo<ShopCarVO> pageList(ShopCarQuery query);


}
