package com.bobo.bshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Alias("Goods")
@Accessors(chain = true)
public class Goods implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer goodsId;

  private String goodsNo;

  private String goodsName;

  private BigDecimal price;

  private String photo;

  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

  private Integer stock;

  private String info;
}

