package com.ndlan.canyin.base.repository.sygl;

import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface PaymentTypeDao extends BaseDao<PaymentType, String>
{
  public abstract List<PaymentType> findByRestIdOrderByShowSeqAscCreateTimeAsc(String paramString);
  @Query("select paymentTypes from PaymentType paymentTypes where restId=?1 and enableStatus = ?2 and paymentType not in('1')  order by showSeq asc")
  public abstract List<PaymentType> findByRestIdAndEnableStatusOrderByShowSeqAscCreateTimeAsc(String paramString1, String paramString2);

  public abstract PaymentType findByRestIdAndPaymentTypeAndSysdataTypeAndEnableStatus(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract List<PaymentType> findByRestIdAndEnableStatusAndPaymentTypeIn(String paramString1, String paramString2, List<String> paramList);

  public abstract List<PaymentType> findByRestIdAndPaymentName(String paramString1, String paramString2);
  
  @Query("select a from PaymentType a where a.restId =?1 and a.paymentType ='1' ")
  public abstract PaymentType getCptId(String paramString1);
/**
 * @param restId
 * @return
 */
@Query("select paymentTypes from PaymentType paymentTypes where restId=?1 and payStatus ='1' and payCode not in('1')  order by showSeq asc ")
public abstract List<PaymentType> findPaymentTypeByRestIDAndPayStatus(String restId);
  
}

