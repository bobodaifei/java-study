package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Goods;
import com.example.pojo.query.GoodsQuery;
import com.example.pojo.dto.GoodsDTO;
import com.example.pojo.vo.GoodsVO;


/**
* <p>
    *  服务类
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
public interface GoodsService extends IService<Goods> {

/**
* 新增 
*
* @param dto 参数
*/
void addGoods(GoodsDTO dto);

/**
* 修改 
*
* @param dto 参数
*/
void modifyGoods(Long id, GoodsDTO dto);

/**
* 删除 
*
* @param id 主键
*/
void deleteGoods(Long id);

/**
* 根据id获取  详情
*
* @param id 主键
*/
GoodsVO queryGoodsById(Long id);


/**
* 分页查询 
*
* @param query 参数
* @return
*/
Page<GoodsVO> pageList(GoodsQuery query);
    }
