package com.bobo.component.convert;


import com.bobo.entity.ShopCar;
import com.bobo.pojo.dto.ShopCarDTO;
import com.bobo.pojo.vo.ShopCarVO;
import com.bobo.utils.MapStructUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = MapStructUtil.class)
public interface ShopCarConvert {

  ShopCarConvert INSTANCE = Mappers.getMapper(ShopCarConvert.class);

  @Mapping(target = "shopCarId", source = "userId", qualifiedByName = "userIdToShopCarId")
  ShopCar toEntity(ShopCarDTO dto);

  @Mapping(target = "shopCarId", source = "userId", qualifiedByName = "userIdToShopCarId")
  List<ShopCar> toEntities(List<ShopCarDTO> dtos);

  @Mapping(target = "userId", source = "shopCarId", qualifiedByName = "shopCarIdToUserId")
  ShopCarVO toVO(ShopCar entity);

  @Mapping(target = "userId", source = "shopCarId", qualifiedByName = "shopCarIdToUserId")
  List<ShopCarVO> toVOS(List<ShopCar> entities);

}
