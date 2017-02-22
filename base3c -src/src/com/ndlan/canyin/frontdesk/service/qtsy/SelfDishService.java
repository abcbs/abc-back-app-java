 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.SelfDish;
 import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
 import com.ndlan.canyin.base.repository.qtsy.SelfDishDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class SelfDishService extends BaseService<SelfDishDao, SelfDish>
 {
   @Autowired
   public void setDao(SelfDishDao dao)
   {
     super.setDao(dao);
   }
 
   public List<SelfDish> findByRestId(String restId) {
     return ((SelfDishDao)getDao()).findByRestId(restId);
   }
   public List<SelfDish> findBySelfOrderAndStatus(SelfOrder selfOrder, String status) {
     return ((SelfDishDao)getDao()).findBySelfOrderAndStatus(selfOrder, status);
   }
 }

