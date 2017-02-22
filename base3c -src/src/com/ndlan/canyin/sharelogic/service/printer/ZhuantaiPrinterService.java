 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.ZhuantaiPrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;

 import java.awt.print.PrinterException;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class ZhuantaiPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printZhuantai(String restId, DinerBill dinerBill, String oldTabName, String tabName)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode());
 
     for (Printer printer : printers)
     {
       ZhuantaiPrint.print(dinerBill, oldTabName, tabName, printer);
     }
   }
 }

