package com.example.service.impl;

import com.example.entity.Address;
import com.example.mapper.AddressMapper;
    import com.example.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.example.pojo.query.AddressQuery;
import com.example.pojo.dto.AddressDTO;
import com.example.pojo.vo.AddressVO;
import com.example.component.convert.AddressConvert;

import java.util.List;


/**
* <p>
    *  服务实现类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
@Slf4j
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

private final AddressConvert INSTANCE = AddressConvert.INSTANCE;

/**
* 新增 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void addAddress(AddressDTO dto){
Address entity = INSTANCE.toEntity(dto);
super.save(entity);
}

/**
* 修改 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void modifyAddress(Long id, AddressDTO dto){
Address entity = INSTANCE.toEntity(dto);
entity.setId(id.toString());
super.updateById(entity);
}

/**
* 删除 
*
* @param id 主键
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void deleteAddress(Long id){
super.removeById(id);
}

/**
* 根据id获取  详情
*
* @param id 主键
*/
@Override
public AddressVO queryAddressById(Long id){
Address entity = super.getById(id);
return INSTANCE.toVO(entity);
}


/**
* 分页查询 
*
* @param query 参数
* @return
*/
@Override
public Page
<AddressVO> pageList(AddressQuery query) {
    Page<Address> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
    new LambdaQueryWrapper<Address>());
    List
    <AddressVO> resultList = INSTANCE.toVOS(page.getRecords());
        return new Page
        <AddressVO>().setTotal(page.getTotal()).setRecords(resultList);
            }
            }
