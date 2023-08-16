package com.bobo.gateway.pojo.dto;

import com.bobo.gateway.utils.JedisConstants;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-30
 */

public class AlipayDTO implements Serializable {

  private String subject;// 商品名称
  private String out_trade_no;//订单编号
  private BigDecimal total_amount;//应付金额
  private String product_code;//支付场景
  private String return_url;
  private String notify_url;

  private String quit_url;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getOut_trade_no() {
    return out_trade_no;
  }

  public String getRedisNo() {
    return JedisConstants.ALIPAY_PRE + ":" + out_trade_no;
  }


  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public BigDecimal getTotal_amount() {
    return total_amount;
  }

  public void setTotal_amount(BigDecimal total_amount) {
    this.total_amount = total_amount;
  }

  public String getProduct_code() {
    return product_code;
  }

  public void setProduct_code(String product_code) {
    this.product_code = product_code;
  }

  public String getReturn_url() {
    return return_url;
  }

  public void setReturn_url(String return_url) {
    this.return_url = return_url;
  }

  public String getNotify_url() {
    return notify_url;
  }

  public void setNotify_url(String notify_url) {
    this.notify_url = notify_url;
  }

  public String getQuit_url() {
    return quit_url;
  }

  public void setQuit_url(String quit_url) {
    this.quit_url = quit_url;
  }
}

