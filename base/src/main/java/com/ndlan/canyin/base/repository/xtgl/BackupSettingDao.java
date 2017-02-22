package com.ndlan.canyin.base.repository.xtgl;

import com.ndlan.canyin.base.entity.xtgl.BackupSetting;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface BackupSettingDao extends BaseDao<BackupSetting, String>
{
  public abstract List<BackupSetting> findByRestIdOrderByCreateTimeDesc(String paramString);

  @Query("select a from BackupSetting a ")
  public abstract List<BackupSetting> findAll();
}

