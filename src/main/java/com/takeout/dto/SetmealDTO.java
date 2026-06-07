package com.takeout.dto;

import com.takeout.entity.SetmealDish;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SetmealDTO implements Serializable {

    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String description;
    private String image;
    // 套餐关联的菜品列表
    private List<SetmealDish> setmealDishes;
}
