package com.takeout.mapper;

import com.takeout.entity.DishFlavor;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DishFlavorMapper {

    //批量插入新口味
    void insertBatch(List<DishFlavor>flavors);

    //根据菜品id删除口味
    // 根据菜品id删除口味
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);


    // 根据菜品id查询口味列表（回显用）
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);


}
