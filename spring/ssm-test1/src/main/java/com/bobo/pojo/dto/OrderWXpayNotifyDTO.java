package com.bobo.pojo.dto;

import lombok.Data;

@Data
public class OrderWXpayNotifyDTO {
  private String out_trade_no;//订单号

  private String result_code;//支付是否成功

  private Integer total_fee;//实付金额

  private String time_end;//支付时间

//  @Override
//  public String toString() {
//    return "WXPayVO{" +
//            "out_trade_no='" + out_trade_no + '\'' +
//            ", result_code='" + result_code + '\'' +
//            ", settlement_total_fee=" + settlement_total_fee +
//            ", time_end='" + time_end + '\'' +
//            '}';
//  }
}
