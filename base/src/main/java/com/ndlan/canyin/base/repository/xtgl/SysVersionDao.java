package com.ndlan.canyin.base.repository.xtgl;

import com.ndlan.canyin.base.entity.xtgl.SysVersion;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface SysVersionDao extends BaseDao<SysVersion, String>
{
  @Query("select s from SysVersion s order by createTime desc ")
  public abstract List<SysVersion> findAllOrderByCreateTimeDesc();

  public abstract List<SysVersion> findByRestId(String paramString);
}

