package com.takeout.mapper;

import com.github.pagehelper.Page;
import com.takeout.entity.Dish;
import com.takeout.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DishMapper {
    //新增菜品
    void insert(Dish dish);

    //分页查询
    Page<DishVO> pageQuery(String name,Long categoryId,Integer status);


    // 根据id查询菜品
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
    // 根据id删除菜品
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);
    // 修改菜品
    void update(Dish dish);
    // 根据分类id查询菜品列表
    @Select("select * from dish where category_id = #{categoryId} and status = 1")
    List<Dish> listByCategoryId(Long categoryId);
}
