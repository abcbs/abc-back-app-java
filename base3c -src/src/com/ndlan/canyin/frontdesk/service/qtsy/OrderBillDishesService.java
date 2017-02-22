 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
 import com.ndlan.canyin.base.entity.qtsy.TableOrder;
 import com.ndlan.canyin.base.repository.qtsy.OrderBillDisheDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class OrderBillDishesService extends BaseService<OrderBillDisheDao, OrderBillDishe>
 {
   private OrderBillDisheDao orderBillDisheDao;
 
   public List<OrderBillDishe> findByRestIdAndTableOrder(String restId, TableOrder tableOrder)
   {
     return this.orderBillDisheDao.findByRestIdAndTableOrder(restId, tableOrder);
   }
   @Autowired
   public void setDao(OrderBillDisheDao dao) {
     this.orderBillDisheDao = dao;
     super.setDao(dao);
   }
 }

