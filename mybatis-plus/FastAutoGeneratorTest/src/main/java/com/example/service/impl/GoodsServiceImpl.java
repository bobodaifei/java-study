package com.example.service.impl;

import com.example.entity.Goods;
import com.example.mapper.GoodsMapper;
    import com.example.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.example.pojo.query.GoodsQuery;
import com.example.pojo.dto.GoodsDTO;
import com.example.pojo.vo.GoodsVO;
import com.example.component.convert.GoodsConvert;

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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

private final GoodsConvert INSTANCE = GoodsConvert.INSTANCE;

/**
* 新增 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void addGoods(GoodsDTO dto){
Goods entity = INSTANCE.toEntity(dto);
super.save(entity);
}

/**
* 修改 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void modifyGoods(Long id, GoodsDTO dto){
Goods entity = INSTANCE.toEntity(dto);
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
public void deleteGoods(Long id){
super.removeById(id);
}

/**
* 根据id获取  详情
*
* @param id 主键
*/
@Override
public GoodsVO queryGoodsById(Long id){
Goods entity = super.getById(id);
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
<GoodsVO> pageList(GoodsQuery query) {
    Page<Goods> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
    new LambdaQueryWrapper<Goods>());
    List
    <GoodsVO> resultList = INSTANCE.toVOS(page.getRecords());
        return new Page
        <GoodsVO>().setTotal(page.getTotal()).setRecords(resultList);
            }
            }
