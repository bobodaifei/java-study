package com.bobo.purview.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

  private Integer userId;

  private String userName;

  private String password;

  private String roleId;

  private String token;

  private RoleVO roleVO;

  private List<MenuVO> menu;

}
