package com.bobo.service.impl;

import com.bobo.component.convert.ShopCarConvert;
import com.bobo.entity.ShopCar;
import com.bobo.pojo.dto.ShopCarDTO;
import com.bobo.pojo.query.ShopCarQuery;
import com.bobo.pojo.vo.GoodsVO;
import com.bobo.pojo.vo.ShopCarVO;
import com.bobo.service.GoodsService;
import com.bobo.service.ShopCarService;
import com.bobo.utils.JedisUtil;
import com.bobo.utils.MapStructUtil;
import com.bobo.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ShopCarServiceImpl implements ShopCarService {

  @Autowired
  JedisUtil jedisUtil;

  @Autowired
  PageUtil pageUtil;

  @Autowired
  GoodsService goodsService;

  private final ShopCarConvert INSTANCE = ShopCarConvert.INSTANCE;

  /**
   * 新增
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int addShopCar(ShopCarDTO dto) {
    ShopCar entity = INSTANCE.toEntity(dto);
    return jedisUtil.hincrby(entity.getShopCarId(), entity.getGoodsId().toString(), entity.getNum()).intValue();
  }

  /**
   * 修改
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int modifyShopCar(ShopCarDTO dto) {
    ShopCar entity = INSTANCE.toEntity(dto);
    return jedisUtil.hset(entity.getShopCarId(), entity.getGoodsId().toString(), entity.getNum().toString()).intValue();
  }

  /**
   * 删除
   *
   * @param dto 按条件删除
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int delete(ShopCarDTO dto) {
    ShopCar entity = INSTANCE.toEntity(dto);
    return jedisUtil.hdel(entity.getShopCarId(), entity.getGoodsId().toString()).intValue();
  }

  /**
   * 查看列表
   *
   * @param idArr 按条件删除
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<ShopCarVO> selectByIds(Integer userId, String[] idArr) {
    String shopCarId = MapStructUtil.userIdToShopCarId(userId);
    List<ShopCarVO> list = new ArrayList<>();
    for(String id : idArr){
      Long num = Long.valueOf(jedisUtil.hget(shopCarId, id));
      GoodsVO goodsVO = goodsService.selectById(Integer.valueOf(id));
      ShopCarVO shopCarVO = new ShopCarVO(userId, Integer.valueOf(id), num, goodsVO);
      list.add(shopCarVO);
    }
    return list;
  }

  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  @Override
  public PageInfo<ShopCarVO> pageList(ShopCarQuery query) {
    PageInfo<ShopCarVO> pageInfo = new PageInfo<>();
    long total = jedisUtil.hlen(query.getshopCarId());
    pageInfo.setTotal(total);
    if (total == 0) {
      return pageInfo;
    }
    String shopCarId = query.getshopCarId();
    int pageSize = query.getPageSize();
    int pageNum = query.getPageNum();
    PageUtil.Page page = pageUtil.pageAdapt(pageNum, pageSize, total);
    List<String> fields = new ArrayList<>(jedisUtil.hkeys(shopCarId));
    List<ShopCarVO> list = new ArrayList<>();
    Iterator<String> iterator = fields.subList(page.getStart(), page.getEnd()).iterator();
    while (iterator.hasNext()) {
      String goodsId = iterator.next();
      GoodsVO goodsVO = goodsService.selectById(Integer.valueOf(goodsId));
      Long num = Long.valueOf(jedisUtil.hget(shopCarId, goodsId));
      ShopCarVO shopCarVO = new ShopCarVO(query.getUserId(), Integer.valueOf(goodsId), num, goodsVO);
      list.add(shopCarVO);
    }
    pageInfo.setList(list);
    pageInfo.setPageNum(page.getPageNum());
    pageInfo.setPageSize(page.getPageSize());
    return pageInfo;
  }
}
