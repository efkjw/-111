package com.takeout.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.takeout.context.BaseContext;
import com.takeout.dto.SetmealDTO;
import com.takeout.dto.SetmealPageQuery;
import com.takeout.entity.Dish;
import com.takeout.entity.DishFlavor;
import com.takeout.entity.Setmeal;
import com.takeout.entity.SetmealDish;
import com.takeout.mapper.SetmealDishMapper;
import com.takeout.mapper.SetmealMapper;
import com.takeout.result.PageResult;
import com.takeout.service.SetmealService;
import com.takeout.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private  SetmealDishMapper setmealDishMapper;

@Transactional
    @Override
    public void saveWithDish(SetmealDTO setmealDTO) {
    Setmeal setmeal = new Setmeal();
    BeanUtils.copyProperties(setmealDTO,setmeal);
    setmeal.setStatus(1);
    setmeal.setCreateTime(LocalDateTime.now());
    setmeal.setUpdateTime(LocalDateTime.now());
    setmeal.setCreateUser(BaseContext.getCurrentId());
    setmeal.setUpdateUser(BaseContext.getCurrentId());
    setmealMapper.insert(setmeal);

    Long setMealId = setmeal.getId();

    List<SetmealDish>dish = setmealDTO.getSetmealDishes();

    if (dish!=null&& !dish.isEmpty()){
        dish.forEach(dishs->{dishs.setSetmealId(setMealId);});
        setmealDishMapper.insertBatch(dish);

    }

    }

    @Override
    public PageResult pageQuery(SetmealPageQuery setmealPageQuery) {

        PageHelper.startPage(setmealPageQuery.getPage(),setmealPageQuery.getPageSize());
        Page<SetmealVO>page = setmealMapper.pageQuery(setmealPageQuery);

    return new PageResult(page.getTotal(),page.getResult());
    }
@Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
    for(Long id:ids){
        setmealDishMapper.deleteBySetmealId(id);
        setmealMapper.deleteById(id);

    }

    }

    @Override
    public SetmealVO getByWithDish(Long id) {
    Setmeal setmeal = setmealMapper.getById(id);
    List<SetmealDish>dishes = setmealDishMapper.getBySetmealId(id);

    SetmealVO setmealVO = new SetmealVO();

    BeanUtils.copyProperties(setmeal,setmealVO);

    setmealVO.setSetmealDishes(dishes);

    return  setmealVO;


    }

    @Override
    public void updateWithDish(SetmealDTO setmealDTO) {


        Setmeal setmeal = new Setmeal();

        BeanUtils.copyProperties(
                setmealDTO,
                setmeal);
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        setmealMapper.update(setmeal);


        setmealDishMapper.deleteBySetmealId(
                setmeal.getId());

        // 新口味
        List<SetmealDish> setmealDishes =
                setmealDTO.getSetmealDishes();

        if(setmealDishes != null &&
                !setmealDishes.isEmpty()){

            setmealDishes.forEach(Dishs -> {
                Dishs.setSetmealId(
                        setmeal.getId());
            });

            setmealDishMapper.insertBatch(
                    setmealDishes);
        }

    }

    @Override
    public void startOrStop(Integer status, Long id) {
Setmeal setmeal = Setmeal.builder()
        .id(id)
        .status(status)
        .updateTime(LocalDateTime.now())
        .updateUser(BaseContext.getCurrentId()).build();

setmealMapper.update(setmeal);

    }
}
