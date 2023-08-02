package com.bobo.purview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.purview.entity.Address;
import com.bobo.purview.mapper.AddressMapper;
import com.bobo.purview.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

  @Autowired
  AddressMapper addressMapper;

  @Override
  public List<Address> tree(String parentId) {
    return addressMapper.selectAddressTreeByPid(parentId);
  }
}
