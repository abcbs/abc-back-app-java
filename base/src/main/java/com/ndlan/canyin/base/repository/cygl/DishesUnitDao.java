package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesUnit;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesUnitDao extends BaseDao<DishesUnit, String>
{
  public abstract List<DishesUnit> findByRestId(String paramString);

  public abstract List<DishesUnit> findByRestIdAndEnableStatus(String paramString1, String paramString2);

  public abstract DishesUnit findByRestIdAndName(String paramString1, String paramString2);

  public abstract List<DishesUnit> findByCodeAndRestId(String paramString1, String paramString2);

@Query("from DishesUnit a where a.name=? ")
public abstract DishesUnit getoneByUnitName(String unitName);
}

