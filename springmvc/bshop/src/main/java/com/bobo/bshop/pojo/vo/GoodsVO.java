package com.bobo.bshop.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Accessors(chain = true)
public class GoodsVO implements Serializable {

  private Integer goodsId;

  private String goodsNo;

  private String goodsName;

  private BigDecimal price;

  private String photo;

  private Date createTime;

  private Date updateTime;

  private Integer stock;

  private String info;

  private Integer version;

}

