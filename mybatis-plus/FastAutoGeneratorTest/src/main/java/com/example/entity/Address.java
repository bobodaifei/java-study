package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("address")
@ApiModel(value = "Address对象", description = "")
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("用户地址id")
  @TableId(value = "addr_id", type = IdType.ASSIGN_ID)
  private Integer addrId;

  @ApiModelProperty("用户id")
  @TableField("user_id")
  private Integer userId;

  @ApiModelProperty("详细地址")
  @TableField("addr")
  private String addr;

  @ApiModelProperty("手机号")
  @TableField("phone")
  private String phone;
}

