package com.bobo.gateway.component.convert;

import com.bobo.gateway.entity.Alipay;
import com.bobo.gateway.pojo.dto.AlipayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 转换类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-30
 */

@Mapper
public interface AlipayConvert {

  AlipayConvert INSTANCE = Mappers.getMapper(AlipayConvert.class);

  @Mapping(target = "outTradeNo", source = "out_trade_no")
  @Mapping(target = "totalAmount", source = "total_amount")
  @Mapping(target = "productCode", source = "product_code")
  @Mapping(target = "returnUrl", source = "return_url")
  @Mapping(target = "notifyUrl", source = "notify_url")
  Alipay toEntity(AlipayDTO dto);


}
