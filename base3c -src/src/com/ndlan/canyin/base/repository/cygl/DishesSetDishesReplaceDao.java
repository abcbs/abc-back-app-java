package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishesReplace;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface DishesSetDishesReplaceDao extends BaseDao<DishesSetDishesReplace, String>
{
  public abstract List<DishesSetDishesReplace> findByRestIdAndDsIdAndDishesSetDishes(String paramString1, String paramString2, DishesSetDishes paramDishesSetDishes);

  public abstract List<DishesSetDishesReplace> findByRestIdAndDsId(String paramString1, String paramString2);

  public abstract List<DishesSetDishesReplace> findByRestIdAndReplaceDishe(String paramString, Dishe paramDishe);
}

