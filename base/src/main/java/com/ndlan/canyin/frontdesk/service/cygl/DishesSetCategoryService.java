 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.google.common.collect.Maps;
import com.ndlan.canyin.frontdesk.service.BaseService;
 import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
 import com.ndlan.canyin.base.repository.cygl.DishesSetCategoryDao;
 import com.ndlan.canyin.core.common.CategoryLevelEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesSetCategoryService extends BaseService<DishesSetCategoryDao, DishesSetCategory>
 {
   private DishesSetCategoryDao dishesSetCategoryDao;
 
   public Page<DishesSetCategory> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String restId, String[] sortType)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, sortType));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), DishesSetCategory.class);
 
     return this.dishesSetCategoryDao.findAll(spec, pageRequest);
   }
 
   public List<DishesSetCategory> findByRestIdAndEnableStatusOrderByShowSeqAsc(String restId) {
     return this.dishesSetCategoryDao.findByRestIdAndEnableStatusOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<DishesSetCategory> findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(String restId, String categoryLevel) {
     if (categoryLevel.equals(CategoryLevelEnum.FIRST.getCode()))
     {
       return findFirst(this.dishesSetCategoryDao.findByRestIdAndEnableStatusOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode()));
     }
     return this.dishesSetCategoryDao.findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode(), categoryLevel);
   }
 
   private List<DishesSetCategory> findFirst(List<DishesSetCategory> all)
   {
     List f = new ArrayList();
     for (DishesSetCategory e : all)
     {
       if ((StringUtils.isEmpty(e.getCategoryLevel())) || (e.getCategoryLevel().equals(CategoryLevelEnum.FIRST.getCode())))
       {
         f.add(e);
       }
     }
     return f;
   }
   public List<DishesSetCategory> findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryIdOrderByShowSeqAsc(String restId, String categoryLevel, String parentCategoryId) {
     return this.dishesSetCategoryDao.findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryIdOrderByShowSeqAsc(restId, EnableStatusEnum.NORMAL.getCode(), categoryLevel, parentCategoryId);
   }
 
   public List<DishesSetCategory> searchPage(String restId)
   {
     Map filters = Maps.newHashMap();
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), DishesSetCategory.class);
     return this.dishesSetCategoryDao.findAll(spec);
   }
 
   public List<DishesSetCategory> findByRestIdOrderByShowSeqAsc(String restId) {
     return this.dishesSetCategoryDao.findByRestIdOrderByShowSeqAsc(restId);
   }
 
   public DishesSetCategory findByRestIdAndCategoryName(String restId, String categoryName) {
     return this.dishesSetCategoryDao.findByRestIdAndCategoryName(restId, categoryName);
   }
   @Autowired
   public void setBaseDao(DishesSetCategoryDao dishesSetCategoryDao) {
     super.setDao(dishesSetCategoryDao);
     this.dishesSetCategoryDao = dishesSetCategoryDao;
   }
 }

