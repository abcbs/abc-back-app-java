 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.PrinterLog;
 import com.ndlan.canyin.base.entity.ctzh.PrinterTask;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.ctzh.PrinterTaskDao;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogLogicService;

 import java.util.Date;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional(readOnly=true)
 public class PrinterTaskLogicService extends BaseLogicService<PrinterTaskDao, PrinterTask>
 {
 
   @Autowired
   PrinterLogLogicService printerLogService;
 
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
     save(printerTask);
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

