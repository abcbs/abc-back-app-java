package com.ndlan.canyin.base.repository.hygl;

import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface MembershipCardDao extends BaseDao<MembershipCard, String>
{
  public abstract List<MembershipCard> findByRestIdAndRestMemberInfoIsNull(String paramString);

  public abstract List<MembershipCard> findByCardNoAndRestId(String paramString1, String paramString2);

  public abstract MembershipCard findByCardNoAndCardPasswordAndRestId(String paramString1, String paramString2, String paramString3);

  public abstract List<MembershipCard> findByRestId(String paramString);

  public abstract List<MembershipCard> findByRestMemberInfoAndRestId(RestMemberInfo paramRestMemberInfo, String paramString);

  public abstract List<MembershipCard> findByRestMemberInfoAndCardStatus(RestMemberInfo paramRestMemberInfo, String paramString);

  public abstract List<MembershipCard> findByRestMemberInfoAndRestIdAndCardStatus(RestMemberInfo paramRestMemberInfo, String paramString1, String paramString2);
}

