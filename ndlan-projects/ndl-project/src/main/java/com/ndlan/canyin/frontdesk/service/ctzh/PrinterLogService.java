 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.PrinterLog;
 import com.ndlan.canyin.base.repository.ctzh.PrinterLogDao;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class PrinterLogService extends BaseService<PrinterLogDao, PrinterLog>
 {
   @Autowired
   public void setDao(PrinterLogDao dao)
   {
     super.setDao(dao);
   }
 }

