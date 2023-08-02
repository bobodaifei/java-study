package com.bobo.pojo.dto;

import lombok.Data;

@Data
public class AliPayDTO {

  private String subject;// 商品名称

  private String outTradeNo;//订单编号

  private String totalAmount;//应付金额

  private String notifyUrl;//应付金额

  private String returnUrl;//应付金额

}
