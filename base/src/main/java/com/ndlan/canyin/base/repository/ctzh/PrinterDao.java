package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Printer;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface PrinterDao extends BaseDao<Printer, String>
{
  public abstract List<Printer> findByRestIdAndStatus(String paramString1, String paramString2);

  public abstract List<Printer> findByRestIdAndStatusAndType(String paramString1, String paramString2, String paramString3);
}

