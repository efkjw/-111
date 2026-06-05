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

import com.takeout.dto.EmployeeDTO;
import com.takeout.constant.PasswordConstant;
import com.takeout.context.BaseContext;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;

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

    /**
     * 新增员工
     * @param employeeDTO 员工数据传输对象
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        // 对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        // 设置账号状态，默认 1 表示正常（StatusConstant.ENABLE）
        employee.setStatus(StatusConstant.ENABLE);

        // 设置密码，默认密码 123456，并进行 md5 加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes(StandardCharsets.UTF_8)));

        // 设置创建时间、修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 设置创建人、修改人（从 ThreadLocal 中获取）
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }
}
