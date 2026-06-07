package com.takeout.controller.admin;

import com.takeout.dto.DishDTO;
import com.takeout.dto.DishPageQuery;
import com.takeout.entity.Dish;
import com.takeout.result.PageResult;
import com.takeout.result.Result;
import com.takeout.service.DishService;
import com.takeout.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    // 新增菜品
    @PostMapping
    public Result<String> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    // 分页查询
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQuery dishPageQuery) {
        log.info("分页查询菜品：{}", dishPageQuery);
        PageResult pageResult = dishService.pageQuery(dishPageQuery);
        return Result.success(pageResult);
    }

    // 批量删除（前端传逗号分隔的id列表）
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids) {
        log.info("批量删除菜品：{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    // 根据id查询（用于回显）
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    // 修改菜品
    @PutMapping
    public Result<String> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    // 根据分类id查询菜品（下拉框用）
    @GetMapping("/list")
    public Result<List<Dish>> listByCategoryId(@RequestParam Long categoryId) {
        log.info("根据分类查询菜品 categoryId={}", categoryId);
        List<Dish> list = dishService.listByCategoryId(categoryId);
        return Result.success(list);
    }
}