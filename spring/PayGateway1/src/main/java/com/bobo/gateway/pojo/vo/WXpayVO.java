package com.bobo.gateway.pojo.vo;

public class WXpayVO {

  private String result_code;//支付是否成功

  private String out_trade_no;//订单编号

  private Integer settlement_total_fee;//实付金额

  private Integer total_fee;

  private String time_end;//支付时间

  public String getResult_code() {
    return result_code;
  }

  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  public String getOut_trade_no() {
    return out_trade_no;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public Integer getSettlement_total_fee() {
    return settlement_total_fee;
  }

  public void setSettlement_total_fee(Integer settlement_total_fee) {
    this.settlement_total_fee = settlement_total_fee;
  }

  public String getTime_end() {
    return time_end;
  }

  public void setTime_end(String time_end) {
    this.time_end = time_end;
  }

  public Integer getTotal_fee() {
    return total_fee;
  }

  public void setTotal_fee(Integer total_fee) {
    this.total_fee = total_fee;
  }
}
