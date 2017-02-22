package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesCategoryDao extends BaseDao<DishesCategory, String>
{
  public abstract List<DishesCategory> findByRestIdOrderByShowSeqAsc(String paramString);

  public abstract List<DishesCategory> findByRestIdAndEnableStatusOrderByShowSeqAsc(String paramString1, String paramString2);

  public abstract List<DishesCategory> findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(String paramString1, String paramString2, String paramString3);

  public abstract List<DishesCategory> findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryId(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract DishesCategory findByRestIdAndCategoryName(String paramString1, String paramString2);

  public abstract List<DishesCategory> findByRestIdAndParentCategoryId(String paramString1, String paramString2);

  @Query("SELECT category FROM DishesCategory category WHERE category.restId = ?1 AND category.enableStatus = ?2 AND category.categoryId NOT IN (SELECT parentCategoryId FROM DishesCategory WHERE parentCategoryId IS NOT NULL AND restId = ?1 AND enableStatus = ?2 ) ORDER BY category.showSeq ASC")
  public abstract List<DishesCategory> getNoChildrenCategoryOrderByAsc(String paramString1, String paramString2);

  @Query("SELECT category FROM DishesCategory category WHERE category.restId = ?1 AND category.enableStatus = ?2 AND category.categoryLevel = '1' ")
  public abstract List<DishesCategory> getNoChildrenDishesOrderByAsc(String paramString1, String paramString2);
/*  @Query("SELECT category FROM DishesCategory category WHERE category.restId = ?1 AND category.enableStatus = ?2 AND category.categoryLevel = '1' AND category.categoryId NOT IN (SELECT DISTINCT dishesCategory.categoryId FROM Dishe WHERE restId = ?1 AND isStopSell = '0') ORDER BY showSeq")
  public abstract List<DishesCategory> getNoChildrenDishesOrderByAsc(String paramString1, String paramString2);
*/

	@Query(" from DishesCategory a where a.categoryCode=?1 and a.restId=?2 ")
	public abstract List<DishesCategory> findByCategoryCodeAndRestId(String paramString1,String paramString2);

	@Query(" from DishesCategory a where a.categoryName=?1 and a.restId=?2")
	public abstract List findByCategoryNameAndRestId(String paramString1,String paramString2);

}

