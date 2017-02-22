 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhuDishe;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillZiZhuDisheDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class DinerBillZiZhuDisheService extends BaseService<DinerBillZiZhuDisheDao, DinerBillZiZhuDishe>
 {
   @Autowired
   public void setDao(DinerBillZiZhuDisheDao dao)
   {
     super.setDao(dao);
   }
 
   public List<DinerBillZiZhuDishe> findByRestId(String restId) {
     return ((DinerBillZiZhuDisheDao)getDao()).findByRestId(restId);
   }
 }

