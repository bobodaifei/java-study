package com.bobo.gateway.pojo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXpayXml1DTO {


  private String out_trade_no;
  private String appid;
  private String mch_id;
  private String result_code;
  private String trade_type;
  private String transaction_id;
  private String openid;
  private Integer total_fee;
  private int settlement_total_fee;
  private String time_end;

  public String getOut_trade_no() {
    return out_trade_no;
  }

  public String getRedisNo() {
    return "wxpay:" + out_trade_no;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getMch_id() {
    return mch_id;
  }

  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }

  public String getResult_code() {
    return result_code;
  }

  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  public String getTrade_type() {
    return trade_type;
  }

  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }

  public String getTransaction_id() {
    return transaction_id;
  }

  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public Integer getTotal_fee() {
    return total_fee;
  }

  public void setTotal_fee(Integer total_fee) {
    this.total_fee = total_fee;
  }

  public int getSettlement_total_fee() {
    return settlement_total_fee;
  }

  public void setSettlement_total_fee(int settlement_total_fee) {
    this.settlement_total_fee = settlement_total_fee;
  }

  public String getTime_end() {
    return time_end;
  }

  public void setTime_end(String time_end) {
    this.time_end = time_end;
  }
}
