 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.SelfMessage;
 import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
 import com.ndlan.canyin.base.repository.qtsy.SelfMessageDao;
 import com.ndlan.canyin.core.common.SelfServiceMarkEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class SelfMessageService extends BaseService<SelfMessageDao, SelfMessage>
 {
 
   @Autowired
   SelfMessageDao selfMessageDao;
 
   @Autowired
   public void setDao(SelfMessageDao dao)
   {
     this.selfMessageDao = dao;
     super.setDao(dao);
   }
 
   public List<SelfMessage> findByRestId(String restId)
   {
     return ((SelfMessageDao)getDao()).findByRestId(restId);
   }
   public List<SelfMessage> findByRestIdAndStatusOrderByUpdateTimeDesc(String restId) {
     return ((SelfMessageDao)getDao()).findByRestIdAndStatusOrderByUpdateTimeDesc(restId, TrueFalseEnum.FALSE.getCode());
   }
   public List<SelfMessage> findByRestIdAndStatus(String restId, String status) {
     return ((SelfMessageDao)getDao()).findByRestIdAndStatus(restId, status);
   }
   public List<SelfMessage> findByTypeAndRestAndTab(String mesType, String restId, String tabNo) {
     return ((SelfMessageDao)getDao()).findByMesTypeAndRestIdAndTabNo(mesType, restId, tabNo);
   }
 
   public List<SelfMessage> findByRestAndTab(String mesType, String restId, String tabNo) {
     return ((SelfMessageDao)getDao()).findByRestIdAndTabNo(restId, tabNo);
   }
 
   public List<SelfMessage> findByRestIdAndMesType(String restId, String mesType) {
     return ((SelfMessageDao)getDao()).findByRestIdAndMesType(restId, mesType);
   }
 
   public List<SelfMessage> findBySelfOrderAndMesType(SelfOrder selfOrder, String mesType) {
     return ((SelfMessageDao)getDao()).findBySelfOrderAndMesTypeOrderByCreateTimeDesc(selfOrder, mesType);
   }
 
   public Page<SelfMessage> searchPage(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, getRequiredSort(sortType));
     Map filters = SearchFilter.parse(searchParams);
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String now = sdf.format(new Date());
     try {
       filters.put("GTE_updateTime", new SearchFilter("updateTime", SearchFilter.Operator.GTE, format.parse(now + " 00:00:00")));
       filters.put("LTE_updateTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, format.parse(now + " 23:59:59")));
     } catch (ParseException e) {
       e.printStackTrace();
     }
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), SelfMessage.class);
     return ((SelfMessageDao)getDao()).findAll(Specifications.where(spec), pageRequest);
   }
 
   public Page<SelfMessage> findByStatusAndMesTypeIn(String restId, String status, Collection<String> list, int pageNumber, int pagzSize) {
     Date startDate = getStartTime();
     Date endDate = getEndTime();
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.DESC, new String[] { "updateTime" }));
     Page page = ((SelfMessageDao)getDao()).findByRestIdAndStatusAndUpdateTimeGreaterThanAndUpdateTimeLessThanAndMesTypeIn(restId, status, startDate, endDate, list, pageRequest);
     return page;
   }
 
   public Page<SelfMessage> findByMesTypeIsDisplay(String restId, int pageNumber, int pagzSize) {
     Date startDate = getStartTime();
     Date endDate = getEndTime();
     String display = SelfServiceMarkEnum.CALLERIDDISPLAY.getCode();
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
     Page page = ((SelfMessageDao)getDao()).findByRestIdAndCreateTimeGreaterThanAndCreateTimeLessThanAndMesType(restId, startDate, endDate, display, pageRequest);
     return page;
   }
 
   private Date getStartTime() {
     Calendar todayStart = Calendar.getInstance();
     todayStart.set(11, 0);
     todayStart.set(12, 0);
     todayStart.set(13, 0);
     todayStart.set(14, 0);
     return todayStart.getTime();
   }
 
   private Date getEndTime() {
     Calendar todayEnd = Calendar.getInstance();
     todayEnd.set(11, 23);
     todayEnd.set(12, 59);
     todayEnd.set(13, 59);
     todayEnd.set(14, 999);
     return todayEnd.getTime();
   }
 }

