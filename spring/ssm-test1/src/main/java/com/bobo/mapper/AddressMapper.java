package com.bobo.mapper;

import com.bobo.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */

public interface AddressMapper {


  public int save(Address entity);

  public int updateById(Address entity);

  public int removeById(@Param("addrId") Integer id);

  public Address selectById(@Param("addrId") Integer id);

  public List<Address> selectList(@Param("userId") Integer userId);


}
