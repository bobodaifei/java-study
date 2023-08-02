package com.bobo.purview.component.convert;


import com.bobo.purview.entity.Permission;
import com.bobo.purview.pojo.dto.PermissionDTO;
import com.bobo.purview.pojo.vo.PermissionVO;
import com.bobo.purview.utils.MapStructUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

@Mapper(uses = MapStructUtil.class)
public interface PermissionConvert {

  PermissionConvert INSTANCE = Mappers.getMapper(PermissionConvert.class);

  @Mapping(target = "permissionId", source = "id", qualifiedByName = "absolute")
  Permission toEntity(PermissionDTO dto);

  @Mapping(target = "permissionId", source = "id", qualifiedByName = "absolute")
  List<Permission> toEntities(List<PermissionDTO> dtos);

  @Mapping(target = "id", source = "permissionId", qualifiedByName = "negation")
  @Mapping(target = "permissionId", source = "permissionId")
  PermissionVO toVO(Permission entity);

  @Mapping(target = "id", source = "permissionId", qualifiedByName = "negation")
  @Mapping(target = "permissionId", source = "permissionId")
  List<PermissionVO> toVOS(List<Permission> entities);

}
