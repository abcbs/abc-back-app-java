 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesUnit;
 import com.ndlan.canyin.base.repository.cygl.DishesUnitDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.domain.Specifications;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DishesUnitService extends BaseService<DishesUnitDao, DishesUnit>
 {
   public Page<DishesUnit> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, null);
     Map filters = SearchFilter.parse(searchParams);
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), DishesUnit.class);
 
     Map restfilters = new HashMap();
     restfilters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification specWithRest = DynamicSpecifications.bySearchFilter(restfilters.values(), DishesUnit.class);
 
     return ((DishesUnitDao)getDao()).findAll(Specifications.where(spec).and(specWithRest), pageRequest);
   }
 
   public List<DishesUnit> findAllDishesUnitByRestId(String restId)
   {
     return ((DishesUnitDao)getDao()).findByRestId(restId);
   }
 
   public List<DishesUnit> findByRestIdAndEnableStatus(String restId) {
     return ((DishesUnitDao)getDao()).findByRestIdAndEnableStatus(restId, EnableStatusEnum.NORMAL.getCode());
   }
   @Autowired
   public void setBaseDao(DishesUnitDao dishesUnitDao) {
     super.setDao(dishesUnitDao);
   }
 }

