package com.example.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.entity.Goods;
import com.example.pojo.dto.GoodsDTO;
import com.example.pojo.vo.GoodsVO;

import java.util.List;

/**
* <p>
    *  转换类
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
