 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillDishesSetDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class DinerBillDishesSetService extends BaseService<DinerBillDishesSetDao, DinerBillDishesSet>
 {
   private DinerBillDishesSetDao dinerBillDishesSetDao;
 
   public List<DinerBillDishesSet> findByRestIdAndDinerBill(String restId, DinerBill dinerBill)
   {
     return this.dinerBillDishesSetDao.findByRestIdAndDinerBill(restId, dinerBill);
   }
   @Autowired
   public void setDao(DinerBillDishesSetDao dao) { super.setDao(dao);
     this.dinerBillDishesSetDao = dao;
   }
 }

