package com.bobo.pojo.query;


import com.bobo.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Order 分页参数", description = "")
public class OrderQuery extends BasePageQuery {


}
