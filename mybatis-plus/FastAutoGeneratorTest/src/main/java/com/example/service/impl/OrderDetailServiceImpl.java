package com.example.service.impl;

import com.example.entity.OrderDetail;
import com.example.mapper.OrderDetailMapper;
    import com.example.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.example.pojo.query.OrderDetailQuery;
import com.example.pojo.dto.OrderDetailDTO;
import com.example.pojo.vo.OrderDetailVO;
import com.example.component.convert.OrderDetailConvert;

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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

private final OrderDetailConvert INSTANCE = OrderDetailConvert.INSTANCE;

/**
* 新增 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void addOrderDetail(OrderDetailDTO dto){
OrderDetail entity = INSTANCE.toEntity(dto);
super.save(entity);
}

/**
* 修改 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void modifyOrderDetail(Long id, OrderDetailDTO dto){
OrderDetail entity = INSTANCE.toEntity(dto);
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
public void deleteOrderDetail(Long id){
super.removeById(id);
}

/**
* 根据id获取  详情
*
* @param id 主键
*/
@Override
public OrderDetailVO queryOrderDetailById(Long id){
OrderDetail entity = super.getById(id);
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
<OrderDetailVO> pageList(OrderDetailQuery query) {
    Page<OrderDetail> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
    new LambdaQueryWrapper<OrderDetail>());
    List
    <OrderDetailVO> resultList = INSTANCE.toVOS(page.getRecords());
        return new Page
        <OrderDetailVO>().setTotal(page.getTotal()).setRecords(resultList);
            }
            }
