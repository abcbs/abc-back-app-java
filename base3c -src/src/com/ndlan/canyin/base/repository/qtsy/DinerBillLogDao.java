package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillLog;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface DinerBillLogDao extends BaseDao<DinerBillLog, String>
{
  public abstract List<DinerBillLog> findByRestIdAndDinerBill(String paramString, DinerBill paramDinerBill);

  public abstract List<DinerBillLog> findByRestIdAndOnlineIdOrderByCreateTimeAsc(String paramString1, String paramString2);
}

