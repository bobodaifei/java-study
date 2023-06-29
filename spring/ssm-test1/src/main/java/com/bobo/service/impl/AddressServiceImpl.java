package com.bobo.service.impl;


import com.bobo.component.convert.AddressConvert;
import com.bobo.entity.Address;
import com.bobo.mapper.AddressMapper;
import com.bobo.pojo.dto.AddressDTO;
import com.bobo.pojo.vo.AddressVO;
import com.bobo.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  AddressMapper addressMapper;

  private final AddressConvert INSTANCE = AddressConvert.INSTANCE;

  /**
   * 新增
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int addAddress(AddressDTO dto) {
    Address entity = INSTANCE.toEntity(dto);
    return addressMapper.save(entity);
  }

  /**
   * 修改
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int modifyAddress(Integer id, AddressDTO dto) {
    Address entity = INSTANCE.toEntity(dto);
    entity.setAddrId(id);
    return addressMapper.updateById(entity);
  }

  /**
   * 删除
   *
   * @param id 主键
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int deleteById(Integer id) {
    return addressMapper.removeById(id);
  }

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  @Override
  public AddressVO selectById(Integer id) {
    Address entity = addressMapper.selectById(id);
    return INSTANCE.toVO(entity);
  }

  @Override
  public List<AddressVO> selectList(Integer userId) {
    List<Address> list = addressMapper.selectList(userId);
    return INSTANCE.toVOS(list);
  }

}
