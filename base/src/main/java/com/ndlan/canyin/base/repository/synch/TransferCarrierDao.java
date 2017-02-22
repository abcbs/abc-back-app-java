package com.ndlan.canyin.base.repository.synch;

import com.ndlan.canyin.base.entity.synch.TransferCarrier;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface TransferCarrierDao extends BaseDao<TransferCarrier, String>
{
  public abstract List<TransferCarrier> findBytransactionIdOrderBySeqAsc(int paramInt);
}

