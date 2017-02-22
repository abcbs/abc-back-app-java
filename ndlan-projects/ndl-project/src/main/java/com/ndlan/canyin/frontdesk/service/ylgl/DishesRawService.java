 package com.ndlan.canyin.frontdesk.service.ylgl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.base.entity.ylgl.DishesRaw;
 import com.ndlan.canyin.base.repository.ylgl.DishesRawDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 @Lazy
 public class DishesRawService extends BaseService<DishesRawDao, DishesRaw>
 {
   private DishesRawDao dishesRawDao;
 
   public List<DishesRaw> findByRestIdAndDishe(String restId, Dishe dishe)
   {
     return this.dishesRawDao.findByRestIdAndDishe(restId, dishe);
   }
 
   public List<DishesRaw> getByRestIdAndDishesId(String restId, String dishesId) {
     return this.dishesRawDao.getByRestIdAndDishesId(restId, dishesId);
   }
   @Autowired
   public void setDishesRawDao(DishesRawDao dishesRawDao) { super.setDao(dishesRawDao);
     this.dishesRawDao = dishesRawDao;
   }
 }

