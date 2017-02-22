package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface TableBillRelationDao extends BaseDao<TableBillRelation, String>
{
  public abstract List<TableBillRelation> findByTableAndTabBillType(Table paramTable, String paramString);

  public abstract List<TableBillRelation> findByTable(Table paramTable);
}

