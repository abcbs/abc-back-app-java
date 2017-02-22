 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.TuicaiPrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;

 import java.util.ArrayList;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TuicaiPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printTuicai(String restId, DinerBillDishe dinerBillDishe, Table table, String cancelNum)
     throws Exception
   {
     DinerBill dinerBill = dinerBillDishe.getDinerBill();
 
     String area = (table == null) || (table.getTableArea() == null) ? null : table.getTableArea().getAreaId();
     List<Printer> printers = this.printerService.findPrintersByArea(area, dinerBill.getBillType(), restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode());
 
     for (Printer printer : printers) {
       boolean allDisheCategory = TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(printer.getIsAllDishe());
       DinerBillDishe newDinerBillDishe = null;
 
       if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDishe.getIsSet())) {
         newDinerBillDishe = this.printerService.findDinnerDishesByDisheCategorysForSet(allDisheCategory, printer.getDisheCategory(), dinerBillDishe);
         newDinerBillDishe.setDinerBill(dinerBill);
       } else {
         List dinerBillDisheList = new ArrayList();
         dinerBillDisheList.add(dinerBillDishe);
         List list = this.printerService.findDinnerDishesByDisheCategorys(allDisheCategory, printer.getDisheCategory(), dinerBillDisheList);
         if ((list != null) && (list.size() > 0)) {
           newDinerBillDishe = (DinerBillDishe)list.get(0);
           newDinerBillDishe.setDinerBill(dinerBill);
         }
       }
       if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDishe.getIsSet())) {
         if ((newDinerBillDishe.getDishesSetDishesList() != null) && (newDinerBillDishe.getDishesSetDishesList().size() > 0)) {
           TuicaiPrint.print(newDinerBillDishe, printer, cancelNum);
         }
       }
       else if (newDinerBillDishe != null)
         TuicaiPrint.print(newDinerBillDishe, printer, cancelNum);
     }
   }
 }

