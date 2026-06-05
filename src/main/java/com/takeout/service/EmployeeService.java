package com.takeout.service;

import com.takeout.dto.EmployeeLoginDTO;
import com.takeout.entity.Employee;

public interface EmployeeService {

    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
