package com.bobo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
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

  @ApiModelProperty("商品id")
  private Integer goodsId;

  @ApiModelProperty("商品编号")
  private String goodsNo;

  @ApiModelProperty("商品名称")
  private String goodsName;

  @ApiModelProperty("价格")
  private BigDecimal price;

  @ApiModelProperty("图片")
  private String photo;

  @ApiModelProperty("创建时间")
  private Date createTime;

  @ApiModelProperty("修改时间")
  private Date updateTime;

  @ApiModelProperty("库存")
  private Integer stock;

  @ApiModelProperty("描述")
  private String info;
}

