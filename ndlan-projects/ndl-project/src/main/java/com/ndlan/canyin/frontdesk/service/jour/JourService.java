 package com.ndlan.canyin.frontdesk.service.jour;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.jour.Jour;
 import com.ndlan.canyin.base.repository.jour.JourDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 @Lazy
 public class JourService extends BaseService<JourDao, Jour>
 {
   private JourDao jourDao;
 
   public List<Jour> findByJourNoAndStatusAndRestId(String jourNo, String jourStatus, String restId)
   {
     return this.jourDao.findByJourNoAndJourStatusAndRestId(jourNo, jourStatus, restId);
   }
   @Autowired
   public void setBaseDao(JourDao dao) {
     super.setDao(dao);
     this.jourDao = dao;
   }
 }

