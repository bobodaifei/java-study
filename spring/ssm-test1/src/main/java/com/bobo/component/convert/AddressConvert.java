package com.bobo.component.convert;


import com.bobo.entity.Address;
import com.bobo.pojo.dto.AddressDTO;
import com.bobo.pojo.vo.AddressVO;
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
public interface AddressConvert {

  AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

  Address toEntity(AddressDTO dto);

  List<Address> toEntities(List<AddressDTO> dtos);

  AddressVO toVO(Address entity);

  List<AddressVO> toVOS(List<Address> entities);

}
