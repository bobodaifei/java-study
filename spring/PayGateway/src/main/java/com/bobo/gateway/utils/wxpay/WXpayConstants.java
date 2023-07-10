package com.bobo.gateway.utils.wxpay;

public interface WXpayConstants {

  public String APPID = "appid";

  public String MCH_ID = "mch_id";

  public String NONCE_STR = "nonce_str";

  public String SIGN = "sign";

  public String SIGN_TYPE = "sign_type";

  public String BODY = "body";

  public String OUT_TRADE_NO = "out_trade_no";

  public String TOTAL_FEE = "total_fee";

  public String SPBILL_CREATE_IP = "spbill_create_ip";

  public String NOTIFY_URL = "notify_url";

  public String TRADE_TYPE = "trade_type";

  public String SCENE_INFO = "scene_info";

  public String REDIRECT_URL = "redirect_url";

  public String RETURN_SUCCESS = "<xml>\n" +
          "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
          "  <return_msg><![CDATA[OK]]></return_msg>\n" +
          "</xml>";

  public String RETURN_ERROR1 = "<xml>\n" +
          "  <return_code><![CDATA[FAIL]]></return_code>\n" +
          "  <return_msg><![CDATA[签名失败]]></return_msg>\n" +
          "</xml>";

  public String RETURN_ERROR2 = "<xml>\n" +
          "  <return_code><![CDATA[FAIL]]></return_code>\n" +
          "  <return_msg><![CDATA[参数格式校验错误]]></return_msg>\n" +
          "</xml>";
}
