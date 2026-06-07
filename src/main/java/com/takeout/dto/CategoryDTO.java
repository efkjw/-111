package com.takeout.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private  long id;

    private  Integer type;

    private  String name;

    private  Integer sort;


}
