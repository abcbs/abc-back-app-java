 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.frontdesk.front.bill.RunBarPayController;
import com.ndlan.canyin.frontdesk.message.ware.MessageCenterNativeServer;
import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.xtgl.BackupSettingService;
import com.ndlan.canyin.frontdesk.transport.TransportClient;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.xtgl.BackupSetting;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.service.RegisterService;
 import com.ndlan.canyin.core.utils.LowPriorityExecutor;
 import com.ndlan.canyin.core.utils.VersionUpdateUtil;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.List;
 import java.util.Properties;
 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;
 import javax.servlet.ServletRequestAttributeEvent;
 import javax.servlet.ServletRequestAttributeListener;
 import javax.servlet.ServletRequestEvent;
 import javax.servlet.ServletRequestListener;
 import javax.servlet.http.HttpSessionAttributeListener;
 import javax.servlet.http.HttpSessionBindingEvent;
 import javax.servlet.http.HttpSessionBindingListener;
 import javax.servlet.http.HttpSessionEvent;
 import javax.servlet.http.HttpSessionListener;
 import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
 public class CustomizeListener
   implements ServletContextListener, HttpSessionListener, ServletRequestListener, ServletRequestAttributeListener, HttpSessionAttributeListener, HttpSessionBindingListener
 {
//   private RunBarPayController th;
   public void contextDestroyed(ServletContextEvent arg0)
   {
   }
 
   public void contextInitialized(ServletContextEvent servletContextEvent)
   {
//	   if ( th == null) {  
//		   th = new RunBarPayController();  
//		   th.start();  
//       }
     com.ndlan.canyin.core.common.Constants.currentEnv = getProperty("development.type");
     VersionUpdateUtil.update(0);
 
     ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
 
     MessageCenterNativeServer messageCenterNativeServer = (MessageCenterNativeServer)applicationContext.getBean("messageCenterNativeServer");
     messageCenterNativeServer.start();
 
     RestaurantService restaurantService = (RestaurantService)applicationContext.getBean("restaurantService");
     List rs = restaurantService.findAll();
     if ((rs != null) && (rs.size() > 0))
     {
       final Restaurant r = (Restaurant)rs.get(0);
       UserSettingCache.getInstance().init(r);
 
       BackupSettingService backupSettingService = (BackupSettingService)applicationContext.getBean("backupSettingService");
       List<BackupSetting> list = backupSettingService.findAll();
       if (list.size() > 0) {
         for (BackupSetting backupSetting : list)
         {
           if (TrueFalseEnum.TRUE.getCode().equals(backupSetting.getIsOpenBackup())) {
             int backup_hour = backupSetting.getBackupHour() == null ? 2 : backupSetting.getBackupHour().intValue();
             int backup_min = backupSetting.getBackupMin() == null ? 0 : backupSetting.getBackupMin().intValue();
             PathUtil.setBackUpPath(backupSetting.getBackupDiskAddress());
             TimerManagerFactory.createTimerManage(backup_hour, backup_min, servletContextEvent.getServletContext());
           }
         }
       }
 
       TransportClient transportClient = (TransportClient)applicationContext.getBean("transportClient");
       transportClient.setApplicationContext(applicationContext);
       transportClient.setRestId(r.getRestId());
       transportClient.start();
 
       CloudBillService cloudBillService = (CloudBillService)applicationContext.getBean("cloudBillService");
       cloudBillService.saveQueueToDB();
       cloudBillService.submitQueueToCloud(r.getRestId());
 
       LowPriorityExecutor.execLog(new Runnable()
       {
         public void run() {
           RegisterService.everyDayResiter(r.getRestId(), "2.3.0", r.getRestInfoDesc());
         }
       });
     }
   }
 
   public String getProperty(String key)
   {
     try {
       String fileName = "/application.properties";
       Properties p = new Properties();
       InputStream in = getClass().getResourceAsStream(fileName);
       p.load(in);
       in.close();
       if (p.containsKey(key))
         return p.getProperty(key);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public void sessionCreated(HttpSessionEvent arg0)
   {
   }
 
   public void sessionDestroyed(HttpSessionEvent arg0)
   {
   }
 
   public void requestDestroyed(ServletRequestEvent arg0)
   {
   }
 
   public void requestInitialized(ServletRequestEvent arg0)
   {
   }
 
   public void attributeAdded(ServletRequestAttributeEvent arg0)
   {
   }
 
   public void attributeRemoved(ServletRequestAttributeEvent arg0)
   {
   }
 
   public void attributeReplaced(ServletRequestAttributeEvent arg0)
   {
   }
 
   public void attributeAdded(HttpSessionBindingEvent arg0)
   {
   }
 
   public void attributeRemoved(HttpSessionBindingEvent arg0)
   {
   }
 
   public void attributeReplaced(HttpSessionBindingEvent arg0)
   {
   }
 
   public void valueBound(HttpSessionBindingEvent arg0)
   {
   }
 
   public void valueUnbound(HttpSessionBindingEvent arg0)
   {
   }
 }

