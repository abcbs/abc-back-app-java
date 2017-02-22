package com.ndlan.canyin.base.repository.xtgl;

import com.ndlan.canyin.base.entity.xtgl.Backup;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface BackupDao extends BaseDao<Backup, String>
{
  @Query("select obj from Backup obj where obj.bakName = '系统自动备份' and datediff(NOW(),obj.createTime)>?1")
  public abstract List<Backup> getFlushBackups(String paramString);
}

