 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
 import com.ndlan.canyin.base.repository.cygl.DishesCategoryDao;
 import com.ndlan.canyin.core.common.CategoryLevelEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesCategoryService extends BaseService<DishesCategoryDao, DishesCategory>
 {
   private DishesCategoryDao categoryDao;
 
   public List<DishesCategory> findAllDishesCategoryByRestId(String restId)
   {
     return this.categoryDao.findByRestIdOrderByShowSeqAsc(restId);
   }
 
   public List<DishesCategory> findByRestIdAndEnableStatus(String restId)
   {
     return this.categoryDao.findByRestIdAndEnableStatusOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<DishesCategory> findByRestIdAndEnableStatusAndCategoryLevel(String restId, String categoryLevel) {
     if (categoryLevel.equals(CategoryLevelEnum.FIRST.getCode()))
     {
       return findFirst(this.categoryDao.findByRestIdAndEnableStatusOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode()));
     }
     return this.categoryDao.findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode(), categoryLevel);
   }
 
   private List<DishesCategory> findFirst(List<DishesCategory> all)
   {
     List f = new ArrayList();
     for (DishesCategory e : all)
     {
       if ((StringUtils.isEmpty(e.getCategoryLevel())) || (e.getCategoryLevel().equals(CategoryLevelEnum.FIRST.getCode())))
       {
         f.add(e);
       }
     }
     return f;
   }
 
   public List<DishesCategory> findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryId(String restId, String categoryLevel, String parentCategoryId) {
     return this.categoryDao.findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryId(restId, EnableStatusEnum.NORMAL.getCode(), categoryLevel, parentCategoryId);
   }
 
   @Autowired
   public void setBaseDao(DishesCategoryDao dishesCategoryDao) {
     super.setDao(dishesCategoryDao);
     this.categoryDao = dishesCategoryDao;
   }
 }

