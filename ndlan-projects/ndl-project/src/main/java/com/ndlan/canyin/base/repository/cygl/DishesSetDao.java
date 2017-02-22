package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public abstract interface DishesSetDao extends BaseDao<DishesSet, String>
{
  public abstract Page<DishesSet> findByRestIdOrderByShowSeqAsc(String paramString, Pageable paramPageable);

  public abstract List<DishesSet> findByRestIdOrderByShowSeqAsc(String paramString);

  public abstract Page<DishesSet> findByRestIdAndDishesSetCategoryOrderByShowSeqAsc(String paramString, DishesSetCategory paramDishesSetCategory, Pageable paramPageable);

  public abstract List<DishesSet> findByDsCodeAndRestId(String paramString1, String paramString2);

  public abstract List<DishesSet> findByDsNameAndRestId(String paramString1, String paramString2);

  public abstract List<DishesSet> findByRestIdAndDishesSetCategory(String paramString, DishesSetCategory paramDishesSetCategory);

  @Query("select dishesSet from DishesSet dishesSet where restId=?1 and (isSpecialty = 1 or isRecommend = 1) order by showSeqRecommend asc ")
  public abstract List<DishesSet> findByRestIdOrderByShowSeqRecommendAsc(String paramString);

  public abstract List<DishesSet> findByRestIdOrderByShowSeqTakeoutAsc(String paramString);

  public abstract List<DishesSet> findByRestIdAndEstimateIsNotNull(String paramString);

  public abstract List<DishesSet> findByRestIdAndIsStopSellOrderByShowSeqAsc(String paramString1, String paramString2);
}

