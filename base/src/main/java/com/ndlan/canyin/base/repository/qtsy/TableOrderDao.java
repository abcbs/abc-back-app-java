package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.repository.BaseDao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface TableOrderDao extends BaseDao<TableOrder, String>
{
  @Query("select sum(d.prepay) from TableOrder d left join d.paymentType p where d.restId=?1 and d.createEmployee.empId=?2 and d.createTime >= ?3 and p.paymentType = ?4")
  public abstract BigDecimal getPrepaySumByPaymentType(String paramString1, String paramString2, Date paramDate, String paramString3);

  @Query("select tableOrder from TableOrder tableOrder where restId=?1 and orderStatus=?2 and orderTime >= ?3 order by orderTime asc")
  public abstract List<TableOrder> findByRestIdAndOrderStatus(String paramString1, String paramString2, Date paramDate);

  public abstract List<TableOrder> findByRestIdAndOrderStatus(String paramString1, String paramString2);

  public abstract List<TableOrder> findByTable(Table paramTable);

  public abstract List<TableOrder> findByRestIdAndOnlineOrderId(String paramString1, String paramString2);

  public abstract TableOrder findByRestIdAndBillId(String paramString1, String paramString2);
  
  @Query("SELECT a.orderTime  from  TableOrder a WHERE a.table.tabId=?1  AND a.orderStatus='2' and a.restId=?2  and a.orderTime >= ?3  and a.orderTime <= ?4" )
  public abstract List<TableOrder> findOrdertime(String paramString1, String paramString2,Date paramString3,Date paramString4);
}

