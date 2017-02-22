package com.ndlan.canyin.base.repository.hygl;

import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface MembershipCardClassDao extends BaseDao<MembershipCardClass, String>
{
  public abstract List<MembershipCardClass> findByNameAndRestId(String paramString1, String paramString2);

  public abstract List<MembershipCardClass> findByCashDiscountAndRestId(CashDiscount paramCashDiscount, String paramString);

  public abstract List<MembershipCardClass> findByRestId(String paramString);
  
  @Query("select a from MembershipCardClass a where a.restId=?")
  public List<MembershipCardClass> findMemberCardClassByRestId(String shopId);
  
}

