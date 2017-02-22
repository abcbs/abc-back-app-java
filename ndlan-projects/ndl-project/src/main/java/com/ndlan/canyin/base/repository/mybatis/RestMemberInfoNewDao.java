package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface RestMemberInfoNewDao
{
  public abstract List<RestMemberInfo> getAll(@Param("restend") List restend, @Param("name") String paramString2, @Param("offset") String paramString3, @Param("start") int paramInt1, @Param("pageSize") int paramInt2, @Param("mbid") String paramString4);

  public abstract int getAllCount(@Param("restend") List restend, @Param("name") String paramString2, @Param("offset") String paramString3);

  public abstract List<RestMemberInfo> getAllByrestId(@Param("restId") String restId, @Param("name") String paramString2, @Param("offset") String paramString3, @Param("start") int paramInt1, @Param("pageSize") int paramInt2, @Param("mbid") String paramString4);

  public abstract int getAllCountByrestId(@Param("restId") String restId, @Param("name") String paramString2, @Param("offset") String paramString3);
}

