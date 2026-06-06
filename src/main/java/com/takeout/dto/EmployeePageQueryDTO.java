package com.takeout.dto;

import lombok.Data;

@Data
public class EmployeePageQueryDTO {
    private  int page;
    private  int pagesize;
    private  String name;
}
