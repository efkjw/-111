package com.takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setmeal implements Serializable {
    private Long id;
    // 分类id
    private Long categoryId;
    // 套餐名称
    private String name;
    // 套餐价格
    private BigDecimal price;
    // 状态 0停售 1起售
    private Integer status;
    // 描述信息
    private String description;
    // 图片路径
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;

}
