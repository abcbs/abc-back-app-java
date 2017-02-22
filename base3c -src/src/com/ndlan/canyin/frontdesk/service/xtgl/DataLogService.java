 package com.ndlan.canyin.frontdesk.service.xtgl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.xtgl.DataLog;
 import com.ndlan.canyin.base.repository.xtgl.DataLogDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DataLogService extends BaseService<DataLogDao, DataLog>
 {
   @Transactional(readOnly=false)
   public void saveLog(String dataLogType, String actType, String editStr)
   {
     DataLog dataLog = new DataLog();
     dataLog.setDataLogType(dataLogType);
     dataLog.setOperateAct(actType);
     dataLog.setNotes(editStr);
     this.self.save(dataLog);
   }
   @Autowired
   public void setBaseDao(DataLogDao dataLogDao) {
     super.setDao(dataLogDao);
   }
 }

