package com.bobo.bshop.controller;


import com.bobo.bshop.component.base.Result;
import com.bobo.bshop.pojo.query.GoodsQuery;
import com.bobo.bshop.pojo.vo.GoodsVO;
import com.bobo.bshop.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

  @Autowired
  private GoodsService goodsService;

  @PostMapping("/page")
  public Result<?> pagingList(@RequestBody GoodsQuery query) {
    PageInfo<GoodsVO> page = goodsService.pageList(query);
    return Result.success(page);
  }
}
