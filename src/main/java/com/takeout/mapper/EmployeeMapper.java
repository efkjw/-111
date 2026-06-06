package com.takeout.mapper;

import com.github.pagehelper.Page;
import com.takeout.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
            "<if test='name != null and name != \"\"'>" +
            "and name like concat('%',#{name},'%')" +
            "</if>" +
            "</where>" +
            "order by create_time desc" +
            "</script>")
    Page<Employee> pageQuery(String name);


    /**
     * 根据主键动态修改属性
     * @param employee 员工实体
     */
    @Update("<script>" +
            "update employee " +
            "<set>" +
            "<if test=\"name != null\"> name = #{name}, </if>" +
            "<if test=\"username != null\"> username = #{username}, </if>" +
            "<if test=\"phone != null\"> phone = #{phone}, </if>" +
            "<if test=\"sex != null\"> sex = #{sex}, </if>" +
            "<if test=\"idNumber != null\"> id_number = #{idNumber}, </if>" +
            "<if test=\"status != null\"> status = #{status}, </if>" +
            "<if test=\"updateTime != null\"> update_time = #{updateTime}, </if>" +
            "<if test=\"updateUser != null\"> update_user = #{updateUser}, </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    // TODO: 请补充方法签名
    void update(Employee employee);

    /**
     * 根据 ID 查询员工信息
     * @param id 员工ID
     * @return 员工实体
     */
    @Select("select *from employee where id =#{id}")
    Employee getById(long id);//

}
