package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.repository.BaseDao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface DinerBillPaymentDao extends BaseDao<DinerBillPayment, String>
{
  public abstract List<DinerBillPayment> findByRestIdAndDinerBillAndPaymentType(String paramString, DinerBill paramDinerBill, PaymentType paramPaymentType);

  public abstract List<DinerBillPayment> findByDinerBill(DinerBill paramDinerBill);

  public abstract List<DinerBillPayment> findByRestIdAndDinerBillAndCreditStatus(String paramString1, DinerBill paramDinerBill, String paramString2);

  public abstract List<DinerBillPayment> findByRestIdAndPaymentType(String paramString, PaymentType paramPaymentType);
//���,�Ͳ�����,ʵ�ʸ�����,Ĩ����
  @Query("select sum(d.money),sum(db.peopleNum),sum(db.realCost),sum(db.molingModeCost) from DinerBillPayment d left join d.dinerBill db left join d.paymentType p where d.restId=?1 and db.cashierEmployee.empId=?2 and db.payTime >= ?3 and p.cptId = ?4 and db.billStatus=?5")
  public abstract Object[] getMoneySumByCptId(String paramString1, String paramString2, Date paramDate, String paramString3, String paramString4);
  
  //�����˽��
  @Query("SELECT SUM(cdbd.unitNum*cd.price)   FROM DinerBillDishe cdbd  , Dishe cd  ,DinerBill e " +
  		"WHERE cdbd.orderTime>=?1  " +
  		"AND cdbd.discountType=3 " +
  		"AND cdbd.cancelReasonId IS NULL  " +
  		"AND  cd.dishesId=cdbd.dishesId " +
  		"AND e.billId=cdbd.dinerBill " +
  		"AND e.billStatus=?2 " +
  		"AND cdbd.restId=?3 ")
  public abstract Double getdishesSet(Date paramString1,String paramString2,String paramString3);
  
  @Query("SELECT SUM(ss.unitNum * aa.price) FROM DinerBillDishesSet ss, DishesSet aa, DinerBill e " +
  		"WHERE ss.orderTime >=?1  " +
  		"AND ss.discountType=3 " +
		"AND ss.cancelReasonId IS NULL " +
		"AND ss.dsId=aa.dsId " +
		"AND e.billId = ss.dinerBill " +
		"AND e.billStatus =?2 " +
		"AND ss.restId=?3")
  public abstract Double getdishes(Date paramString1,String paramString2,String paramString3);
  
  
  public abstract DinerBillPayment findByDinerBillAndRestIdAndPaymentType(DinerBill paramDinerBill, String paramString, PaymentType paramPaymentType);

  public abstract List<DinerBillPayment> findByDinerBillAndRestIdAndPaymentTypeIn(DinerBill paramDinerBill, String paramString, List<PaymentType> paramList);

  @Query("select sum(d.molingModeCost) as molingModeCost from DinerBill d where d.restaurant.restId=?1 and d.cashierEmployee.empId=?2 and d.payTime >= ?3 and d.billStatus='3' ")
  public abstract BigDecimal getCurrentTotalMolingModeCost(String paramString1, String paramString2, Date paramDate);

  @Query("select sum(d.oriCost) as oriCost from DinerBill d where d.restaurant.restId=?1 and d.createEmployee.empId=?2 and d.createTime >= ?3 and ( d.billStatus='1' or d.billStatus='2' or d.billStatus='4' or d.billStatus='9' ) ")
  public abstract BigDecimal getCurrentTotalUnPayBillCost(String paramString1, String paramString2, Date paramDate);

  @Query("select sum(d.saveCost) as saveCost from DinerBill d where d.restaurant.restId=?1 and d.cashierEmployee.empId=?2 and d.payTime >= ?3 and d.billStatus='3' ")
  public abstract BigDecimal getCurrentTotalSaveCost(String paramString1, String paramString2, Date paramDate);

  @Query("select sum(d.serviceChargeMoney) as serviceChargeMoney from DinerBill d where d.restaurant.restId=?1 and d.cashierEmployee.empId=?2 and d.payTime >= ?3 and d.billStatus='3' ")
  public abstract BigDecimal getCurrentTotalServiceChargeMoney(String paramString1, String paramString2, Date paramDate);

  @Query("select sum(cdbd.realCost) as  realCost from DinerBill cdb,DinerBillDishe cdbd where cdb.restaurant.restId=?1 and cdb.cashierEmployee.empId=?2 and cdb.payTime >= ?3 and cdb.billId = cdbd.dinerBill.billId and cdb.billStatus='3'  and ( cdbd.dishesStatus = '3' or cdbd.dishesStatus = '4') ")
  public abstract BigDecimal getCurrentTotalTuiCaiMoney(String paramString1, String paramString2, Date paramDate);

  @Query("select sum(dish.price) as realCost from DinerBill cdb,DinerBillDishe cdbd ,Dishe dish where dish.dishesId = cdbd.dishesId and cdb.restaurant.restId=?1 and cdb.cashierEmployee.empId=?2 and cdb.payTime >= ?3 and cdb.billId = cdbd.dinerBill.billId and cdb.billStatus='3'  and  cdbd.discountType = '3'  ")
  public abstract BigDecimal getCurrentTotalZengCaiMoney(String paramString1, String paramString2, Date paramDate);
}

