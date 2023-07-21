package com.bobo.purview.interceptors;

import com.bobo.purview.component.annotate.RequiresPermissions;
import com.bobo.purview.component.annotate.RequiresRoles;
import com.bobo.purview.entity.Role;
import com.bobo.purview.entity.User;
import com.bobo.purview.exception.CustomException;
import com.bobo.purview.service.PermissionService;
import com.bobo.purview.service.RoleService;
import com.bobo.purview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

  @Autowired
  UserService userService;

  @Autowired
  RoleService roleService;

  @Autowired
  PermissionService permissionService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String userId = (String) request.getAttribute("userId");
    User user = userService.getById(userId);
    Role role = roleService.getById(user.getRoleId());
    String roleName = role.getName();
    List<String> list = permissionService.selectPermissionListByRoleId(role.getRoleId());

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    RequiresPermissions requiresPermissions = handlerMethod.getMethod().getAnnotation(RequiresPermissions.class);
    if (requiresPermissions != null) {
      String value = requiresPermissions.value();
      // 在这里可以对注解值进行处理
      System.out.println("注解值：" + value);
      if (!list.contains(value)) {
        throw new CustomException("4001", "暂无权限");
      }
    }

    RequiresRoles requiresRoles = handlerMethod.getMethod().getAnnotation(RequiresRoles.class);
    if (requiresRoles != null) {
      String value = requiresRoles.value();
      // 在这里可以对注解值进行处理
      System.out.println("注解值：" + value);
      if (!value.equals(roleName)) {
        throw new CustomException("4001", "暂无角色");
      }
    }

    return true;

  }

}
