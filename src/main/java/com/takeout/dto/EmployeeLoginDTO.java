package com.takeout.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeLoginDTO implements Serializable {
private  long id;
    private String username;
    private String password;
    private  String phone;
    private  String sex;
    private  String idNumber;
}
