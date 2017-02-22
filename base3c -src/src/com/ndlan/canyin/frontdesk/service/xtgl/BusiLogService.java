 package com.ndlan.canyin.frontdesk.service.xtgl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.xtgl.BusiLog;
 import com.ndlan.canyin.base.repository.xtgl.BusiLogDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class BusiLogService extends BaseService<BusiLogDao, BusiLog>
 {
   @Autowired
   public void setBaseDao(BusiLogDao dataLogDao)
   {
     super.setDao(dataLogDao);
   }
 }

