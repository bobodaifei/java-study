package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Address;
import com.example.pojo.query.AddressQuery;
import com.example.pojo.dto.AddressDTO;
import com.example.pojo.vo.AddressVO;


/**
* <p>
    *  服务类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
public interface AddressService extends IService<Address> {

/**
* 新增 
*
* @param dto 参数
*/
void addAddress(AddressDTO dto);

/**
* 修改 
*
* @param dto 参数
*/
void modifyAddress(Long id, AddressDTO dto);

/**
* 删除 
*
* @param id 主键
*/
void deleteAddress(Long id);

/**
* 根据id获取  详情
*
* @param id 主键
*/
AddressVO queryAddressById(Long id);


/**
* 分页查询 
*
* @param query 参数
* @return
*/
Page<AddressVO> pageList(AddressQuery query);
    }
