 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.xtgl.ResettleLog;
 import com.ndlan.canyin.base.repository.xtgl.ResettleLogDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class ResettleLogService extends BaseService<ResettleLogDao, ResettleLog>
 {
   @Autowired
   public void setBaseDao(ResettleLogDao resettleLogDao)
   {
     super.setDao(resettleLogDao);
   }
 }

