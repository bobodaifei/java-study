package com.bobo.controller;

import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.Good;
import com.bobo.entity.Stock;
import com.bobo.entity.StockVO;
import com.bobo.entity.User;
import com.bobo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController extends BaseController {

  @Autowired
  private StockService stockService;

  @GetMapping("/toAdd")
  public Result<?> toAdd() {
    String shop_no = getUser().getShop().getShop_no();
    List<Good> res = stockService.absentList(shop_no);
    return Result.success(res);
  }

  @PutMapping("/add")
  public Result<?> add(@RequestBody Stock stock_) {
    User user = getUser();
    String shop_no = user.getShop().getShop_no();
    stock_.setShop_no(shop_no);

    int res = stockService.insert(stock_);
    if (res == 0) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @DeleteMapping("/{good_no}")
  public Result<?> delete(@PathVariable String good_no) {
    int res = stockService.delete(good_no);
    if (res == 0) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @PutMapping("/update")
  public Result<?> update(@RequestBody Stock stock_) {
    int res = stockService.update(stock_);

    if (res == 0) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @GetMapping("/selectPage")
  public Result<?> selectPage(@RequestParam(defaultValue = "1") Long currentPage,
                              @RequestParam(defaultValue = "10") Long pageSize) {
    User user = getUser();
    String shop_no = user.getShop().getShop_no();

    Page<StockVO> res = stockService.selectPage(currentPage, pageSize, shop_no);

    return Result.success(res);
  }


}
