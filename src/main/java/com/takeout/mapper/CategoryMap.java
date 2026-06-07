package com.takeout.mapper;

import com.github.pagehelper.Page;
import com.takeout.dto.CategoryDTO;
import com.takeout.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMap {


    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " values(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void  insert(Category category);

    //分页查询
    Page<Category> pageQuery(CategoryDTO categoryDTO);

    //根据id删除分类
    @Delete("delete from category where id = #{id}")
    void deleteById(long id);

    void  update(Category category);

    //根据类型查分类
    List<Category> list(Integer type);

}
