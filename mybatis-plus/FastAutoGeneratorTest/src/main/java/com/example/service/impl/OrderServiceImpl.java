package com.example.service.impl;

import com.example.entity.Order;
import com.example.mapper.OrderMapper;
    import com.example.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.example.pojo.query.OrderQuery;
import com.example.pojo.dto.OrderDTO;
import com.example.pojo.vo.OrderVO;
import com.example.component.convert.OrderConvert;

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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

private final OrderConvert INSTANCE = OrderConvert.INSTANCE;

/**
* 新增 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void addOrder(OrderDTO dto){
Order entity = INSTANCE.toEntity(dto);
super.save(entity);
}

/**
* 修改 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void modifyOrder(Long id, OrderDTO dto){
Order entity = INSTANCE.toEntity(dto);
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
public void deleteOrder(Long id){
super.removeById(id);
}

/**
* 根据id获取  详情
*
* @param id 主键
*/
@Override
public OrderVO queryOrderById(Long id){
Order entity = super.getById(id);
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
<OrderVO> pageList(OrderQuery query) {
    Page<Order> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
    new LambdaQueryWrapper<Order>());
    List
    <OrderVO> resultList = INSTANCE.toVOS(page.getRecords());
        return new Page
        <OrderVO>().setTotal(page.getTotal()).setRecords(resultList);
            }
            }
