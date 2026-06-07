package com.takeout.vo;


import com.takeout.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//查询时返回前端数据
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishVO implements Serializable {


    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer status;
    // 分类名称（dish 表没有，需要关联查询）
    private String categoryName;
    private LocalDateTime updateTime;
    // 口味列表
    private List<DishFlavor> flavors = new ArrayList<>();
}
