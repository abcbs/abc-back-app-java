 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.XiadanPrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;

 import java.util.HashMap;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class XiadanPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printXiadan(String restId, DinerBill dinerBill, Table table, HashMap<String, Object> printParaments)
     throws Exception
   {
     String area = (table == null) || (table.getTableArea() == null) ? null : table.getTableArea().getAreaId();
     List<Printer> printers = this.printerService.findPrintersByArea(area, dinerBill.getBillType(), restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode());
 
     for (Printer printer : printers) {
       boolean allDisheCategory = TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(printer.getIsAllDishe());
       List list = this.printerService.findDinnerDishesByDisheCategorys(allDisheCategory, printer.getDisheCategory(), dinerBill.getDinerBillDishes());
       if ((list != null) && (list.size() > 0))
         XiadanPrint.print(dinerBill, list, printer, printParaments);
     }
   }
 }

