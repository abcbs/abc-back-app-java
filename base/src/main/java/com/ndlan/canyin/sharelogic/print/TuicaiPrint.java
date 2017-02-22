 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.print.PrinterException;
 import java.util.ArrayList;
import java.util.List;
 
 public class TuicaiPrint
 {
   public static void print(DinerBillDishe dinerBillDishe, Printer printer, String cancelNum)
     throws PrinterException
   {
     DinerBill dinerBill = dinerBillDishe.getDinerBill();
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(dinerBill.getRestaurant().getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setCashierName(dinerBill.getCashierName());
     dinerBillVo.setWaiterName(dinerBill.getWaiterName());
     dinerBillVo.setOrderTime(dinerBillDishe.getOrderTime());
     if (dinerBill.getTable() != null) {
       dinerBillVo.setTabName(dinerBill.getTable().getTabName());
     }
     dinerBillVo.setCancleTime(dinerBillDishe.getCancelTime());
 
     List dinerBillDisheVos = new ArrayList();
     DinerBillDisheVo dinerBillDisheVo = new DinerBillDisheVo();
     dinerBillDisheVo.setDishesName("(退)" + dinerBillDishe.getDishesName());
     dinerBillDisheVo.setUnitNum(Float.valueOf(dinerBillDishe.getUnitNum()));
     dinerBillDisheVo.setUnitName(dinerBillDishe.getUnitName());
     dinerBillDisheVo.setCancleNum(Float.valueOf(cancelNum));
     dinerBillDisheVo.setIsSet(dinerBillDishe.getIsSet());
 
     if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDishe.getIsSet())) {
       List dishesSetDishesList = new ArrayList();
       List<DinerBillDishe> billDishes = dinerBillDishe.getDishesSetDishesList();
       for (DinerBillDishe dbd : billDishes) {
         DinerBillDisheVo billDisheVo = new DinerBillDisheVo();
         billDisheVo.setDishesName(dbd.getDishesName());
         billDisheVo.setUnitNum(Float.valueOf(dbd.getUnitNum()));
         billDisheVo.setUnitName(dbd.getUnitName());
         dishesSetDishesList.add(billDisheVo);
       }
       dinerBillDisheVo.setDishesSetDishesList(dishesSetDishesList);
     }
 
     dinerBillDisheVos.add(dinerBillDisheVo);
 
     dinerBillVo.setDinerBillDishes(dinerBillDisheVos);
     AbstractPrinter aprinter = PrinterFactory.getTuicaiPrinter(dinerBillVo);
     aprinter.print(printer, 1, null);
   }
 }

