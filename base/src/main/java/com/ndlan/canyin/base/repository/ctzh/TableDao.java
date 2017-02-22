package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface TableDao extends BaseDao<Table, String>
{
  public abstract List<Table> findByTabNoAndRestId(String paramString1, String paramString2);
  
  public abstract List<Table> findByTabNoAndRestIdAndIsEnable(String paramString1, String paramString2, String paramString3);

  public abstract List<Table> findByRestId(String paramString);

  public abstract List<Table> findByRestIdAndIsEnable(String paramString1, String paramString2);

  @Query("select t from Table t left join t.tableArea r where r.areaId=?1 and t.restId=?2 order by t.tabName asc")
  public abstract List<Table> findByTableAreaIdAndRestId(String paramString1, String paramString2);
  
  
  
}

