package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface TableAreaDao extends BaseDao<TableArea, String>
{
  @Query("select t from TableArea t left join t.restaurant r where  r.restId=?1 order by t.createTime asc")
  public abstract List<TableArea> getTableAreaByRestId(String paramString);

  public abstract TableArea findByNameAndRestaurant(String paramString, Restaurant paramRestaurant);

  @Query("select t from TableArea t , Table table where t.areaId = table.tableArea.areaId and table.tabId = ?1 ")
  public abstract TableArea getTableAreaByTableId(String paramString);
}

