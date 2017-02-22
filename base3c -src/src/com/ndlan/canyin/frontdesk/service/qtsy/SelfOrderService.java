 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
 import com.ndlan.canyin.base.repository.qtsy.SelfOrderDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class SelfOrderService extends BaseService<SelfOrderDao, SelfOrder>
 {
   @Autowired
   public void setDao(SelfOrderDao dao)
   {
     super.setDao(dao);
   }
 
   public List<SelfOrder> findByRestId(String restId) {
     return ((SelfOrderDao)getDao()).findByRestId(restId);
   }
 
   public SelfOrder getSelfOrderByTabNo(String restId, String tabNo)
   {
     List list = ((SelfOrderDao)getDao()).findByRestIdAndTabNoAndStatusOrderByCreateTimeDesc(restId, tabNo, TrueFalseEnum.TRUE.getCode());
     if ((list != null) && (list.size() > 0))
     {
       return (SelfOrder)list.get(0);
     }
     return null;
   }
 }

