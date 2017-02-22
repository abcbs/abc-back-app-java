 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.print.PrinterException;
import java.util.Date;
 
 public class CuicaiPrint
 {
   public static void print(DinerBill dinerBill, DinerBillDishe dinerBillDishe, Printer printer, String cuiCaiType)
     throws PrinterException
   {
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(dinerBill.getRestaurant().getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setCashierName(dinerBill.getCashierName());
 
     dinerBillVo.setUrgeTime(new Date());
     if (dinerBill.getTable() != null) {
       dinerBillVo.setTabName(dinerBill.getTable().getTabName());
     }
     dinerBillVo.setWaiterName(dinerBill.getCreateEmployee().getName());
     DinerBillDisheVo dinerBillDisheVo = new DinerBillDisheVo();
     if (dinerBillDishe != null) {
       dinerBillVo.setOrderTime(dinerBillDishe.getCreateTime());
       dinerBillDisheVo.setDishesName(dinerBillDishe.getDishesName());
     } else {
       dinerBillVo.setOrderTime(dinerBill.getCreateTime());
     }
     dinerBillVo.setDinerBillDisheVo(dinerBillDisheVo);
     AbstractPrinter aprinter = PrinterFactory.getCuicaiPrinter(dinerBillVo, cuiCaiType);
     aprinter.print(printer, 1, null);
   }
 }

