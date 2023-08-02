package com.bobo.component.convert;


import com.bobo.entity.Recharge;
import com.bobo.pojo.dto.RechargeDTO;
import com.bobo.pojo.vo.RechargeVO;
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
public interface RechargeConvert {

  RechargeConvert INSTANCE = Mappers.getMapper(RechargeConvert.class);

  Recharge toEntity(RechargeDTO dto);

  List<Recharge> toEntities(List<RechargeDTO> dtos);

  RechargeVO toVO(Recharge entity);

  List<RechargeVO> toVOS(List<Recharge> entities);

}
