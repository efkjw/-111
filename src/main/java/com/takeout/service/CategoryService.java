package com.takeout.service;

import com.takeout.dto.CategoryDTO;
import com.takeout.entity.Category;
import com.takeout.result.PageResult;

import java.util.List;

public interface CategoryService {

    void  save(CategoryDTO categoryDTO);

    PageResult pageQuery(int page ,int pageSize,String name,Integer type);

    void deleteById(Long id);

    void  update(CategoryDTO categoryDTO);

    void startOrStop(Integer status,Long id);

    List<Category>list(Integer type);
}
