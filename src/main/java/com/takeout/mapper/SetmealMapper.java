package com.takeout.mapper;

import com.github.pagehelper.Page;
import com.takeout.dto.SetmealPageQuery;
import com.takeout.entity.Setmeal;
import com.takeout.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {
    void insert(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQuery setmealPageQuery);


    // 根据id查询套餐
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);
    // 根据id删除套餐
    @Delete("delete from setmeal where id = #{id}")
    void deleteById(Long id);
    // 动态修改套餐
    void update(Setmeal setmeal);
}
