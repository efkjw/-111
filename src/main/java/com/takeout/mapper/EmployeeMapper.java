package com.takeout.mapper;

import com.github.pagehelper.Page;
import com.takeout.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工数据
     * @param employee 员工实体
     */
    @Insert("insert into employee (name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values (#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Employee employee);



    /**
     * 分页查询员工
     * @param name 员工姓名（用于模糊查询）
     * @return PageHelper 包装的 Page 集合
     */
    @Select("<script>" +
            "select * from employee " +
            "<where>" +
            // TODO: 请补充针对 name 的动态 SQL 条件判断。
            // 提示：如果 name 不为 null 且不为空字符串，拼接：and name like concat('%', #{name}, '%')
            "</where>" +
            "order by create_time desc" +
            "</script>")
    Page<Employee> pageQuery(String name);

}
