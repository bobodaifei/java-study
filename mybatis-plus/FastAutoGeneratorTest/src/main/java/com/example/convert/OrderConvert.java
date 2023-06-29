package com.example.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.entity.Order;
import com.example.pojo.dto.OrderDTO;
import com.example.pojo.vo.OrderVO;

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
public interface OrderConvert {

OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

Order toEntity(OrderDTO dto);

List<Order> toEntities(List<OrderDTO> dtos);

    OrderVO toVO(Order entity);

    List<OrderVO> toVOS(List<Order> entities);

        }
