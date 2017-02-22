 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.DishesMaterial;
 import com.ndlan.canyin.base.repository.cygl.DishesMaterialDao;
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
 public class DishesMaterialService extends BaseService<DishesMaterialDao, DishesMaterial>
 {
   public Page<DishesMaterial> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, getRequiredSort(sortType));
     Map filters = SearchFilter.parse(searchParams);
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), DishesMaterial.class);
 
     Map restfilters = new HashMap();
     restfilters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification specWithRest = DynamicSpecifications.bySearchFilter(restfilters.values(), DishesMaterial.class);
 
     return ((DishesMaterialDao)getDao()).findAll(Specifications.where(spec).and(specWithRest), pageRequest);
   }
 
   public List<DishesMaterial> findAllDishesMaterialByRestId(String restId) {
     return ((DishesMaterialDao)getDao()).findByRestId(restId);
   }
 
   public List<DishesMaterial> findByRestIdAndEnableStatus(String restId) {
     return ((DishesMaterialDao)getDao()).findByRestIdAndEnableStatus(restId, EnableStatusEnum.NORMAL.getCode());
   }
   @Autowired
   public void setBaseDao(DishesMaterialDao dishesMaterialDao) {
     super.setDao(dishesMaterialDao);
   }
 
   public List<DishesMaterial> findByRestId(String restId) {
     return ((DishesMaterialDao)getDao()).findByRestId(restId);
   }
 
   public DishesMaterial findByRestIdAndName(String restId, String name) {
     return ((DishesMaterialDao)getDao()).findByRestIdAndName(restId, name);
   }
 }

