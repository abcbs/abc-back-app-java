 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
 import com.ndlan.canyin.base.repository.cygl.SpecialPriceIntervalDao;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class SpecialPriceIntervalService extends BaseService<SpecialPriceIntervalDao, SpecialPriceInterval>
 {
   public Page<SpecialPriceInterval> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, null);
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), SpecialPriceInterval.class);
 
     return ((SpecialPriceIntervalDao)getDao()).findAll(spec, pageRequest);
   }
 
   public List<SpecialPriceInterval> getAllSpecialPriceInterval(String restId)
   {
     return ((SpecialPriceIntervalDao)getDao()).findByRestId(restId);
   }
   @Autowired
   public void setBaseDao(SpecialPriceIntervalDao specialPriceIntervalDao) {
     super.setDao(specialPriceIntervalDao);
   }
 }

