package com.bobo.component.convert;

import com.bobo.entity.WXPay;
import com.bobo.pojo.dto.WXPayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WXPayConvert {

  WXPayConvert INSTANCE = Mappers.getMapper(WXPayConvert.class);

  WXPay toEntity(WXPayDTO dto);

}
