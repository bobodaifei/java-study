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
    public class AddressVO implements Serializable {

            @ApiModelProperty("用户地址id")
        private Integer addrId;

            @ApiModelProperty("用户id")
        private Integer userId;

            @ApiModelProperty("详细地址")
        private String addr;

            @ApiModelProperty("手机号")
        private String phone;
}

