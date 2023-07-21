package com.bobo.purview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

  @TableId(type = IdType.AUTO)
  private Integer userId;

  private String userName;

  private String password;

  private Integer roleId;

  @TableField(exist = false)
  private String name;

}
