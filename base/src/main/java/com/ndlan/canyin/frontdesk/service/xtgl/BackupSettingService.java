 package com.ndlan.canyin.frontdesk.service.xtgl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.xtgl.BackupSetting;
 import com.ndlan.canyin.base.repository.xtgl.BackupSettingDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class BackupSettingService extends BaseService<BackupSettingDao, BackupSetting>
 {
   @Autowired
   public void setDao(BackupSettingDao dao)
   {
     super.setDao(dao);
   }
 
   public List<BackupSetting> findAllByRestId(String restId) {
     return ((BackupSettingDao)getDao()).findByRestIdOrderByCreateTimeDesc(restId);
   }
 
   public List<BackupSetting> findAll()
   {
     return ((BackupSettingDao)getDao()).findAll();
   }
 }

