package com.example.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
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
* @since 2023-07-03
*/
    @Getter
    @Setter
        @Accessors(chain = true)
    @TableName("wxpay")
    @ApiModel(value = "Wxpay对象", description = "")
    public class Wxpay implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty("订单id")
                @TableId(value = "order_id", type = IdType.ASSIGN_ID)
    private Integer orderId;

            @ApiModelProperty("订单编号")
        @TableField("out_trade_no")
    private String outTradeNo;

            @ApiModelProperty("商品描述")
        @TableField("body")
    private String body;

            @ApiModelProperty("总金额/分")
        @TableField("total_fee")
    private Integer totalFee;

            @ApiModelProperty("前端回调")
        @TableField("redirect_url")
    private String redirectUrl;

            @ApiModelProperty("后端回调")
        @TableField("notify_url")
    private String notifyUrl;

            @ApiModelProperty("公众账号ID")
        @TableField("appid")
    private String appid;

            @ApiModelProperty("商户号")
        @TableField("mch_id")
    private String mchId;

            @ApiModelProperty("业务结果")
        @TableField("result_code")
    private String resultCode;

            @ApiModelProperty("交易类型")
        @TableField("trade_type")
    private String tradeType;
}

