package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.IndexFavorite;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface IndexFavoriteDao extends BaseDao<IndexFavorite, String>
{
  @Query("select t from IndexFavorite t left join t.employee r where r.empId=?1 order by t.showSeq ")
  public abstract List<IndexFavorite> getMyIndexFavorites(String paramString);

  @Query("select t from IndexFavorite t left join t.employee r where r.empId=?1 and t.authorityModule.parent.parentCamId=?2 order by t.showSeq ")
  public abstract List<IndexFavorite> getIndexByEmpIdAndParentCamId(String paramString1, String paramString2);
}

