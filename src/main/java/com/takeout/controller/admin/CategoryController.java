package com.takeout.controller.admin;

import com.takeout.dto.CategoryDTO;
import com.takeout.entity.Category;
import com.takeout.result.PageResult;
import com.takeout.result.Result;
import com.takeout.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 新增分类
    @PostMapping
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类: {}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    // 分页查询
    @GetMapping("/page")
    public Result<PageResult> page(int page, int pageSize, String name, Integer type) {
        log.info("分页查询 page={}, pageSize={}, name={}, type={}", page, pageSize, name, type);
        PageResult pageResult = categoryService.pageQuery(page, pageSize, name, type);
        return Result.success(pageResult);
    }

    // 删除分类
    @DeleteMapping
    public Result<String> deleteById(@RequestParam Long id) {
        log.info("删除分类 id={}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    // 修改分类
    @PutMapping
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类: {}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    // 启用/禁用分类
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, @RequestParam Long id) {
        log.info("启用/禁用分类 id={}, status={}", id, status);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    // 根据类型查询分类列表
    @GetMapping("/list")
    public Result<List<Category>> list(@RequestParam Integer type) {
        log.info("查询分类列表 type={}", type);
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}