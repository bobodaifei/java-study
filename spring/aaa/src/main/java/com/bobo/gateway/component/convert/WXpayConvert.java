package com.bobo.gateway.component.convert;

import com.bobo.gateway.entity.WXpay;
import com.bobo.gateway.pojo.dto.WXpayDTO;
import com.bobo.gateway.pojo.dto.WXpayXml1DTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WXpayConvert {

  WXpayConvert INSTANCE = Mappers.getMapper(WXpayConvert.class);

  @Mapping(target = "outTradeNo", source = "out_trade_no")
  @Mapping(target = "totalFee", source = "total_fee")
  @Mapping(target = "spbillCreateIp", source = "spbill_create_ip")
  @Mapping(target = "redirectUrl", source = "redirect_url")
  @Mapping(target = "notifyUrl", source = "notify_url")
  WXpay toEntity(WXpayDTO dto);


  @Mapping(target = "outTradeNo", source = "out_trade_no")
  @Mapping(target = "mchId", source = "mch_id")
  @Mapping(target = "resultCode", source = "result_code")
  @Mapping(target = "tradeType", source = "trade_type")
  @Mapping(target = "transactionId", source = "transaction_id")
  @Mapping(target = "settlementTotalFee", source = "settlement_total_fee")
  @Mapping(target = "timeEnd", source = "time_end")
  WXpay toEntity(WXpayXml1DTO dto);

}
