package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Order;
import com.example.pojo.query.OrderQuery;
import com.example.pojo.dto.OrderDTO;
import com.example.pojo.vo.OrderVO;


/**
* <p>
    *  服务类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
public interface OrderService extends IService<Order> {

/**
* 新增 
*
* @param dto 参数
*/
void addOrder(OrderDTO dto);

/**
* 修改 
*
* @param dto 参数
*/
void modifyOrder(Long id, OrderDTO dto);

/**
* 删除 
*
* @param id 主键
*/
void deleteOrder(Long id);

/**
* 根据id获取  详情
*
* @param id 主键
*/
OrderVO queryOrderById(Long id);


/**
* 分页查询 
*
* @param query 参数
* @return
*/
Page<OrderVO> pageList(OrderQuery query);
    }
