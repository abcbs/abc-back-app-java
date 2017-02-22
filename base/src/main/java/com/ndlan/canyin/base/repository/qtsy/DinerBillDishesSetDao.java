package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface DinerBillDishesSetDao extends BaseDao<DinerBillDishesSet, String>
{
  public abstract List<DinerBillDishesSet> findByRestIdAndDsId(String paramString1, String paramString2);

  public abstract List<DinerBillDishesSet> findByRestIdAndDinerBill(String paramString, DinerBill paramDinerBill);

  public abstract List<DinerBillDishesSet> findByRestIdAndDishesSetCategory(String paramString, DishesSetCategory paramDishesSetCategory);

  @Query("SELECT sum(dbds.unitNum) FROM DinerBillDishesSet dbds WHERE dbds.dsId=?1 AND dbds.dinerBill.payTime>=?2 AND dbds.dinerBill.payTime<=?3 AND dbds.dinerBill.billStatus='3' ")
  public abstract Double getDishesSaleSerings(String paramString, Date paramDate1, Date paramDate2);
}

