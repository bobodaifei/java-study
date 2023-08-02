package com.bobo.purview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.purview.entity.Address;

import java.util.List;

public interface AddressService extends IService<Address> {

  public List<Address> tree(String parentId);

}
