package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.OrderDetail;
import com.example.pojo.query.OrderDetailQuery;
import com.example.pojo.dto.OrderDetailDTO;
import com.example.pojo.vo.OrderDetailVO;


/**
* <p>
    *  服务类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
public interface OrderDetailService extends IService<OrderDetail> {

/**
* 新增 
*
* @param dto 参数
*/
void addOrderDetail(OrderDetailDTO dto);

/**
* 修改 
*
* @param dto 参数
*/
void modifyOrderDetail(Long id, OrderDetailDTO dto);

/**
* 删除 
*
* @param id 主键
*/
void deleteOrderDetail(Long id);

/**
* 根据id获取  详情
*
* @param id 主键
*/
OrderDetailVO queryOrderDetailById(Long id);


/**
* 分页查询 
*
* @param query 参数
* @return
*/
Page<OrderDetailVO> pageList(OrderDetailQuery query);
    }
