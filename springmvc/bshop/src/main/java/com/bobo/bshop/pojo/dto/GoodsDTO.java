package com.bobo.bshop.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Getter
@Setter
@Accessors(chain = true)
public class GoodsDTO implements Serializable {


  private String goodsNo;

  private String goodsName;

  private BigDecimal price;

  private String photo;

  private Integer stock;

  private String info;
}

