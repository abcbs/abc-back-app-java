 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.RestNews;
 import com.ndlan.canyin.base.repository.ctzh.RestNewsDao;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.domain.Specifications;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional(readOnly=true)
 public class RestaurantNewsService extends BaseService<RestNewsDao, RestNews>
 {
   public List<RestNews> findByTitleAndRestId(String title, String restId)
   {
     return ((RestNewsDao)getDao()).findByTitleAndRestId(title, restId);
   }
 
   public List<RestNews> findByRestIdAndIsShowOrderByTopTimeDescCreateTimeDesc(String restId, String isShow) {
     return ((RestNewsDao)getDao()).findByRestIdAndIsShowOrderByTopTimeDescCreateTimeDesc(restId, isShow);
   }
 
   public Page<RestNews> searchRestNews(String restId, int pageNumber, int pagzSize)
   {
     Sort.Direction d = Sort.Direction.DESC;
 
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { "createTime" }));
 
     Map filters = new HashMap();
 
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), RestNews.class);
 
     Map restfilters = new HashMap();
     restfilters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification specWithRest = DynamicSpecifications.bySearchFilter(restfilters.values(), RestNews.class);
     return ((RestNewsDao)getDao()).findAll(Specifications.where(spec).and(specWithRest), pageRequest);
   }
   @Autowired
   public void setBaseDao(RestNewsDao restNewsDao) {
     super.setDao(restNewsDao);
   }
 }

