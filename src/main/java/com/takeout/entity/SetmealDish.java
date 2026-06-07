package com.takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDish implements Serializable {
    private Long id;
    // 套餐id
    private Long setmealId;
    // 菜品id
    private Long dishId;
    // 菜品名称（冗余）
    private String name;
    // 菜品价格（冗余）
    private BigDecimal price;
    // 份数
    private Integer copies;
}