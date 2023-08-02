package com.bobo.purview.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.purview.component.convert.RoleConvert;
import com.bobo.purview.component.convert.UserConvert;
import com.bobo.purview.entity.Role;
import com.bobo.purview.entity.User;
import com.bobo.purview.mapper.UserMapper;
import com.bobo.purview.pojo.dto.UserDTO;
import com.bobo.purview.pojo.query.UserPage;
import com.bobo.purview.pojo.vo.MenuVO;
import com.bobo.purview.pojo.vo.RoleVO;
import com.bobo.purview.pojo.vo.UserVO;
import com.bobo.purview.service.RoleService;
import com.bobo.purview.service.UserService;
import com.bobo.purview.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  private final UserConvert INSTANCE = UserConvert.INSTANCE;

  private final RoleConvert INSTANCE1 = RoleConvert.INSTANCE;

  @Autowired
  UserMapper userMapper;

  @Autowired
  RoleService roleService;

  @Autowired
  HttpServletRequest request;

  @Override
  public UserVO login(UserDTO userDTO) {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserName, userDTO.getUserName()).eq(User::getPassword, userDTO.getPassword());
    User user = userMapper.selectOne(wrapper);
    if (user == null) {
      return null;
    }

    //角色信息
    UserVO userVO = INSTANCE.toVO(user);
    Role role = roleService.getById(user.getRoleId());
    RoleVO roleVO = INSTANCE1.toVO(role);
    userVO.setRoleVO(roleVO);

    //权限树
    List<MenuVO> menuVOList = roleService.getMenuTreeByRoleId(user.getRoleId());
    userVO.setMenu(menuVOList);

    //token
    String token = JwtUtil.sign(user.getUserId(), user.getUserName(), user.getPassword(), request.getRemoteAddr());
    userVO.setToken(token);

    return userVO;
  }

  @Override
  public boolean save(UserDTO userDTO) {
    int i = userMapper.insert(INSTANCE.toEntity(userDTO));
    return i != 0;
  }

  @Override
  public boolean update(Integer userId, UserDTO userDTO) {
    User user = INSTANCE.toEntity(userDTO);
    user.setUserId(userId);
    int i = userMapper.updateById(user);
    return i != 0;
  }

  @Override
  public Page<User> pageWithRoleName(UserPage page, Wrapper<User> queryWrapper) {
    return userMapper.selectPageWithRoleName(page,null);
  }
}
