package com.bobo.purview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("menu")
public class Menu {

  @TableId(type = IdType.AUTO)
  private Integer menuId;

  private String title;

  private String routeName;

  private String routePath;

  private Integer pid;

  private Integer sort;

  private String icon;

  private Integer level;

  @TableField(exist = false)
  private List children;

  @TableField(exist = false)
  private boolean hasMenuChildren;

}
