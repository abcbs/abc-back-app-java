package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesStyle;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesStyleDao extends BaseDao<DishesStyle, String>
{
  public abstract List<DishesStyle> findByRestId(String paramString);

  public abstract List<DishesStyle> findByRestIdAndEnableStatus(String paramString1, String paramString2);

  public abstract DishesStyle findByRestIdAndName(String paramString1, String paramString2);

  public abstract List<DishesStyle> findByCodeAndRestId(String paramString1, String paramString2);

  @Query("select d from DishesStyle d")
  public abstract List<DishesStyle> findAll();

  @Query("select d from DishesStyle d where d.enableStatus=?1 group by d.name")
  public abstract List<DishesStyle> getAllDishesStyle(String paramString);

@Query("from DishesStyle a where a.name=? ")
public abstract DishesStyle getoneByStyleName(String styleName);
}

