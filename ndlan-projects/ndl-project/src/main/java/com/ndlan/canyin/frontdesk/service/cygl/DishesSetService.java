 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
 import com.ndlan.canyin.base.repository.cygl.DishesSetDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.ArrayList;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesSetService extends BaseService<DishesSetDao, DishesSet>
 {
   private DishesSetDao dishesSetDao;
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   public void setBaseDao(DishesSetDao dishesSetDao)
   {
     super.setDao(dishesSetDao);
     this.dishesSetDao = dishesSetDao;
   }
 
   public List<DishesSet> findByRestIdOrderByShowSeqAsc(String restId)
   {
     List ds = this.dishesSetDao.findByRestIdAndIsStopSellOrderByShowSeqAsc(restId, TrueFalseEnum.FALSE.getCode());
     if (ds == null)
     {
       return new ArrayList();
     }
     return ds;
   }
 }

