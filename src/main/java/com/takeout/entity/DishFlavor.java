package com.takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DishFlavor  implements Serializable {
    private  Long id;

    private  Long dishId;

    private  String name;
    //辣度
    private  String value;

}
