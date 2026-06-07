package com.takeout.mapper;

import com.github.pagehelper.Page;
import com.takeout.dto.CategoryDTO;
import com.takeout.dto.DishDTO;
import com.takeout.dto.DishPageQuery;
import com.takeout.entity.Category;
import com.takeout.entity.Dish;
import com.takeout.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DishMapper {
    //新增菜品
    void insert(Dish dish);



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


    Page<DishVO> pageQuery(DishPageQuery dishPageQuery);
}
