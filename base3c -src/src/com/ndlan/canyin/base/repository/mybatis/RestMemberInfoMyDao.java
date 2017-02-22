package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface RestMemberInfoMyDao
{
  public abstract List<RestMemberInfo> getAll(@Param("restId") String paramString1, @Param("mcclassId") String paramString2, @Param("cardStatus") String paramString3, @Param("keywords") String paramString4, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getAllCount(@Param("restId") String paramString1, @Param("mcclassId") String paramString2, @Param("cardStatus") String paramString3, @Param("keywords") String paramString4);

  public abstract List<MembershipCard> getAllByMemberId(@Param("mbId") String paramString);
}

