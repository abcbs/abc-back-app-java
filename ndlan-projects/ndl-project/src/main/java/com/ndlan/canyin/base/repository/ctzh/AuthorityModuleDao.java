package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface AuthorityModuleDao extends BaseDao<AuthorityModule, String>
{
  @Query("select distinct rl from Employee e left join e.roleList gl left join gl.authList rl where  e.empId=?1 and e.restaurant.restId=?2 order by show_seq asc")
  public abstract List<AuthorityModule> getUserAuthorityModule(String paramString1, String paramString2);

  @Query("select a from AuthorityModule a where a.camLevel<4 ")
  public abstract List<AuthorityModule> getParentAuthorityModule();

  @Query("select rl from Role r left join  r.authList rl where  r.crId=?1 ")
  public abstract List<AuthorityModule> getRoleAuthorityModule(String paramString);

  public abstract List<AuthorityModule> getModulesByCamLevelAndParentCamIdOrderByShowSeqAsc(String paramString1, String paramString2);

  @Query("select a from AuthorityModule a  order by a.showSeq")
  public abstract List<AuthorityModule> findAll();
  
  @Query("select a from AuthorityModule a where a.moduleStatus =1 order by a.showSeq")
  public abstract List<AuthorityModule> findList();
}

