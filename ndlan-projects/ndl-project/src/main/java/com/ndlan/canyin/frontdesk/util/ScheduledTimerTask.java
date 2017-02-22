 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.frontdesk.service.xtgl.BackupSettingService;
import com.ndlan.canyin.base.entity.xtgl.Backup;
 import com.ndlan.canyin.base.entity.xtgl.BackupSetting;
 import com.ndlan.canyin.base.entity.xtgl.SysLog;
 import com.ndlan.canyin.base.repository.xtgl.BackupDao;
 import com.ndlan.canyin.base.repository.xtgl.SysLogDao;
 import com.ndlan.canyin.core.common.BackUpTypeEnum;
 import com.ndlan.canyin.core.common.SysLogTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.DateProvider;
 import com.ndlan.canyin.core.utils.Identities;
 import java.util.Date;
 import java.util.List;
 import java.util.TimerTask;
 import javax.servlet.ServletContext;
 import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
 public class ScheduledTimerTask extends TimerTask
 {
   BackupSettingService backupSettingService;
   private ServletContext sc;
 
   public void run()
   {
     try
     {
       ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.sc);
 
       this.backupSettingService = ((BackupSettingService)applicationContext.getBean("backupSettingService"));
       BackupDao backupDao = (BackupDao)applicationContext.getBean("backupDao");
       SysLogDao sysLogDao = (SysLogDao)applicationContext.getBean("sysLogDao");
       List<BackupSetting> list = this.backupSettingService.findAll();
       String currenVersion = "2.3.0";
       if (list.size() > 0)
         for (BackupSetting backupSetting : list)
         {
           if (TrueFalseEnum.TRUE.getCode().equals(backupSetting.getIsOpenBackup())) {
             String uuid = Identities.uuid2();
             String dataFileName = DataBackup.backupData(backupSetting.getRestId(), currenVersion, uuid, BackUpTypeEnum.LOCAL.getCode());
             Backup obj = new Backup();
             obj.setBakName("系统自动备份");
             obj.setCreateTime(DateProvider.DEFAULT.getDate());
             obj.setBakPath(dataFileName);
             obj.setBakVersion(currenVersion);
             obj.setRestId(backupSetting.getRestId());
             backupDao.save(obj);
             SysLog sysLog = new SysLog();
             sysLog.setSysLogType(SysLogTypeEnum.SYSLOG.getCode());
             sysLog.setCreateTime(new Date());
             sysLog.setNotes("本地自动备份");
             sysLog.setRestId(backupSetting.getRestId());
             sysLogDao.save(sysLog);
           }
         }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   public ServletContext getSc()
   {
     return this.sc;
   }
 
   public void setSc(ServletContext sc) {
     this.sc = sc;
   }
 }

