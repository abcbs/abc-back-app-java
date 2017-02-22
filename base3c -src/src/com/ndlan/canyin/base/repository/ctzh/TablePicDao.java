package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TablePic;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface TablePicDao extends BaseDao<TablePic, String>
{
  public abstract List<TablePic> findByRestIdAndTable(String paramString, Table paramTable);
}

