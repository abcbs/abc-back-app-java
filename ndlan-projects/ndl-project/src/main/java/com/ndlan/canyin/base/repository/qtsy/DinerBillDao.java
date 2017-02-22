package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.repository.BaseDao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public abstract interface DinerBillDao extends BaseDao<DinerBill, String>
{
  public abstract Page<DinerBill> findByRestaurantAndTableIsNullOrderByCreateTimeDesc(Restaurant paramRestaurant, Pageable paramPageable);

  @Query("select obj from DinerBill obj where obj.billId=?1 and obj.restaurant.restId = ?2")
  public abstract DinerBill findByBillIdAndRestaurant(String paramString1, String paramString2);

  public abstract List<DinerBill> findByCashDiscount(CashDiscount paramCashDiscount);

  @Query("select sum(c.payableCost) as payableCostTotal  from DinerBill c where (c.billStatus = 1 or c.billStatus = 2  or c.billStatus = 4 or c.billStatus = 9) and c.restaurant.restId = ?1 and TO_DAYS(c.billTime) = TO_DAYS(NOW()) ")
  public abstract BigDecimal getCurrentDayPayableCostTotal(String paramString);

  @Query("select sum(c.realCost) as realCostTotal from DinerBill c where c.billStatus = 3 and c.restaurant.restId = ?1 and TO_DAYS(c.billTime) = TO_DAYS(NOW()) ")
  public abstract BigDecimal getCurrentDayRealCostTotal(String paramString);

  public abstract List<DinerBill> findByRestaurantAndBillStatus(Restaurant paramRestaurant, String paramString);

  @Query("select count(d) from DinerBill d where d.restaurant.restId=?1 and d.billStatus=?2")
  public abstract Long getCountByBillStatus(String paramString1, String paramString2);

  @Query("select count(d) from DinerBill d where d.restaurant.restId=?1 and d.billType=?2 and d.billStatus in(?3)")
  public abstract Long getCountByBillTypeAndBillStatusIn(String paramString1, String paramString2, List<String> paramList);

  public abstract List<DinerBill> findByRestaurantAndBillTypeAndBillStatusIn(Restaurant paramRestaurant, String paramString, List<String> paramList);

  @Query("select sum(d.oddChange) from DinerBill d where d.restaurant.restId=?1 and d.cashierEmployee.empId=?2 and d.payTime >= ?3 and d.billStatus=?4")
  public abstract BigDecimal getOddChangeSum(String paramString1, String paramString2, Date paramDate, String paramString3);

  @Query("select sum(d.peopleNum) from DinerBill d where d.restaurant.restId=?1 and d.cashierEmployee.empId=?2 and d.payTime >= ?3 and d.billStatus=?4")
  public abstract Long getPeopleNumSum(String paramString1, String paramString2, Date paramDate, String paramString3);

  @Query("select sum(d.payableCost)-sum(d.realCost) from DinerBill d where d.restaurant.restId=?1 and d.forcePayOperator.empId=?2 and d.forcePayTime >= ?3 and d.billStatus=?4 and d.isForcePay=?5 and d.isValid=?6 ")
  public abstract BigDecimal getForceMoneySum(String paramString1, String paramString2, Date paramDate, String paramString3, String paramString4, String paramString5);

  public abstract List<DinerBill> findByRestaurantAndBillStatusAndIsShift(Restaurant paramRestaurant, String paramString1, String paramString2);

  public abstract List<DinerBill> findByRestaurantAndBillStatusAndIsShiftAndBillType(Restaurant paramRestaurant, String paramString1, String paramString2, String paramString3);

  public abstract List<DinerBill> findByRestaurantAndCesId(Restaurant paramRestaurant, String paramString);

  public abstract List<DinerBill> findByRestaurantAndBillNo(Restaurant paramRestaurant, String paramString);

  public abstract List<DinerBill> findByRestaurantAndBillNoAndBillStatus(Restaurant paramRestaurant, String paramString1, String paramString2);

  @Query("select d from DinerBill d where d.restaurant.restId = ?1 and d.billStatus = ?2 and d.billType = '2' and d.createTime >= ?3")
  public abstract List<DinerBill> getByRestaurantAndBillStatus(String paramString1, String paramString2, String paramString3);

  @Query("select d from DinerBill d where d.restaurant.restId = ?1 and d.billType = '2' and TO_DAYS(d.createTime) = TO_DAYS(NOW())")
  public abstract List<DinerBill> getTakeoutToday(String paramString);

  @Query("select sum(d.realCost) from DinerBill d where d.restaurant.restId = ?1 and d.billStatus in(?2) and d.billType = '2' and TO_DAYS(d.createTime) = TO_DAYS(NOW())")
  public abstract BigDecimal getRealCostSumByRestaurantAndBillStatus(String paramString, List<String> paramList);

  @Query("select sum(d.payableCost) from DinerBill d where d.restaurant.restId = ?1 and d.billStatus in(?2) and d.billType = '2' and TO_DAYS(d.createTime) = TO_DAYS(NOW())")
  public abstract BigDecimal getPayableCostSumByRestaurantAndBillStatus(String paramString, List<String> paramList);
}

