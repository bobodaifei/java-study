package com.example.service.impl;

import com.example.entity.Wxpay;
import com.example.mapper.WxpayMapper;
    import com.example.service.WxpayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.example.pojo.query.WxpayQuery;
import com.example.pojo.dto.WxpayDTO;
import com.example.pojo.vo.WxpayVO;
import com.example.component.convert.WxpayConvert;

import java.util.List;


/**
* <p>
    *  服务实现类
    * </p>
*
* @author bobodaifei
* @since 2023-07-03
*/
@Slf4j
@Service
public class WxpayServiceImpl extends ServiceImpl<WxpayMapper, Wxpay> implements WxpayService {

private final WxpayConvert INSTANCE = WxpayConvert.INSTANCE;

/**
* 新增 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void addWxpay(WxpayDTO dto){
Wxpay entity = INSTANCE.toEntity(dto);
super.save(entity);
}

/**
* 修改 
*
* @param dto 参数
*/
@Override
@Transactional(rollbackFor = Exception.class)
public void modifyWxpay(Long id, WxpayDTO dto){
Wxpay entity = INSTANCE.toEntity(dto);
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
public void deleteWxpay(Long id){
super.removeById(id);
}

/**
* 根据id获取  详情
*
* @param id 主键
*/
@Override
public WxpayVO queryWxpayById(Long id){
Wxpay entity = super.getById(id);
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
<WxpayVO> pageList(WxpayQuery query) {
    Page<Wxpay> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
    new LambdaQueryWrapper<Wxpay>());
    List
    <WxpayVO> resultList = INSTANCE.toVOS(page.getRecords());
        return new Page
        <WxpayVO>().setTotal(page.getTotal()).setRecords(resultList);
            }
            }
