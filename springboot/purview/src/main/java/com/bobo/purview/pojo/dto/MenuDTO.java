package com.bobo.purview.pojo.dto;

import lombok.Data;

@Data
public class MenuDTO {

  private Integer id;

  private String title;

  private String routeName;

  private String routePath;

  private Integer pid;

  private Integer sort;

  private String icon;

  private Integer level;
  
}
