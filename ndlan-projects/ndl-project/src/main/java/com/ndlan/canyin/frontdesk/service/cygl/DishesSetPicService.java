 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
 import com.ndlan.canyin.base.entity.cygl.DishesSetPic;
 import com.ndlan.canyin.base.repository.cygl.DishesSetPicDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesSetPicService extends BaseService<DishesSetPicDao, DishesSetPic>
 {
   private DishesSetPicDao dishesSetPicDao;
 
   public List<DishesSetPic> findByRestIdAndDishesSet(String restId, DishesSet dishesSet)
   {
     return this.dishesSetPicDao.findByRestIdAndDishesSet(restId, dishesSet);
   }
   public List<DishesSetPic> findByDishesId(String dishesId) {
     return this.dishesSetPicDao.findByDishesId(dishesId);
   }
   @Autowired
   public void setBaseDao(DishesSetPicDao dishesSetPicDao) { super.setDao(dishesSetPicDao);
     this.dishesSetPicDao = dishesSetPicDao;
   }
 }

