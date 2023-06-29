package com.bobo.component.convert;

import com.bobo.entity.AliPay;
import com.bobo.pojo.dto.AliPayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AliPayConvert {
  AliPayConvert INSTANCE = Mappers.getMapper(AliPayConvert.class);

  @Mapping(target = "out_trade_no", source = "outTradeNo")
  @Mapping(target = "total_amount", source = "totalAmount")
  AliPay toEntity(AliPayDTO dto);

}
