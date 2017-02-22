package com.ndlan.canyin.base.repository.hygl;

import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.repository.BaseDao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface MembershipCardOperationDao extends BaseDao<MembershipCardOperation, String>
{
  @Query("select sum(d.paidinCash) from MembershipCardOperation d left join d.paymentType p where d.restId=?1 and d.createEmployee.empId=?2 and d.createTime >= ?3 and p.paymentType = ?4 and d.cardOperationType = ?5")
  public abstract BigDecimal getPaidinCashSumByPaymentType(String paramString1, String paramString2, Date paramDate, String paramString3, String paramString4);

  @Query("select sum(d.cashPledge) from MembershipCardOperation d left join d.membershipCard m left join d.paymentType p where d.restId=?1 and d.createEmployee.empId=?2 and d.createTime >= ?3 and p.paymentType = ?4 and d.cardOperationType = ?5")
  public abstract BigDecimal getCashPledgeSumByPaymentType(String paramString1, String paramString2, Date paramDate, String paramString3, String paramString4);

  @Query("select sum(d.cashPledge) from MembershipCardOperation d where d.restId=?1 and d.createEmployee.empId=?2 and d.createTime >= ?3 and d.cardOperationType = ?4")
  public abstract BigDecimal getCashPledgeSum(String paramString1, String paramString2, Date paramDate, String paramString3);

  @Query("select sum(d.balance) from MembershipCardOperation d where d.restId=?1 and d.createEmployee.empId=?2 and d.createTime >= ?3 and d.cardOperationType = ?4")
  public abstract BigDecimal getBalanceSum(String paramString1, String paramString2, Date paramDate, String paramString3);

  public abstract List<MembershipCardOperation> findByRestIdAndDinerBill(String paramString, DinerBill paramDinerBill);

  public abstract List<MembershipCardOperation> findByRestMemberInfo(RestMemberInfo paramRestMemberInfo);

  @Query("select d from MembershipCardOperation d where d.restId=?1 and d.cardOperationType in (?2) and d.membershipCard.mcId = ?3 order by d.createTime desc")
  public abstract List<MembershipCardOperation> findByRestIdAndCardOperationTypeIn(String paramString1, List<String> paramList, String paramString2);

  @Query("select sum(d.consumeCash) from MembershipCardOperation d where d.restId=?1 and d.membershipCard.mcId=?2 and d.cardOperationType = ?3")
  public abstract BigDecimal getConsumeCashSumByMcId(String paramString1, String paramString2, String paramString3);
}

