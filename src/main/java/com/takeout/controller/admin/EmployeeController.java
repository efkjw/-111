package com.takeout.controller.admin;

import com.takeout.constant.JwtClaimsConstant;
import com.takeout.dto.EmployeeLoginDTO;
import com.takeout.dto.EmployeePageQueryDTO;
import com.takeout.entity.Employee;
import com.takeout.properties.JwtProperties;
import com.takeout.result.PageResult;
import com.takeout.result.Result;
import com.takeout.service.EmployeeService;
import com.takeout.utils.JwtUtil;
import com.takeout.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status,@RequestParam long id){

        log.info("启用禁用员工id",status,id);

        employeeService.startOrstop(status,id);



        return Result.success();
    }
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

    /**
     * 新增员工
     * @param employeeDTO 员工数据传输对象
     * @return 统一返回结果
     */
    @PostMapping
    public Result<String> save(@RequestBody com.takeout.dto.EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /*员工分页查询
    *@param employeePageQueryDTO 分页查询条件
    * @return 统一返回结果
    * */
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询，参数为：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


}

