package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.example.pojo.query.UserQuery;
import com.example.pojo.dto.UserDTO;
import com.example.pojo.vo.UserVO;


/**
* <p>
    *  服务类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
public interface UserService extends IService<User> {

/**
* 新增 
*
* @param dto 参数
*/
void addUser(UserDTO dto);

/**
* 修改 
*
* @param dto 参数
*/
void modifyUser(Long id, UserDTO dto);

/**
* 删除 
*
* @param id 主键
*/
void deleteUser(Long id);

/**
* 根据id获取  详情
*
* @param id 主键
*/
UserVO queryUserById(Long id);


/**
* 分页查询 
*
* @param query 参数
* @return
*/
Page<UserVO> pageList(UserQuery query);
    }
