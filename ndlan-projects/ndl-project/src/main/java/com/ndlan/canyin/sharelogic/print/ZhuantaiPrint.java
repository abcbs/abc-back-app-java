 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.print.PrinterException;
import java.util.Date;
 
 public class ZhuantaiPrint
 {
   public static void print(DinerBill dinerBill, String oldTabName, String tabName, Printer printer)
     throws PrinterException
   {
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(dinerBill.getRestaurant().getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setCashierName(dinerBill.getCashierName());
     dinerBillVo.setWaiterName(dinerBill.getWaiterName());
     dinerBillVo.setOrderTime(new Date());
     dinerBillVo.setTabName(tabName);
     dinerBillVo.setOldTabName(oldTabName);
     AbstractPrinter aPrinter = PrinterFactory.getZhuantaiPrinter(dinerBillVo);
     aPrinter.print(printer, 1, null);
   }
 }

