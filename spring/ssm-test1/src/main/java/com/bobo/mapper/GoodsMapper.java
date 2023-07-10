package com.bobo.mapper;

import com.bobo.entity.Goods;
import com.bobo.pojo.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

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


    int updateNum(@Param("goodsId") Integer id, @Param("reducedNum") Integer reducedNum);

    int updateNum1(@Param("goodsId") Integer id, @Param("stock") Integer stock, @Param("version") Integer version);


    public Goods selectById(@Param("goodsId") Integer id);

    public List<Goods> selectPage(GoodsQuery query);

}
