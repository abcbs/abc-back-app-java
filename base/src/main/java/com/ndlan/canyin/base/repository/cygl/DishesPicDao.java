package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesPic;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public abstract interface DishesPicDao extends BaseDao<DishesPic, String>
{
  @Query("select pic from DishesPic pic where pic.dishe.dishesId=?1 ")
  public abstract List<DishesPic> findByDishesId(String paramString);
  
  @Modifying
  @Query("delete from DishesPic pic where pic.dishe.dishesId=?1")
  public abstract int deleteByDishesId(String paramString);

  @Query("select distinct pic.dishe.dishesId from DishesPic pic where pic.restId=?1 ")
  public abstract List<String> findDishesIdByRestId(String paramString);
}

