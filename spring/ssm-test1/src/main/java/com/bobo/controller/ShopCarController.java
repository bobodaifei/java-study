package com.bobo.controller;


import com.bobo.aop.EnableLog;
import com.bobo.base.Result;
import com.bobo.pojo.dto.ShopCarDTO;
import com.bobo.pojo.query.ShopCarQuery;
import com.bobo.pojo.vo.ShopCarVO;
import com.bobo.service.ShopCarService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Api(value = " API", tags = "")
@RestController
@RequestMapping("/shopCar")
public class ShopCarController extends BaseController {

  @Autowired
  private ShopCarService shopCarService;

  @ApiOperation("新增 ")
  @PostMapping
  @EnableLog("新增 ")
  public Result<?> addShopCar( @RequestBody ShopCarDTO dto) {
    dto.setUserId(getUser().getUserId());
    shopCarService.addShopCar(dto);
    return Result.success();
  }

  @ApiOperation("修改 ")
  @PutMapping
  @EnableLog("修改 ")
  public Result<?> modifyShopCar( @RequestBody ShopCarDTO dto) {
    dto.setUserId(getUser().getUserId());
    shopCarService.modifyShopCar(dto);
    return Result.success();
  }

  @ApiOperation("删除 ")
  @DeleteMapping("/{goodsId}")
  @EnableLog("删除 ")
  public Result<?> deleteShopCar(@PathVariable Integer goodsId) {
    ShopCarDTO dto = new ShopCarDTO(getUser().getUserId(), goodsId);
    shopCarService.delete(dto);
    return Result.success();
  }

  @ApiOperation("根据ids获取  详情")
  @PostMapping("/getByIds")
  @EnableLog("根据id获取  详情")
  public Result<?> queryShopCarByIds(@RequestBody ShopCarDTO dto) {
    String[] idArr = dto.getGoodsIds().split(",");
    Integer userId = getUser().getUserId();
    List<ShopCarVO> vo = shopCarService.selectByIds(userId, idArr);
    return Result.success(vo);
  }

  @ApiOperation("分页查询 ")
  @PostMapping("/page")
  @EnableLog("分页查询  ")
  public Result<?> pagingList( @RequestBody ShopCarQuery query) {
    query.setUserId(getUser().getUserId());
    PageInfo<ShopCarVO> page = shopCarService.pageList(query);
    return Result.success(page);
  }
}