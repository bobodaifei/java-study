package com.bobo.pojo.query;


import com.bobo.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@ApiModel(value = "Goods 分页参数", description = "")
public class GoodsQuery extends BasePageQuery {

  @ApiModelProperty("查询条件")
  private String search;

}
