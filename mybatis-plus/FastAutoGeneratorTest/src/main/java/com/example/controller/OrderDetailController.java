package com.example.controller;

import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import com.example.aop.EnableLog;
import com.example.pojo.query.OrderDetailQuery;
import com.example.pojo.dto.OrderDetailDTO;
import com.example.pojo.vo.OrderDetailVO;
import com.example.base.Result;
import com.example.service.OrderDetailService;

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
@RequestMapping("/order-detail")
    public class OrderDetailController {
@Autowired
private OrderDetailService service;

    @ApiOperation("新增 ")
@PostMapping
@EnableLog("新增 ")
public Result<?> addOrderDetail(@Valid @RequestBody OrderDetailDTO dto) {
    service.addOrderDetail(dto);
    return Result.success();
    }

        @ApiOperation("修改 ")
    @PutMapping("/{id}")
    @EnableLog("修改 ")
    public Result<?> modifyOrderDetail(@PathVariable Long id, @Valid @RequestBody OrderDetailDTO dto) {
        service.modifyOrderDetail(id, dto);
        return Result.success();
        }

            @ApiOperation("删除 ")
        @DeleteMapping("/{id}")
        @EnableLog("删除 ")
        public Result<?> deleteOrderDetail(@PathVariable Long id) {
            service.deleteOrderDetail(id);
            return Result.success();
            }


                @ApiOperation("根据id获取  详情")
            @GetMapping("/{id}")
            @EnableLog("根据id获取  详情")
            public Result<?> queryOrderDetailById(@PathVariable Long id) {
                OrderDetailVO vo = service.queryOrderDetailById(id);
                return Result.success(vo);
                }


                    @ApiOperation("分页查询 ")
                @PostMapping("/page")
                @EnableLog("分页查询  ")
                public Result<?> pagingList(@Valid @RequestBody OrderDetailQuery query) {
                    Page<OrderDetailVO> page = service.pageList(query);
                        return Result.success(page);
                        }
                        }
