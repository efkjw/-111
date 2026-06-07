package com.takeout.dto;


import lombok.Data;


@Data
public class DishPageQuery {

    private String name;
    private int page;
    private int pageSize;
    private Long categoryId;
     private Integer status;
}
