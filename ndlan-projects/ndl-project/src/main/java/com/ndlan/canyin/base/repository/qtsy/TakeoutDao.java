package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

public abstract interface TakeoutDao extends BaseDao<Takeout, String>
{
  public abstract Takeout findByRestIdAndDinerBill(String paramString, DinerBill paramDinerBill);

  public abstract Takeout findByRestIdAndOnlineTakeoutId(String paramString1, String paramString2);

  public abstract List<Takeout> findByRestIdAndMobileOrderByCreateTimeDesc(String paramString1, String paramString2);

  @Query("SELECT t.mobile FROM Takeout t WHERE t.createTime >= ?1 AND t.restId = ?2 AND t.mobile IS NOT NULL AND t.mobile != '' AND t.mobile like ?3 GROUP BY t.mobile ORDER BY COUNT(*) DESC LIMIT 0,10")
  public abstract List<String> getMobileList(Date paramDate, String paramString1, String paramString2);

  @Query("SELECT t.sendAddress FROM Takeout t WHERE t.createTime >= ?1 AND t.restId = ?2 AND t.mobile = ?3 GROUP BY t.sendAddress ORDER BY COUNT(*) DESC LIMIT 0,10")
  public abstract List<String> getSendAddressListByMobile(Date paramDate, String paramString1, String paramString2);

  @Query("SELECT t.sendAddress FROM Takeout t WHERE t.createTime >= ?1 AND t.restId = ?2 AND t.telephone = ?3 GROUP BY t.sendAddress ORDER BY COUNT(*) DESC LIMIT 0,10")
  public abstract List<String> getSendAddressListByTelephone(Date paramDate, String paramString1, String paramString2);

  @Query("from Takeout t order by t.createTime desc LIMIT 0,1")
  public abstract List<Takeout> getLastSendAtOnce();
  
  @Query(" from Takeout t WHERE t.dinerBill.billId=?1")
  public abstract List<Takeout> geTakeout(String paramString1);

}

