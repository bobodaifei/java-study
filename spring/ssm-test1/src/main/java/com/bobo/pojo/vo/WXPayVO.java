package com.bobo.pojo.vo;

import lombok.Data;

@Data
public class WXPayVO {
  private String out_trade_no;//订单号
  private String payTime;//订单完成时间

  @Override
  public String toString() {
    return "WXPayVO{" +
            "out_trade_no='" + out_trade_no + '\'' +
            ", payTime=" + payTime +
            '}';
  }
}
