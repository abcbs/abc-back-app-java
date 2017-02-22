package com.ndlan.canyin.base.repository.hygl;

import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public abstract interface RestMemberInfoDao extends BaseDao<RestMemberInfo, String>
{
  public abstract RestMemberInfo findByMobileAndRestId(String paramString1, String paramString2);

  public abstract List<RestMemberInfo> findByNameAndRestId(String paramString1, String paramString2);

  public abstract List<RestMemberInfo> findByMobile(String paramString);

  public abstract List<RestMemberInfo> findByRestId(String paramString);

  public abstract List<RestMemberInfo> findByRestIdAndGender(String paramString1, String paramString2);

  @Query("select m from RestMemberInfo m where MONTH(m.birthday) = MONTH(:date) and DAYOFMONTH(m.birthday) = DAYOFMONTH(:date) and m.restId = :restId")
  public abstract List<RestMemberInfo> findByDateIsBirthday(@Param("restId") String paramString, @Param("date") Date paramDate);

  @Query("select m from RestMemberInfo m where MONTH(m.birthday) = MONTH(:date) and DAYOFMONTH(m.birthday) = DAYOFMONTH(:date) and m.restId = :restId and m.gender= :gender")
  public abstract List<RestMemberInfo> findByDateIsBirthdayAndGender(@Param("restId") String paramString1, @Param("date") Date paramDate, @Param("gender") String paramString2);

  @Query("select m from MembershipCard c inner join c.restMemberInfo m where exists (select MAX(d.updateTime) from DinerBill d where c.mcId = d.membershipCard.mcId group by d.membershipCard.mcId having date_format(MAX(d.updateTime),'%Y%m%d') = date_format(:lastUseDate,'%Y%m%d')) and m.restId = :restId")
  public abstract List<RestMemberInfo> findByNeedWakeUp(@Param("restId") String paramString, @Param("lastUseDate") Date paramDate);

  @Query("select m from MembershipCard c inner join c.restMemberInfo m where exists (select MAX(d.updateTime) from DinerBill d where c.mcId = d.membershipCard.mcId group by d.membershipCard.mcId having date_format(MAX(d.updateTime),'%Y%m%d') = date_format(:lastUseDate,'%Y%m%d')) and m.restId = :restId and m.gender= :gender")
  public abstract List<RestMemberInfo> findByNeedWakeUpAndGender(@Param("restId") String paramString1, @Param("lastUseDate") Date paramDate, @Param("gender") String paramString2);
}

