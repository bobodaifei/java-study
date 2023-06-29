package com.example.pojo.vo;

    import java.io.Serializable;
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
    public class OrderVO implements Serializable {

            @ApiModelProperty("订单id")
        private Integer orderId;

            @ApiModelProperty("订单编号")
        private String orderNo;

            @ApiModelProperty("归属用户id")
        private Integer userId;

            @ApiModelProperty("收货信息")
        private String addrMsg;

            @ApiModelProperty("订单状态(1 待支付、2 订单超时、3 取消订单、4 支付成功、5 待发货、6 已完成)")
        private Integer status;

            @ApiModelProperty("下单时间")
        private LocalDateTime createTime;

            @ApiModelProperty("支付时间")
        private LocalDateTime payTime;

            @ApiModelProperty("物流编号")
        private String logisticsNo;

            @ApiModelProperty("订单价格")
        private BigDecimal totalPrice;

            @ApiModelProperty("支付方式")
        private String payMethod;
}

