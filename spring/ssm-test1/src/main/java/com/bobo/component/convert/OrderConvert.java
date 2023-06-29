package com.bobo.component.convert;


import com.bobo.entity.Order;
import com.bobo.pojo.dto.OrderDTO;
import com.bobo.pojo.vo.OrderVO;
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
public interface OrderConvert {

  OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

  Order toEntity(OrderDTO dto);

  List<Order> toEntities(List<OrderDTO> dtos);

  OrderVO toVO(Order entity);

  List<OrderVO> toVOS(List<Order> entities);

}
