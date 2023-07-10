package com.bobo.bshop.pojo.query;


import com.bobo.bshop.component.base.BasePageQuery;
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
public class GoodsQuery extends BasePageQuery {

  private String search;

}
