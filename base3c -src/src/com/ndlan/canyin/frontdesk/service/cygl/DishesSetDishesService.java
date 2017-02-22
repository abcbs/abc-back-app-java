 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.base.entity.cygl.DishesSet;
 import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
 import com.ndlan.canyin.base.repository.cygl.DishesSetDishesDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=false)
 public class DishesSetDishesService extends BaseService<DishesSetDishesDao, DishesSetDishes>
 {
   private DishesSetDishesDao dishesSetDishesDao;
 
   public List<DishesSetDishes> findByRestIdAndDishesSetAndDishe(String restId, DishesSet dishesSet, Dishe dishe)
   {
     return this.dishesSetDishesDao.findByRestIdAndDishesSetAndDishe(restId, dishesSet, dishe);
   }
 
   public List<DishesSetDishes> findByRestIdAndDishesSet(String restId, DishesSet dishesSet) {
     return this.dishesSetDishesDao.findByRestIdAndDishesSet(restId, dishesSet);
   }
 
   public void delete(String id) {
     this.dishesSetDishesDao.delete(id);
   }
   @Autowired
   public void setBaseDao(DishesSetDishesDao dishesSetDishesDao) {
     super.setDao(dishesSetDishesDao);
     this.dishesSetDishesDao = dishesSetDishesDao;
   }
 }

