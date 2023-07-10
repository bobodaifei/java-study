package com.bobo.bshop.component.convert;


import com.bobo.bshop.entity.Goods;
import com.bobo.bshop.pojo.dto.GoodsDTO;
import com.bobo.bshop.pojo.vo.GoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */

@Mapper
public interface GoodsConvert {

  GoodsConvert INSTANCE = Mappers.getMapper(GoodsConvert.class);

  Goods toEntity(GoodsDTO dto);

  List<Goods> toEntities(List<GoodsDTO> dtos);

  GoodsVO toVO(Goods entity);

  List<GoodsVO> toVOS(List<Goods> entities);

}
