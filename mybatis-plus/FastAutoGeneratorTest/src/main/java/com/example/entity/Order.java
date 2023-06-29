package com.example.entity;

    import com.baomidou.mybatisplus.annotation.FieldFill;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import java.math.BigDecimal;
    import java.time.LocalDateTime;
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
    @TableName("order")
    @ApiModel(value = "Order对象", description = "")
    public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty("订单id")
                @TableId(value = "order_id", type = IdType.ASSIGN_ID)
    private Integer orderId;

            @ApiModelProperty("订单编号")
        @TableField("order_no")
    private String orderNo;

            @ApiModelProperty("归属用户id")
        @TableField("user_id")
    private Integer userId;

            @ApiModelProperty("收货信息")
        @TableField("addr_msg")
    private String addrMsg;

            @ApiModelProperty("订单状态(1 待支付、2 订单超时、3 取消订单、4 支付成功、5 待发货、6 已完成)")
        @TableField("status")
    private Integer status;

            @ApiModelProperty("下单时间")
            @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

            @ApiModelProperty("支付时间")
        @TableField("pay_time")
    private LocalDateTime payTime;

            @ApiModelProperty("物流编号")
        @TableField("logistics_no")
    private String logisticsNo;

            @ApiModelProperty("订单价格")
        @TableField("total_price")
    private BigDecimal totalPrice;

            @ApiModelProperty("支付方式")
        @TableField("pay_method")
    private String payMethod;
}

