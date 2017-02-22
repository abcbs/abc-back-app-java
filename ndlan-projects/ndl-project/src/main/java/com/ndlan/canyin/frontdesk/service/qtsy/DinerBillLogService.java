 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillLog;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillLogDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
 @Component
 public class DinerBillLogService extends BaseService<DinerBillLogDao, DinerBillLog>
 {
   DinerBillLogDao dinerBillLogDao;
 
   public List<DinerBillLog> findByRestIdAndDinerBill(String restId, DinerBill dinerBill)
   {
     return this.dinerBillLogDao.findByRestIdAndDinerBill(restId, dinerBill);
   }
 
   public List<DinerBillLog> findByRestIdAndOnlineId(String restId, String onlineId)
   {
     return this.dinerBillLogDao.findByRestIdAndOnlineIdOrderByCreateTimeAsc(restId, onlineId);
   }
   @Autowired
   public void setDao(DinerBillLogDao dao) { this.dinerBillLogDao = dao;
     super.setDao(dao);
   }
 }

