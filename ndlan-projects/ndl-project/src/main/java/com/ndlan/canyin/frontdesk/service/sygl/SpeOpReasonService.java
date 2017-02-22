 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.sygl.SpeOpReason;
 import com.ndlan.canyin.base.repository.sygl.SpeOpReasonDao;
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
import org.springframework.stereotype.Component;
 
 @Component
 public class SpeOpReasonService extends BaseService<SpeOpReasonDao, SpeOpReason>
 {
   private SpeOpReasonDao reasonDao;
 
   public Page<SpeOpReason> searchSpeOpReason(Map<String, Object> searchParams, int pageNumber, int pagzSize, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, null);
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), SpeOpReason.class);
     return this.reasonDao.findAll(spec, pageRequest);
   }
 
   public Page<SpeOpReason> getPage(PageRequest pageRequest, String restId, String reaType, String enableStatus)
   {
     Map filters = new HashMap();
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("reaType", new SearchFilter("reaType", SearchFilter.Operator.EQ, reaType));
     filters.put("enableStatus", new SearchFilter("enableStatus", SearchFilter.Operator.EQ, enableStatus));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), SpeOpReason.class);
     return this.reasonDao.findAll(spec, pageRequest);
   }
 
   public List<SpeOpReason> findByRestIdAndReaType(String restId, String reaType)
   {
     return this.reasonDao.findByRestIdAndReaTypeAndEnableStatus(restId, reaType, EnableStatusEnum.NORMAL.getCode());
   }
 
   @Autowired
   public void setDao(SpeOpReasonDao dao)
   {
     super.setDao(dao);
     this.reasonDao = dao;
   }
 }

