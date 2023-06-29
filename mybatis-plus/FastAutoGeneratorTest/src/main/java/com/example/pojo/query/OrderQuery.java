package com.example.pojo.query;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Getter;
    import lombok.Setter;
        import lombok.experimental.Accessors;
import com.example.base.BasePageQuery;

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
    @ApiModel(value = "Order 分页参数", description = "")
public class OrderQuery extends BasePageQuery{



}
