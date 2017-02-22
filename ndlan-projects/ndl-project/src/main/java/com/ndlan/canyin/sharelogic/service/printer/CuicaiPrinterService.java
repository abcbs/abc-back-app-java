 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.CuicaiPrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.cygl.DishesCategory;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.DishesStatusEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;

 import java.awt.print.PrinterException;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class CuicaiPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printCuiCaiOne(String restId, DinerBillDishe dinerBillDishe, DinerBill dinerBill)
     throws PrinterException
   {
     Table table = dinerBill.getTable();
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode());
 
     for (Printer printer : printers)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsAllDishe()))
       {
         if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsAllArea())) {
           CuicaiPrint.print(dinerBill, dinerBillDishe, printer, "URGE");
         }
         else if (table != null) {
           String areaIds = printer.getTableArea();
           if (StringUtils.isNotEmpty(areaIds)) {
             for (String areaId : areaIds.split(",")) {
               if (table.getTableArea().getAreaId().equals(areaId)) {
                 CuicaiPrint.print(dinerBill, dinerBillDishe, printer, "URGE");
                 break;
               }
             }
           }
         }
 
       }
       else
       {
         String disheCategorys = printer.getDisheCategory();
         if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsAllArea())) {
           if (StringUtils.isNotEmpty(disheCategorys)) {
             for (String disheCategory : disheCategorys.split(",")) {
               if (dinerBillDishe.getDishesCategory().getCategoryId().equals(disheCategory)) {
                 CuicaiPrint.print(dinerBill, dinerBillDishe, printer, "URGE");
                 break;
               }
             }
           }
         }
         else if (table != null) {
           String areaIds = printer.getTableArea();
           if (StringUtils.isNotEmpty(areaIds))
             for (String areaId : areaIds.split(",")) {
               if ((!table.getTableArea().getAreaId().equals(areaId)) || 
                 (!StringUtils.isNotEmpty(disheCategorys))) continue;
               for (String disheCategory : disheCategorys.split(","))
                 if (dinerBillDishe.getDishesCategory().getCategoryId().equals(disheCategory)) {
                   CuicaiPrint.print(dinerBill, dinerBillDishe, printer, "URGE");
                   break;
                 }
             }
         }
       }
     }
   }
 
   public void printCuiCaiAll(String restId, DinerBill dinerBill)
     throws PrinterException
   {
     Table table = dinerBill.getTable();
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode());
 
     for (Printer printer : printers)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsAllDishe()))
       {
         if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsAllArea())) {
           CuicaiPrint.print(dinerBill, null, printer, "URGEALL");
         }
         else if (table != null) {
           String areaIds = printer.getTableArea();
           if (StringUtils.isNotEmpty(areaIds)) {
             for (String areaId : areaIds.split(",")) {
               if (table.getTableArea().getAreaId().equals(areaId)) {
                 CuicaiPrint.print(dinerBill, null, printer, "URGEALL");
                 break;
               }
             }
           }
         }
 
       }
       else
       {
         String disheCategorys = printer.getDisheCategory();
         DinerBillDishe dinerBillDishe;
         if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsAllArea())) {
           if (!StringUtils.isNotEmpty(disheCategorys))
             continue;
           Set categorys = new HashSet();
           for (String disheCategory : disheCategorys.split(",")) {
             for (Iterator localIterator4 = dinerBill.getDinerBillDishes().iterator(); localIterator4.hasNext(); ) { dinerBillDishe = (DinerBillDishe)localIterator4.next();
 
               if ((!DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishe.getDishesStatus())) || 
                 (!dinerBillDishe.getDishesCategory().getCategoryId().equals(disheCategory))) continue;
               categorys.add(dinerBillDishe.getDishesCategory().getCategoryId());
             }
 
           }
 
           Iterator localIterator2 = categorys.iterator(); if (!localIterator2.hasNext()) continue; String c = (String)localIterator2.next();
           CuicaiPrint.print(dinerBill, null, printer, "URGEALL");
         }
         else if (table != null) {
           String areaIds = printer.getTableArea();
           if ((!StringUtils.isNotEmpty(areaIds)) || 
             (!StringUtils.isNotEmpty(disheCategorys)))
             continue;
           HashSet<String> categorys = new HashSet();
           Object localObject1;
//           DinerBillDishe dinerBillDishe;
           for (String disheCategory : disheCategorys.split(",")) {
             for (localObject1 = dinerBill.getDinerBillDishes().iterator(); ((Iterator)localObject1).hasNext(); ) { dinerBillDishe = (DinerBillDishe)((Iterator)localObject1).next();
 
               if ((!DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishe.getDishesStatus())) || 
                 (!dinerBillDishe.getDishesCategory().getCategoryId().equals(disheCategory))) continue;
               ((Set)categorys).add(dinerBillDishe.getDishesCategory().getCategoryId());
             }
 
           }
 
           for (String c : categorys) {
//             DinerBillDishe localDinerBillDishe1 = (localObject1 = disheCategorys.split(",")).length;
        	   int localDinerBillDishe1 = (disheCategorys.split(",")).length;
             for (int dinerBillDishei = 0; dinerBillDishei < localDinerBillDishe1; dinerBillDishei++) {
//            	 String disheCategory = localObject1[dinerBillDishei];
            	 String disheCategory = (disheCategorys.split(","))[dinerBillDishei];
               if (c.equals(disheCategory)) {
                 CuicaiPrint.print(dinerBill, null, printer, "URGEALL");
                 break;
               }
             }
           }
         }
       }
     }
   }
 }

