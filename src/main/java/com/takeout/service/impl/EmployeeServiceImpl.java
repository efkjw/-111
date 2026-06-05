package com.takeout.service.impl;

import com.takeout.constant.MessageConstant;
import com.takeout.constant.StatusConstant;
import com.takeout.dto.EmployeeLoginDTO;
import com.takeout.entity.Employee;
import com.takeout.exception.AccountLockedException;
import com.takeout.exception.AccountNotFoundException;
import com.takeout.exception.PasswordErrorException;
import com.takeout.mapper.EmployeeMapper;
import com.takeout.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new AccountNotFoundException(MessageConstant.LOGIN_FAILED);
        }

        Employee employee = employeeMapper.getByUsername(username);
        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!encryptedPassword.equals(employee.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (StatusConstant.DISABLE.equals(employee.getStatus())) {
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }
}
