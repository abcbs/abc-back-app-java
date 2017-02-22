package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface RoleDao extends BaseDao<Role, String>
{
  public abstract List<Role> findByRestIdAndRoleType(String paramString1, String paramString2);
}

