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
* @since 2023-06-19
*/
    @Getter
    @Setter
        @Accessors(chain = true)
    @TableName("user")
    @ApiModel(value = "User对象", description = "")
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty("用户id")
                @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Integer userId;

            @ApiModelProperty("账户")
        @TableField("user_name")
    private String userName;

            @ApiModelProperty("密码")
        @TableField("password")
    private String password;
}

