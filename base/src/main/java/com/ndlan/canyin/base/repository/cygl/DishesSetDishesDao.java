package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface DishesSetDishesDao extends BaseDao<DishesSetDishes, String>
{
  public abstract List<DishesSetDishes> findByRestIdAndDishesSetAndDishe(String paramString, DishesSet paramDishesSet, Dishe paramDishe);

  public abstract List<DishesSetDishes> findByRestIdAndDishesSet(String paramString, DishesSet paramDishesSet);

  public abstract List<DishesSetDishes> findByRestIdAndDishe(String paramString, Dishe paramDishe);
}

