package com.bobo.gateway.entity;

import com.bobo.gateway.utils.JedisConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-30
 */

public class Alipay implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer orderId;

  private String outTradeNo;

  private String subject;

  private BigDecimal totalAmount;

  private String returnUrl;

  private String notifyUrl;

  private String productCode;

  private String tradeNo;

  private String appId;

  private String buyerId;

  private String sellerId;

  private String tradeStatus;

  private BigDecimal receiptAmount;

  private Date gmtCreate;

  private Date gmtPayment;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public String getRedisNo() {
    return JedisConstants.ALIPAY_PRE + ":" + outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
  }

  public BigDecimal getReceiptAmount() {
    return receiptAmount;
  }

  public void setReceiptAmount(BigDecimal receiptAmount) {
    this.receiptAmount = receiptAmount;
  }

  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public Date getGmtPayment() {
    return gmtPayment;
  }

  public void setGmtPayment(Date gmtPayment) {
    this.gmtPayment = gmtPayment;
  }
}

