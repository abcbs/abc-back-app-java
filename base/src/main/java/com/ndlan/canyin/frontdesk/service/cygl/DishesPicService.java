 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesPic;
 import com.ndlan.canyin.base.repository.cygl.DishesPicDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesPicService extends BaseService<DishesPicDao, DishesPic>
 {
   public List<DishesPic> findByDishesId(String dishesId)
   {
     return ((DishesPicDao)getDao()).findByDishesId(dishesId);
   }
   @Autowired
   public void setBaseDao(DishesPicDao dishesPicDao) { super.setDao(dishesPicDao);
   }
 }

