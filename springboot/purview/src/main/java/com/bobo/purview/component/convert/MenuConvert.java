package com.bobo.purview.component.convert;


import com.bobo.purview.entity.Menu;
import com.bobo.purview.pojo.dto.MenuDTO;
import com.bobo.purview.pojo.vo.MenuVO;
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
public interface MenuConvert {

  MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

  @Mapping(target = "menuId", source = "id", qualifiedByName = "absolute")
  Menu toEntity(MenuDTO dto);

  @Mapping(target = "menuId", source = "id", qualifiedByName = "absolute")
  List<Menu> toEntities(List<MenuDTO> dtos);

  @Mapping(target = "id", source = "menuId", qualifiedByName = "absolute")
  @Mapping(target = "menuId", source = "menuId")
  MenuVO toVO(Menu entity);

  @Mapping(target = "id", source = "menuId", qualifiedByName = "absolute")
  @Mapping(target = "menuId", source = "menuId")
  List<MenuVO> toVOS(List<Menu> entities);

}
