package com.bobo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Recharge {

  private Integer rechargeId;

  private String rechargeNo;

  private Integer payPrice;

  private Date payTime;

  private Integer userId;

  private String payMethod;

  private Integer afterMoney;

  private Integer status;


}
