package com.takeout.service;

import com.takeout.dto.EmployeeLoginDTO;
import com.takeout.entity.Employee;

import com.takeout.dto.EmployeeDTO;

public interface EmployeeService {

    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO 员工数据传输对象
     */
    void save(EmployeeDTO employeeDTO);
}
