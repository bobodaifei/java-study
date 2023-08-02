package com.bobo.purview.pojo.vo;

import lombok.Data;

@Data
public class PermissionVO {

  private Integer id;

  private Integer permissionId;

  private String title;

  private String func;

  private String url;

  private String type;

  private Integer menuId;

}
