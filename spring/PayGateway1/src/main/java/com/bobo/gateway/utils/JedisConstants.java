package com.bobo.gateway.utils;

public interface JedisConstants {

  public static final String ALIPAY_PRE = "alipay";
  public static final String WXPAY_PRE = "wxpay";


  public static String alpayRedisKey(String out_trade_no) {
    return ALIPAY_PRE + ":" + out_trade_no;
  }

  public static String wxpayRedisKey(String out_trade_no) {
    return WXPAY_PRE + ":" + out_trade_no;
  }

}
