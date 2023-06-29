package com.bobo.component.convert;


import com.bobo.entity.OrderDetail;
import com.bobo.pojo.dto.OrderDetailDTO;
import com.bobo.pojo.vo.OrderDetailVO;
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
public interface OrderDetailConvert {

  OrderDetailConvert INSTANCE = Mappers.getMapper(OrderDetailConvert.class);

  OrderDetail toEntity(OrderDetailDTO dto);

  List<OrderDetail> toEntities(List<OrderDetailDTO> dtos);

  OrderDetailVO toVO(OrderDetail entity);

  List<OrderDetailVO> toVOS(List<OrderDetail> entities);

}
