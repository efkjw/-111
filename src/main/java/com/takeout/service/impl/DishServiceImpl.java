package com.takeout.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.takeout.context.BaseContext;
import com.takeout.dto.CategoryDTO;
import com.takeout.dto.DishDTO;
import com.takeout.dto.DishPageQuery;
import com.takeout.entity.Category;
import com.takeout.entity.Dish;
import com.takeout.entity.DishFlavor;
import com.takeout.mapper.DishFlavorMapper;
import com.takeout.mapper.DishMapper;
import com.takeout.result.PageResult;
import com.takeout.service.DishService;
import com.takeout.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish =new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

        dish.setStatus(1);  // 默认起售
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.insert(dish);

        Long dishId = dish.getId();

        // 获取口味列表
        List<DishFlavor> flavors = dishDTO.getFlavors();

        if(flavors != null && !flavors.isEmpty()){

            // 设置dishId
            flavors.forEach(flavor -> {
                flavor.setDishId(dishId);
            });

            // 批量保存口味
            dishFlavorMapper.insertBatch(flavors);
    }}

    @Override
    public PageResult pageQuery(DishPageQuery dto) {

        PageHelper.startPage(
                dto.getPage(),
                dto.getPageSize());
        Page<DishVO> page =
                dishMapper.pageQuery(dto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
for(Long id :ids){
    dishFlavorMapper.deleteByDishId(id);
    dishMapper.deleteById(id);

}
    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        Dish dish = dishMapper.getById(id);
        List<DishFlavor>flavors = dishFlavorMapper.getByDishId(id);
        DishVO dishVO = new DishVO();

        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

    @Transactional
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {

        // 更新菜品
        Dish dish = new Dish();

        BeanUtils.copyProperties(
                dishDTO,
                dish);
        dish.setUpdateTime(LocalDateTime.now());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.update(dish);

        // 删除旧口味
        dishFlavorMapper.deleteByDishId(
                dish.getId());

        // 新口味
        List<DishFlavor> flavors =
                dishDTO.getFlavors();

        if(flavors != null &&
                !flavors.isEmpty()){

            flavors.forEach(flavor -> {
                flavor.setDishId(
                        dish.getId());
            });

            dishFlavorMapper.insertBatch(
                    flavors);
        }
    }


    @Override
    public List<Dish> listByCategoryId(
            Long categoryId) {

        return dishMapper
                .listByCategoryId(categoryId);
    }
}
