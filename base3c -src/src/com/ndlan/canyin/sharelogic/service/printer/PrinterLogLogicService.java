 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.PrinterLog;
 import com.ndlan.canyin.base.repository.ctzh.PrinterLogDao;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class PrinterLogLogicService extends BaseLogicService<PrinterLogDao, PrinterLog>
 {
   @Autowired
   public void setDao(PrinterLogDao dao)
   {
     super.setDao(dao);
   }
 }

