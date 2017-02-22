package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public abstract interface DinerBillDisheDao extends BaseDao<DinerBillDishe, String>
{
  public abstract Page<DinerBillDishe> findByDishesStatusIn(List<String> paramList, Pageable paramPageable);

  @Query("select dinerBillDishe from DinerBillDishe dinerBillDishe where restId=? and dinerBillDishe.dinerBill.billId=? order by createTime asc ")
  public abstract List<DinerBillDishe> findByRestIdAndBillId(String paramString1, String paramString2);

  @Query("select dinerBillDishe from DinerBillDishe dinerBillDishe where restId=? and dinerBillDishe.dishesCode=? order by sale_num desc")
  public abstract List<DinerBillDishe> findByRestIdAndDishesCode(String paramString1, String paramString2);

  public abstract List<DinerBillDishe> findByDishesIdAndRestId(String paramString1, String paramString2);

  @Query("select dinerBillDishe from DinerBillDishe dinerBillDishe where restId=? and dinerBillDishe.dinerBill.billId=? order by fixedTime desc ")
  public abstract List<DinerBillDishe> findByRestIdAndBillIdOrderbyFixedTimeDesc(String paramString1, String paramString2);

  @Query("select dinerBillDishe from DinerBillDishe dinerBillDishe where restId=? and dinerBillDishe.dinerBill.billId=? and dinerBillDishe.dishesStatus in(?,?) order by fixedTime desc ")
  public abstract List<DinerBillDishe> findByRestIdAndBillIdAndDishesStatusOrderbyFixedTimeDesc(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract List<DinerBillDishe> findByDishesCategory(DishesCategory paramDishesCategory);

  @Query("SELECT sum(dbd.unitNum) FROM DinerBillDishe dbd WHERE dbd.dishesId=?1 AND dbd.dinerBill.payTime>=?2 AND dbd.dinerBill.payTime<=?3 AND dbd.dinerBill.billStatus='3' ")
  public abstract Double getDishesSaleSerings(String paramString, Date paramDate1, Date paramDate2);
}

