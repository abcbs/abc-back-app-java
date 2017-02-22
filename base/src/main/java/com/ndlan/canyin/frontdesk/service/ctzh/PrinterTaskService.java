 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.PrinterLog;
 import com.ndlan.canyin.base.entity.ctzh.PrinterTask;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.ctzh.PrinterTaskDao;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import java.util.Date;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class PrinterTaskService extends BaseService<PrinterTaskDao, PrinterTask>
 {
 
   @Autowired
   PrinterLogService printerLogService;
 
   public void savePrinterTaskAndLog(String restId, DinerBill dinerBill, Printer printer, PrinterTypeEnum printerType)
   {
     PrinterTask printerTask = new PrinterTask();
     printerTask.setRestId(restId);
     if (dinerBill != null) {
       printerTask.setBillId(dinerBill.getBillId());
       printerTask.setBillNo(dinerBill.getBillNo());
     }
     printerTask.setPrinter(printer);
     printerTask.setTaskTime(new Date());
     printerTask.setPrintType(printerType.getCode());
     this.self.save(printerTask);
     PrinterLog printerLog = new PrinterLog();
     printerLog.setRestId(restId);
     printerLog.setPrinter(printer);
     this.printerLogService.save(printerLog);
   }
   @Autowired
   public void setDao(PrinterTaskDao dao) {
     super.setDao(dao);
   }
 }

