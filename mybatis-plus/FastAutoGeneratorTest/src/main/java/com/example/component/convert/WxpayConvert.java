package com.example.component.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.entity.Wxpay;
import com.example.pojo.dto.WxpayDTO;
import com.example.pojo.vo.WxpayVO;

import java.util.List;

/**
* <p>
    *  转换类
    * </p>
*
* @author bobodaifei
* @since 2023-07-03
*/

@Mapper
public interface WxpayConvert {

WxpayConvert INSTANCE = Mappers.getMapper(WxpayConvert.class);

Wxpay toEntity(WxpayDTO dto);

List<Wxpay> toEntities(List<WxpayDTO> dtos);

    WxpayVO toVO(Wxpay entity);

    List<WxpayVO> toVOS(List<Wxpay> entities);

        }
