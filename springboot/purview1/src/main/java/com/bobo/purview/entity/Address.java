package com.bobo.purview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("address")
public class Address {

  @TableId(type = IdType.AUTO)
  private String id;
  private String name;
  private String parentId;
  private String level;

  @TableField(exist = false)
  private List<Address> children;

}
