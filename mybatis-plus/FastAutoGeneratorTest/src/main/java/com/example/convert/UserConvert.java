package com.example.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.entity.User;
import com.example.pojo.dto.UserDTO;
import com.example.pojo.vo.UserVO;

import java.util.List;

/**
* <p>
    *  转换类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/

@Mapper
public interface UserConvert {

UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

User toEntity(UserDTO dto);

List<User> toEntities(List<UserDTO> dtos);

    UserVO toVO(User entity);

    List<UserVO> toVOS(List<User> entities);

        }
