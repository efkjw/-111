package com.takeout.service;

import com.takeout.dto.EmployeeLoginDTO;
import com.takeout.dto.EmployeePageQueryDTO;
import com.takeout.entity.Employee;

import com.takeout.dto.EmployeeDTO;
import com.takeout.result.PageResult;

public interface EmployeeService {

    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO 员工数据传输对象
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO 分页查询条件DTO
     * @return 分页结果封装对象
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    void startOrstop(Integer status,long id);

    Employee getById(long id);

    void update( EmployeeDTO employeeDTO);
}
