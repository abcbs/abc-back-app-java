 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class PrinterService extends BaseService<PrinterDao, Printer>
 {
   private PrinterDao printerDao;
 
   public List<Printer> findAllPrinters(String restId)
   {
     return this.printerDao.findByRestIdAndStatus(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<Printer> findByRestIdAndStatusAndType(String restId, String status, String type) {
     return this.printerDao.findByRestIdAndStatusAndType(restId, status, type);
   }
 
   public List<Printer> findByRestIdAndStatusAndTypeAndUserPrintId(String restId, String status, String type, String userId)
   {
     List alls = this.printerDao.findByRestIdAndStatusAndType(restId, status, type);
 
     String userPrintId = UserSettingCache.getInstance().getUserCache(userId).getPrinterId();
     if ((StringUtils.isEmpty(userPrintId)) || (PrinterTypeEnum.BACK_KITCHEN.getCode().equals(type)))
     {
       return alls;
     }
     return getUserFrontdeskPrints(alls, type, userPrintId);
   }
 
   public List<Printer> getUserFrontdeskPrints(List<Printer> alls, String type, String userPrintId)
   {
     List users = new ArrayList();
     if ((StringUtils.isEmpty(userPrintId)) || (PrinterTypeEnum.BACK_KITCHEN.getCode().equals(type)))
     {
       return alls;
     }
     if ((alls != null) && (alls.size() > 0))
     {
       for (Printer p : alls)
       {
         if (p.getPrinterId().equals(userPrintId))
         {
           users.add(p);
         }
       }
     }
     return users;
   }
 
   @Autowired
   public void setPrinterDao(PrinterDao printerDao) {
     super.setDao(printerDao);
     this.printerDao = printerDao;
   }
 
   public List<Printer> findByRestIdAndStatus(String restId, String status) {
     return this.printerDao.findByRestIdAndStatus(restId, status);
   }
 }

