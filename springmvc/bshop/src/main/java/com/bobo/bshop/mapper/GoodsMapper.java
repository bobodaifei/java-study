package com.bobo.bshop.mapper;

import com.bobo.bshop.entity.Goods;
import com.bobo.bshop.pojo.query.GoodsQuery;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface GoodsMapper {

    public List<Goods> selectPage(GoodsQuery query);

}
