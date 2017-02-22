 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.PayPrint;
import com.ndlan.canyin.sharelogic.print.XiadanPrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;

 import java.awt.print.PrinterException;
 import java.util.HashMap;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DinerBillPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printPay(DinerBill dinerBill, String printType, String printName, Printer printer, String billFrom, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     PayPrint.print(dinerBill, "PAY", printer, billFrom, printParaments);
   }
 
   public void printXiadan(DinerBill dinerBill, Printer printer, HashMap<String, Object> printParaments) throws Exception {
     boolean allDisheCategory = TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(printer.getIsAllDishe());
     List list = this.printerService.findDinnerDishesByDisheCategorys(allDisheCategory, printer.getDisheCategory(), dinerBill.getDinerBillDishes());
     if ((list != null) && (list.size() > 0))
       XiadanPrint.print(dinerBill, list, printer, printParaments);
   }
 }

