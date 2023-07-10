package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Wxpay;
import com.example.pojo.query.WxpayQuery;
import com.example.pojo.dto.WxpayDTO;
import com.example.pojo.vo.WxpayVO;


/**
* <p>
    *  服务类
    * </p>
*
* @author bobodaifei
* @since 2023-07-03
*/
public interface WxpayService extends IService<Wxpay> {

/**
* 新增 
*
* @param dto 参数
*/
void addWxpay(WxpayDTO dto);

/**
* 修改 
*
* @param dto 参数
*/
void modifyWxpay(Long id, WxpayDTO dto);

/**
* 删除 
*
* @param id 主键
*/
void deleteWxpay(Long id);

/**
* 根据id获取  详情
*
* @param id 主键
*/
WxpayVO queryWxpayById(Long id);


/**
* 分页查询 
*
* @param query 参数
* @return
*/
Page<WxpayVO> pageList(WxpayQuery query);
    }
