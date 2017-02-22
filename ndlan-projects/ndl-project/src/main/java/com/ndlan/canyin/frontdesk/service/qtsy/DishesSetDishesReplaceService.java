 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
 import com.ndlan.canyin.base.entity.cygl.DishesSetDishesReplace;
 import com.ndlan.canyin.base.repository.cygl.DishesSetDishesReplaceDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesSetDishesReplaceService extends BaseService<DishesSetDishesReplaceDao, DishesSetDishesReplace>
 {
   private DishesSetDishesReplaceDao dishesSetDishesReplaceDao;
 
   public List<DishesSetDishesReplace> findByRestIdAndDsIdAndDishesSetDishes(String restId, String dsId, DishesSetDishes dishesSetDishes)
   {
     return this.dishesSetDishesReplaceDao.findByRestIdAndDsIdAndDishesSetDishes(restId, dsId, dishesSetDishes);
   }
   @Autowired
   public void setBaseDao(DishesSetDishesReplaceDao dishesSetDishesReplaceDao) {
     super.setDao(dishesSetDishesReplaceDao);
     this.dishesSetDishesReplaceDao = dishesSetDishesReplaceDao;
   }
 }

