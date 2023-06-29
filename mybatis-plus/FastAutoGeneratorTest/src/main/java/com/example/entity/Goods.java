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
    @TableName("goods")
    @ApiModel(value = "Goods对象", description = "")
    public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty("商品id")
                @TableId(value = "goods_id", type = IdType.ASSIGN_ID)
    private Integer goodsId;

            @ApiModelProperty("商品编号")
        @TableField("goods_no")
    private String goodsNo;

            @ApiModelProperty("商品名称")
        @TableField("goods_name")
    private String goodsName;

            @ApiModelProperty("价格")
        @TableField("price")
    private BigDecimal price;

            @ApiModelProperty("图片")
        @TableField("photo")
    private String photo;

            @ApiModelProperty("创建时间")
            @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

            @ApiModelProperty("修改时间")
            @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

            @ApiModelProperty("库存")
        @TableField("stock")
    private Integer stock;

            @ApiModelProperty("描述")
        @TableField("info")
    private String info;
}

