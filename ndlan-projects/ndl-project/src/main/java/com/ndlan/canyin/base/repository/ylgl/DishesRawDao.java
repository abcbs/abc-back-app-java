package com.ndlan.canyin.base.repository.ylgl;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.ylgl.DishesRaw;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface DishesRawDao extends BaseDao<DishesRaw, String>
{
  @Query("select d from DishesRaw d  where  d.rawMaterial.rmId=?1 ")
  public abstract List<DishesRaw> getDishesRawByrmId(String paramString);

  public abstract List<DishesRaw> findByRestIdAndDishe(String paramString, Dishe paramDishe);

  @Query("select d from DishesRaw d  where d.restId=?1 and d.dishe.dishesId=?2 ")
  public abstract List<DishesRaw> getByRestIdAndDishesId(String paramString1, String paramString2);
}

