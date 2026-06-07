package com.takeout.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.takeout.context.BaseContext;
import com.takeout.dto.CategoryDTO;
import com.takeout.entity.Category;
import com.takeout.mapper.CategoryMap;
import com.takeout.result.PageResult;
import com.takeout.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMap categoryMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(1); // 默认启用
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.insert(category);
    }

    @Override
    public PageResult pageQuery(int page, int pageSize, String name, Integer type) {
        PageHelper.startPage(page,pageSize);

        CategoryDTO dto = new CategoryDTO();
        dto.setName(name);
        dto.setType(type);
        Page<Category> pages = categoryMapper.pageQuery(dto);
        return new PageResult(pages.getTotal(), pages.getResult());}



    @Override
    public void deleteById(Long id) {
        // 后续可在此处添加：判断该分类下是否有菜品，有则不允许删除
        categoryMapper.deleteById(id);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }



    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}