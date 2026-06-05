package com.takeout.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EmployeeLoginVO implements Serializable {

    private Long id;
    private String userName;
    private String name;
    private String token;
}
