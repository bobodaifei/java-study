package com.bobo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

  private static final long serialVersionUID = 1L;

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

  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty("创建时间")
  private Date createTime;

  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty("修改时间")
  private Date updateTime;

  @ApiModelProperty("库存")
  private Integer stock;

  @ApiModelProperty("描述")
  private String info;
}

