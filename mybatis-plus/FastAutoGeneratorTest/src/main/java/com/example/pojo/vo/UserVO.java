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
    public class UserVO implements Serializable {

            @ApiModelProperty("用户id")
        private Integer userId;

            @ApiModelProperty("账户")
        private String userName;

            @ApiModelProperty("密码")
        private String password;
}

