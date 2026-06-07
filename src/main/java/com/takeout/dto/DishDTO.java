package com.takeout.dto;

import com.takeout.entity.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//新增/修改时接受前端数据

@Data
public class DishDTO implements Serializable {

    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer status;
    // 菜品关联的口味列表
    private List<DishFlavor> flavors = new ArrayList<>();
}
