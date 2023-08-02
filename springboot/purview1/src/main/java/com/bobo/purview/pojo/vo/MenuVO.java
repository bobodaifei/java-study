package com.bobo.purview.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO<T> {

  private Integer id;

  private Integer menuId;

  private String title;

  private String routeName;

  private String routePath;

  private Integer pid;

  private Integer sort;

  private String icon;

  private Integer level;

  private List children;

  private boolean hasMenuChildren;

}
