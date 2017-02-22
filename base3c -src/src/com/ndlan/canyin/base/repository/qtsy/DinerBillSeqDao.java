package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.DinerBillSeq;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface DinerBillSeqDao extends BaseDao<DinerBillSeq, String>
{
  public abstract List<DinerBillSeq> findByRestId(String paramString);

  public abstract List<DinerBillSeq> findByRestIdAndBillDateAndBillSeqType(String paramString1, Date paramDate, String paramString2);

  @Query("select d from DinerBillSeq d  where  d.billDate=?1 ")
  public abstract List<DinerBillSeq> getDinerBillSeqByBillDate(Date paramDate);

  public abstract List<DinerBillSeq> findByBillDateAndRestId(Date paramDate, String paramString);
}

