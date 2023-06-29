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
    public class GoodsVO implements Serializable {

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

            @ApiModelProperty("创建时间")
        private LocalDateTime createTime;

            @ApiModelProperty("修改时间")
        private LocalDateTime updateTime;

            @ApiModelProperty("库存")
        private Integer stock;

            @ApiModelProperty("描述")
        private String info;
}

