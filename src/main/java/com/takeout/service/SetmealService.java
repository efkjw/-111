package com.takeout.service;

import com.takeout.dto.SetmealDTO;
import com.takeout.dto.SetmealPageQuery;
import com.takeout.entity.SetmealDish;
import com.takeout.result.PageResult;
import com.takeout.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQuery setmealPageQuery);

    void  deleteBatch(List<Long>ids);

    SetmealVO getByWithDish(Long id);

    void updateWithDish(SetmealDTO setmealDTO);

    void startOrStop(Integer status,Long id);
}
