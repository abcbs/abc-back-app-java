 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.CostExpend;
 import com.ndlan.canyin.base.repository.qtsy.CostExpendDao;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class CostExpendService extends BaseService<CostExpendDao, CostExpend>
 {
   @Autowired
   public void setDao(CostExpendDao dao)
   {
     super.setDao(dao);
   }
 
   public List<CostExpend> findByRestId(String restId) {
     return ((CostExpendDao)getDao()).findByRestId(restId);
   }
 
   public Page<CostExpend> searchWithTime(String restId, String startDate, String endDate, Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType) {
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
     Map filters = SearchFilter.parse(searchParams);
     try {
       if ((startDate != null) && (!startDate.isEmpty())) filters.put("GTE_createTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, format.parse(startDate + " 00:00:00")));
       if ((endDate != null) && (!endDate.isEmpty())) filters.put("LTE_createTime", new SearchFilter("createTime", SearchFilter.Operator.LTE, format.parse(endDate + " 23:59:59"))); 
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), CostExpend.class);
     return ((CostExpendDao)getDao()).findAll(spec, pageRequest);
   }
 }

