package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetPic;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesSetPicDao extends BaseDao<DishesSetPic, String>
{
  public abstract List<DishesSetPic> findByRestIdAndDishesSet(String paramString, DishesSet paramDishesSet);

  @Query("select pic from DishesSetPic pic where pic.dishesSet.dsId=?1 ")
  public abstract List<DishesSetPic> findByDishesId(String paramString);

  @Query("select distinct pic.dishesSet.dsId from DishesSetPic pic where pic.restId=?1 ")
  public abstract List<String> findDsIdByRestId(String paramString);

//	@Query("select pic from ")
//	public abstract List<DishesSet> findByDishesSetId(String dsId);
	}

