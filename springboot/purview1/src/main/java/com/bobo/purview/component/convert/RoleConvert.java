package com.bobo.purview.component.convert;


import com.bobo.purview.entity.Role;
import com.bobo.purview.pojo.dto.RoleDTO;
import com.bobo.purview.pojo.vo.RoleVO;
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
public interface RoleConvert {

  RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

  Role toEntity(RoleDTO dto);

  List<Role> toEntities(List<RoleDTO> dtos);

  RoleVO toVO(Role entity);

  List<RoleVO> toVOS(List<Role> entities);

}
