package com.ndlan.canyin.base.repository.synch;

import com.ndlan.canyin.base.entity.synch.Transaction;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface TransactionDao extends BaseDao<Transaction, String>
{
  public abstract List<Transaction> findByRestId(String paramString);

  public abstract List<Transaction> findByRestIdAndStatus(String paramString1, String paramString2);
}

