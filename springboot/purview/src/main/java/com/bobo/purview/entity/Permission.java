package com.bobo.purview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("permission")
public class Permission {

  @TableId(type = IdType.AUTO)
  private Integer permissionId;

  private String title;

  private String func;

  private String url;

  private String type;

  private Integer menuId;

}
