package com.takeout.controller.admin;

import com.takeout.constant.JwtClaimsConstant;
import com.takeout.dto.EmployeeLoginDTO;
import com.takeout.entity.Employee;
import com.takeout.properties.JwtProperties;
import com.takeout.result.Result;
import com.takeout.service.EmployeeService;
import com.takeout.utils.JwtUtil;
import com.takeout.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO.getUsername());

        Employee employee = employeeService.login(employeeLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJwt(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims
        );

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }
}
