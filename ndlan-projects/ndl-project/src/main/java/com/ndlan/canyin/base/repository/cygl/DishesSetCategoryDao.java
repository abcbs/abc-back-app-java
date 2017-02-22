package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesSetCategoryDao extends BaseDao<DishesSetCategory, String>
{
  public abstract List<DishesSetCategory> findByRestIdOrderByShowSeqAsc(String paramString);

  public abstract List<DishesSetCategory> findByRestIdAndEnableStatusOrderByShowSeqAsc(String paramString1, String paramString2);

  public abstract List<DishesSetCategory> findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(String paramString1, String paramString2, String paramString3);

  public abstract List<DishesSetCategory> findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryIdOrderByShowSeqAsc(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract DishesSetCategory findByRestIdAndCategoryName(String paramString1, String paramString2);

  public abstract List<DishesSetCategory> findByRestIdAndParentCategoryId(String paramString1, String paramString2);

  @Query("SELECT category FROM DishesSetCategory category WHERE category.restId = ?1 AND category.enableStatus = ?2 AND category.dsCategoryId NOT IN (SELECT parentCategoryId FROM DishesSetCategory WHERE parentCategoryId IS NOT NULL AND restId = ?1 AND enableStatus = ?2 ) ORDER BY category.showSeq ASC")
  public abstract List<DishesSetCategory> getNoChidrenCategoryOrderByAsc(String paramString1, String paramString2);

  @Query("SELECT category FROM DishesSetCategory category WHERE category.restId = ?1 AND category.enableStatus = ?2 AND category.categoryLevel = '1' ")
  public abstract List<DishesSetCategory> getNoChidrenDishesSetOrderByAsc(String paramString1, String paramString2);
/*  @Query("SELECT category FROM DishesSetCategory category WHERE category.restId = ?1 AND category.enableStatus = ?2 AND category.categoryLevel = '1' AND category.dsCategoryId NOT IN (SELECT DISTINCT dishesSetCategory.dsCategoryId FROM DishesSet WHERE restId = ?1 AND isStopSell = '0') ORDER BY showSeq")
  public abstract List<DishesSetCategory> getNoChidrenDishesSetOrderByAsc(String paramString1, String paramString2);
*/
  	@Query(" from DishesSetCategory a where a.categoryCode=?1 and a.restId=?2 ")
	public abstract List<DishesSetCategory> findByCategoryCodeAndRestId(String paramString1, String paramString2);

	@Query(" from DishesSetCategory a where a.categoryName=?1 and a.restId=?2 ")
	public abstract List<DishesSetCategory> findByCategoryNameAndRestId(String paramString1, String paramString2);
}

