package com.example.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import java.math.BigDecimal;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Getter;
    import lombok.Setter;
        import lombok.experimental.Accessors;

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
    @TableName("order_detail")
    @ApiModel(value = "OrderDetail对象", description = "")
    public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty("订单详情id")
                @TableId(value = "order_detail_id", type = IdType.ASSIGN_ID)
    private Integer orderDetailId;

            @ApiModelProperty("订单编号")
        @TableField("order_no")
    private String orderNo;

            @ApiModelProperty("商品id")
        @TableField("goods_id")
    private Integer goodsId;

            @ApiModelProperty("商品名称")
        @TableField("goods_name")
    private String goodsName;

            @ApiModelProperty("数量")
        @TableField("num")
    private Integer num;

            @ApiModelProperty("单价")
        @TableField("price")
    private BigDecimal price;
}

