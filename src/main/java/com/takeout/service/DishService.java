package com.takeout.service;

import com.takeout.dto.DishDTO;
import com.takeout.dto.DishPageQuery;
import com.takeout.entity.Dish;
import com.takeout.result.PageResult;
import com.takeout.vo.DishVO;

import java.util.List;

public interface DishService {

    // 新增菜品（同时保存口味）
    void saveWithFlavor(DishDTO dishDTO);
    // 分页查询
    PageResult pageQuery(DishPageQuery dishPageQuery);
    // 批量删除菜品
    void deleteBatch(List<Long> ids);
    // 根据id查询菜品
    DishVO getByIdWithFlavor(Long id);
    // 修改菜品
    void updateWithFlavor(DishDTO dishDTO);
    // 根据分类id查询菜品列表
    List<Dish> listByCategoryId(Long categoryId);
}
