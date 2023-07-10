package com.bobo.bshop.service.impl;


import com.bobo.bshop.component.convert.GoodsConvert;
import com.bobo.bshop.mapper.GoodsMapper;
import com.bobo.bshop.pojo.query.GoodsQuery;
import com.bobo.bshop.pojo.vo.GoodsVO;
import com.bobo.bshop.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    private final GoodsConvert INSTANCE = GoodsConvert.INSTANCE;


    /**
     * 分页查询
     *
     * @param query 参数
     * @return
     */
    @Override
    public PageInfo<GoodsVO> pageList(GoodsQuery query) {
        Page page = PageHelper.startPage(query.getPageNum(), query.getPageSize());
        PageInfo<GoodsVO> pageInfo = new PageInfo<>(INSTANCE.toVOS(goodsMapper.selectPage(query)));
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
