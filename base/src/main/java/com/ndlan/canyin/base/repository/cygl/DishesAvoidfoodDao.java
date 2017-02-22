package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesAvoidfood;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesAvoidfoodDao extends BaseDao<DishesAvoidfood, String>
{
  public abstract List<DishesAvoidfood> findByRestId(String paramString);

  public abstract List<DishesAvoidfood> findByRestIdAndEnableStatus(String paramString1, String paramString2);

  public abstract List<DishesAvoidfood> findByCodeAndRestIdAndEnableStatus(String paramString1, String paramString2, String paramString3);

  public abstract DishesAvoidfood findByRestIdAndName(String paramString1, String paramString2);

  public abstract List<DishesAvoidfood> findByCodeAndRestId(String paramString1, String paramString2);

@Query("from DishesAvoidfood a where a.name=? ")
public abstract DishesAvoidfood getoneByAvoidfoodName(String name);
}

