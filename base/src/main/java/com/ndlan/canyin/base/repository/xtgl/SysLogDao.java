package com.ndlan.canyin.base.repository.xtgl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.xtgl.SysLog;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface SysLogDao extends BaseDao<SysLog, String>
{
	@Query("select a from Table a where a.isEnable='1' and restId=?1 and dinnerStatus='2'")
	  public abstract List<Table> logoutCheck(String paramString);
}

