package com.example.controller;

import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import com.example.aop.EnableLog;
import com.example.pojo.query.OrderQuery;
import com.example.pojo.dto.OrderDTO;
import com.example.pojo.vo.OrderVO;
import com.example.base.Result;
import com.example.service.OrderService;

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
@RequestMapping("/order")
    public class OrderController {
@Autowired
private OrderService service;

    @ApiOperation("新增 ")
@PostMapping
@EnableLog("新增 ")
public Result<?> addOrder(@Valid @RequestBody OrderDTO dto) {
    service.addOrder(dto);
    return Result.success();
    }

        @ApiOperation("修改 ")
    @PutMapping("/{id}")
    @EnableLog("修改 ")
    public Result<?> modifyOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO dto) {
        service.modifyOrder(id, dto);
        return Result.success();
        }

            @ApiOperation("删除 ")
        @DeleteMapping("/{id}")
        @EnableLog("删除 ")
        public Result<?> deleteOrder(@PathVariable Long id) {
            service.deleteOrder(id);
            return Result.success();
            }


                @ApiOperation("根据id获取  详情")
            @GetMapping("/{id}")
            @EnableLog("根据id获取  详情")
            public Result<?> queryOrderById(@PathVariable Long id) {
                OrderVO vo = service.queryOrderById(id);
                return Result.success(vo);
                }


                    @ApiOperation("分页查询 ")
                @PostMapping("/page")
                @EnableLog("分页查询  ")
                public Result<?> pagingList(@Valid @RequestBody OrderQuery query) {
                    Page<OrderVO> page = service.pageList(query);
                        return Result.success(page);
                        }
                        }
