package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DisheDao extends BaseDao<Dishe, String>
{
  @Query("select count(*) from Dishe dishe where restId=?1 and categoryId = ?2")
  public abstract int getCountByRestIdAndCategoryId(String paramString1, String paramString2);
@Query("from Dishe a where a.restId=?1  and a.isUserDefined=?2 order by a.showSeq,a.createTime,dishesId asc")
  public abstract List<Dishe> findByRestIdAndIsUserList(String paramString1, String paramString2);

  public abstract List<Dishe> findByRestIdAndIsUserDefinedOrderByShowSeqAsc(String paramString1, String paramString2);

  public abstract List<Dishe> findByRestIdAndIsUserDefinedAndIsStopSellOrderByShowSeqAsc(String paramString1, String paramString2, String paramString3);

  @Query("select dishe from Dishe dishe where restId=?1 and isTakeout = 1 and isUserDefined = 0 order by showSeqTakeout asc ")
  public abstract List<Dishe> findByRestIdOrderByShowSeqTakeoutAsc(String paramString);

  @Query("select dishe from Dishe dishe where restId=?1 and (isSpecialty = 1 or isRecommend = 1) and isUserDefined = 0 order by showSeqRecommend asc ")
  public abstract List<Dishe> findByRestIdOrderByShowSeqRecommendAsc(String paramString);

  @Query("select dishe from Dishe dishe where restId=?1 and is_stop_sell = 0 and is_user_defined = 0 order by show_seq asc")
  public abstract List<Dishe> findAllNotStopSell(String paramString);

  @Query("select dishe from Dishe dishe where dishesCode=?1 and restId=?2")
  public abstract List<Dishe> findByDishesCodeAndRestId(String paramString1, String paramString2);

  @Query("select dishe from Dishe dishe where dishesCode=?1 and restId=?2 and isStopSell=?3")
  public abstract List<Dishe> findByDishesCodeAndRestIdAndIsStopSell(String paramString1, String paramString2, String paramString3);

  @Query("select dishe from Dishe dishe where dishesId=?1 and restId=?2")
  public abstract List<Dishe> findByDishesIdAndRestId(String paramString1, String paramString2);

  @Query("select dishe from Dishe dishe where dishesId=?1 and restId=?2 and isStopSell=?3")
  public abstract List<Dishe> findByDishesIdAndRestIdAndIsStopSell(String paramString1, String paramString2, String paramString3);

  public abstract List<Dishe> findByDishesNameAndRestId(String paramString1, String paramString2);

  public abstract List<Dishe> findByDishesNameAndRestIdAndIsStopSell(String paramString1, String paramString2, String paramString3);

  @Query(" from Dishe a where a.restId=?1 and a.dishesCategory =?2 and a.isUserDefined=?3 order by a.showSeq,a.createTime,dishesId asc")
  public abstract List<Dishe> findByRestIdAndDishesList(String paramString1, DishesCategory paramDishesCategory, String paramString2);
  
  public abstract List<Dishe> findByRestIdAndDishesCategoryAndIsUserDefinedOrderByShowSeqAsc(String paramString1, DishesCategory paramDishesCategory, String paramString2);

  public abstract List<Dishe> findByRestIdAndDishesCategoryAndIsUserDefinedAndIsStopSellOrderByShowSeqAsc(String paramString1, DishesCategory paramDishesCategory, String paramString2, String paramString3);

  public abstract List<Dishe> findByDishesNameAndRestIdAndIsUserDefined(String paramString1, String paramString2, String paramString3);

  public abstract List<Dishe> findByRestIdAndEstimateIsNotNull(String paramString);

  @Query("select d from Dishe d  where  d.dishesCategory.categoryId=?1 and d.isUserDefined = '0' ")
  public abstract List<Dishe> findByCategoryId(String paramString);

  public abstract List<Dishe> findByTasteIdArrayLike(String paramString);

  public abstract List<Dishe> findByDashesStyleIdArrayLike(String paramString);

  public abstract List<Dishe> findByMaterialIdArrayLike(String paramString);

  @Query("update Dishe d  set d.estimate=0 where d.restId=?1")
  public abstract void updateEstimate(String paramString);

@Query("from Dishe a where a.restId=?1 and dishesCategory.categoryId=?2")
public abstract List<Dishe> findByCategoryIdAndRestId(String paramString1,String paramString2);
}

