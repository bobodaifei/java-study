package com.bobo.purview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.purview.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper extends BaseMapper<Address> {


  public List<Address> selectAddressTreeByPid(@Param("parentId") String parentId);

}
