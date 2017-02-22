package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface OrderBillDisheDao extends BaseDao<OrderBillDishe, String>
{
  public abstract List<OrderBillDishe> findByDisheAndRestId(Dishe paramDishe, String paramString);

  public abstract List<OrderBillDishe> findByTableOrder(TableOrder paramTableOrder);

  public abstract List<OrderBillDishe> findByRestIdAndTableOrder(String paramString, TableOrder paramTableOrder);
}

