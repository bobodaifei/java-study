package com.bobo.purview.pojo.dto;

import lombok.Data;

@Data
public class PermissionDTO {

  private Integer id;

  private String title;

  private String func;

  private String url;

  private String type;

  private Integer menuId;

}
