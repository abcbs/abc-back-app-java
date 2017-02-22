package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.SpecialDishe;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface SpecialDisheDao extends BaseDao<SpecialDishe, String>
{
  public abstract List<SpecialDishe> findByRestIdOrderByShowSeqAsc(String paramString);

  public abstract List<SpecialDishe> findByRestIdOrderByShowSeqDesc(String paramString);

  public abstract List<SpecialDishe> findByDisheAndRestId(Dishe paramDishe, String paramString);

  public abstract List<SpecialDishe> findByDishesSetAndRestId(DishesSet paramDishesSet, String paramString);

@Query(" from SpecialDishe a where a.restId=? and a.dishesSet.dsId=? ")
public abstract SpecialDishe findByRestIdAndDsId(String getCurrentUserRestId, String id);
}

