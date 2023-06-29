package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
    import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.example.pojo.query.UserQuery;
import com.example.pojo.dto.UserDTO;
import com.example.pojo.vo.UserVO;
import com.example.component.convert.UserConvert;

import java.util.List;


/**
* <p>
    *  服务实现类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

private final UserConvert INSTANCE = UserConvert.INSTANCE;

/**
* 新增 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void addUser(UserDTO dto){
User entity = INSTANCE.toEntity(dto);
super.save(entity);
}

/**
* 修改 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void modifyUser(Long id, UserDTO dto){
User entity = INSTANCE.toEntity(dto);
entity.setId(id.toString());
super.updateById(entity);
}

/**
* 删除 
*
* @param id 主键
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void deleteUser(Long id){
super.removeById(id);
}

/**
* 根据id获取  详情
*
* @param id 主键
*/
@Override
public UserVO queryUserById(Long id){
User entity = super.getById(id);
return INSTANCE.toVO(entity);
}


/**
* 分页查询 
*
* @param query 参数
* @return
*/
@Override
public Page
<UserVO> pageList(UserQuery query) {
    Page<User> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
    new LambdaQueryWrapper<User>());
    List
    <UserVO> resultList = INSTANCE.toVOS(page.getRecords());
        return new Page
        <UserVO>().setTotal(page.getTotal()).setRecords(resultList);
            }
            }
