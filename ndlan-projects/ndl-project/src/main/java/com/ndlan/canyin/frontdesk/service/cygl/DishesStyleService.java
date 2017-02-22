 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesStyle;
 import com.ndlan.canyin.base.repository.cygl.DishesStyleDao;
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
 public class DishesStyleService extends BaseService<DishesStyleDao, DishesStyle>
 {
   public Page<DishesStyle> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, getRequiredSort(sortType));
     Map filters = SearchFilter.parse(searchParams);
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), DishesStyle.class);
 
     Map restfilters = new HashMap();
     restfilters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification specWithRest = DynamicSpecifications.bySearchFilter(restfilters.values(), DishesStyle.class);
 
     return ((DishesStyleDao)getDao()).findAll(Specifications.where(spec).and(specWithRest), pageRequest);
   }
 
   public List<DishesStyle> findAllDishesStyleByRestId(String restId)
   {
     return ((DishesStyleDao)getDao()).findByRestId(restId);
   }
 
   public List<DishesStyle> findByRestIdAndEnableStatus(String restId) {
     return ((DishesStyleDao)getDao()).findByRestIdAndEnableStatus(restId, EnableStatusEnum.NORMAL.getCode());
   }
   @Autowired
   public void setBaseDao(DishesStyleDao dishesStyleDao) { super.setDao(dishesStyleDao); }
 
   public List<DishesStyle> findByRestId(String restId)
   {
     return ((DishesStyleDao)getDao()).findByRestId(restId);
   }
 
   public DishesStyle findByRestIdAndName(String restId, String name) {
     return ((DishesStyleDao)getDao()).findByRestIdAndName(restId, name);
   }
 }

