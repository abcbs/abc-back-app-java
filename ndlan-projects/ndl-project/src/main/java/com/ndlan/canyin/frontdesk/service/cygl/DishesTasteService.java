 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.frontdesk.service.BaseService;
 import com.ndlan.canyin.base.entity.cygl.DishesTaste;
 import com.ndlan.canyin.base.repository.cygl.DishesTasteDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.domain.Specifications;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 @Lazy
 public class DishesTasteService extends BaseService<DishesTasteDao, DishesTaste>
 {
   public Page<DishesTaste> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, new String[] { "createTime" }));
     Map filters = SearchFilter.parse(searchParams);
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), DishesTaste.class);
 
     Map restfilters = new HashMap();
     restfilters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification specWithRest = DynamicSpecifications.bySearchFilter(restfilters.values(), DishesTaste.class);
 
     return ((DishesTasteDao)getDao()).findAll(Specifications.where(spec).and(specWithRest), pageRequest);
   }
 
   public String findNameArray(String idArray)
   {
     StringBuffer nameArray = new StringBuffer();
     if ((idArray != null) && (!idArray.isEmpty()))
     {
       List ar = ImmutableList.copyOf(StringUtils.split(idArray, ","));
       for (int i = 0; i < ar.size(); i++) {
         String e = (String)ar.get(i);
         DishesTaste dishesTaste = (DishesTaste)this.self.loadOne(e);
         if (dishesTaste == null)
           continue;
         nameArray.append(dishesTaste.getName());
         if (i < ar.size() - 1) {
           nameArray.append(",");
         }
       }
     }
 
     return nameArray.toString();
   }
   public List<DishesTaste> findAllDishesTasteByRestId(String restId) {
     return ((DishesTasteDao)getDao()).findByRestIdAndEnableStatus(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<DishesTaste> findByCodeAndRestIdAndEnableStatus(String code, String restId)
   {
     return ((DishesTasteDao)getDao()).findByCodeAndRestIdAndEnableStatus(code, restId, EnableStatusEnum.NORMAL.getCode());
   }
   @Autowired
   public void setBaseDao(DishesTasteDao dishesTasteDao) {
     super.setDao(dishesTasteDao);
   }
 }

