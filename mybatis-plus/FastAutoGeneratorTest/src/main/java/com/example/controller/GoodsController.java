package com.example.controller;

import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import com.example.aop.EnableLog;
import com.example.pojo.query.GoodsQuery;
import com.example.pojo.dto.GoodsDTO;
import com.example.pojo.vo.GoodsVO;
import com.example.base.Result;
import com.example.service.GoodsService;

import java.util.List;
/**
* <p>
    *  前端控制器
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
    @Api(value = " API", tags = "")
    @RestController
@RequestMapping("/goods")
    public class GoodsController {
@Autowired
private GoodsService service;

    @ApiOperation("新增 ")
@PostMapping
@EnableLog("新增 ")
public Result<?> addGoods(@Valid @RequestBody GoodsDTO dto) {
    service.addGoods(dto);
    return Result.success();
    }

        @ApiOperation("修改 ")
    @PutMapping("/{id}")
    @EnableLog("修改 ")
    public Result<?> modifyGoods(@PathVariable Long id, @Valid @RequestBody GoodsDTO dto) {
        service.modifyGoods(id, dto);
        return Result.success();
        }

            @ApiOperation("删除 ")
        @DeleteMapping("/{id}")
        @EnableLog("删除 ")
        public Result<?> deleteGoods(@PathVariable Long id) {
            service.deleteGoods(id);
            return Result.success();
            }


                @ApiOperation("根据id获取  详情")
            @GetMapping("/{id}")
            @EnableLog("根据id获取  详情")
            public Result<?> queryGoodsById(@PathVariable Long id) {
                GoodsVO vo = service.queryGoodsById(id);
                return Result.success(vo);
                }


                    @ApiOperation("分页查询 ")
                @PostMapping("/page")
                @EnableLog("分页查询  ")
                public Result<?> pagingList(@Valid @RequestBody GoodsQuery query) {
                    Page<GoodsVO> page = service.pageList(query);
                        return Result.success(page);
                        }
                        }
