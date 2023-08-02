package com.bobo.purview.component.convert;


import com.bobo.purview.entity.User;
import com.bobo.purview.pojo.dto.UserDTO;
import com.bobo.purview.pojo.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换类
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
