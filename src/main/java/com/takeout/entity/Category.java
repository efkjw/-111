package com.takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
//菜单实体类
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category  implements Serializable {
    private long id;
    private  String name;

    private Integer sort;

    private  Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private  Long createUser;

    private  Long updateUser;


}
