package com.example.pojo.dto;

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
* @since 2023-07-03
*/
    @Getter
    @Setter
        @Accessors(chain = true)
    public class WxpayDTO implements Serializable {


            @ApiModelProperty("订单编号")
        private String outTradeNo;

            @ApiModelProperty("商品描述")
        private String body;

            @ApiModelProperty("总金额/分")
        private Integer totalFee;

            @ApiModelProperty("前端回调")
        private String redirectUrl;

            @ApiModelProperty("后端回调")
        private String notifyUrl;

            @ApiModelProperty("公众账号ID")
        private String appid;

            @ApiModelProperty("商户号")
        private String mchId;

            @ApiModelProperty("业务结果")
        private String resultCode;

            @ApiModelProperty("交易类型")
        private String tradeType;
}

