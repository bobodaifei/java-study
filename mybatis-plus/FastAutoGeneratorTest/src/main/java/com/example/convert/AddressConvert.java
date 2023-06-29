package com.example.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.entity.Address;
import com.example.pojo.dto.AddressDTO;
import com.example.pojo.vo.AddressVO;

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
public interface AddressConvert {

AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

Address toEntity(AddressDTO dto);

List<Address> toEntities(List<AddressDTO> dtos);

    AddressVO toVO(Address entity);

    List<AddressVO> toVOS(List<Address> entities);

        }
