package com.bobo.service;

import com.bobo.pojo.dto.AddressDTO;
import com.bobo.pojo.vo.AddressVO;

import java.util.List;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface AddressService {

  /**
   * 新增
   * @param dto 参数
   */
  public int addAddress(AddressDTO dto);

  /**
   * 修改
   * @param dto 参数
   */
  public int modifyAddress(Integer id, AddressDTO dto);

  /**
   * 删除
   * @param id 主键
   */
  public int deleteById(Integer id);

  /**
   * 根据id获取  详情
   * @param id 主键
   */
  public AddressVO selectById(Integer id);

  /**
   * 根据userId获取全部
   * @param userId
   */
  public List<AddressVO> selectList(Integer userId);

}
