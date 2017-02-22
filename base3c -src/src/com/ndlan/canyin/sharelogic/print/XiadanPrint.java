 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.core.common.DiscountTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.print.PrinterException;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
import java.util.List;
 
 public class XiadanPrint
 {
   public static void print(DinerBill dinerBill, List<DinerBillDishe> dinerBillDishes, Printer printer, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     if ((dinerBillDishes == null) || (dinerBillDishes.size() == 0))
     {
       return;
     }
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(dinerBill.getRestaurant().getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setCashierName(dinerBill.getCashierName());
     dinerBillVo.setWaiterName(dinerBill.getWaiterName());
     dinerBillVo.setOperatorName((printParaments != null) && (printParaments.get("operatorName") != null) ? String.valueOf(printParaments.get("operatorName")) : "");
     dinerBillVo.setOrderTime(new Date());
     if (dinerBill.getTable() != null) {
       dinerBillVo.setTabName(dinerBill.getTable().getTabName());
     }
     dinerBillVo.setNotes(dinerBill.getAllNotes());
     dinerBillVo.setBillType(dinerBill.getBillType());
 
     String isOneBillOneTimes = printer.getIsOneBillOneTimes();
 
     int times = printer.getPrintTimes();
 
     for (int i = 0; i < times; i++)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(isOneBillOneTimes))
       {
         List dinerBillDisheVos = null;
 
         for (DinerBillDishe dishe : dinerBillDishes)
         {
           DinerBillDisheVo dinerBillDisheVo = getPrintDisheInfo(dishe);
 
           if (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsSet())) {
             List<DinerBillDisheVo> dsDishes = dinerBillDisheVo.getDishesSetDishesList();
             for (DinerBillDisheVo dsDish : dsDishes) {
               dinerBillDisheVos = new ArrayList();
               List dsDishList = new ArrayList();
               dsDishList.add(dsDish);
               dinerBillDisheVo.setDishesSetDishesList(dsDishList);
               dinerBillDisheVos.add(dinerBillDisheVo);
               dinerBillVo.setDinerBillDishes(dinerBillDisheVos);
               AbstractPrinter aprinter = PrinterFactory.getXiadanPrinter(dinerBillVo);
               aprinter.print(printer, i + 1, printParaments);
             }
           }
           else {
             dinerBillDisheVos = new ArrayList();
             dinerBillDisheVos.add(dinerBillDisheVo);
             dinerBillVo.setDinerBillDishes(dinerBillDisheVos);
             AbstractPrinter aprinter = PrinterFactory.getXiadanPrinter(dinerBillVo);
             aprinter.print(printer, i + 1, printParaments);
           }
         }
       }
       else {
         List dinerBillDisheVos = new ArrayList();
 
         for (DinerBillDishe dishe : dinerBillDishes) {
           dinerBillDisheVos.add(getPrintDisheInfo(dishe));
         }
         dinerBillVo.setDinerBillDishes(dinerBillDisheVos);
         AbstractPrinter aprinter = PrinterFactory.getXiadanPrinter(dinerBillVo);
         aprinter.print(printer, i + 1, printParaments);
       }
     }
   }
 
   private static DinerBillDisheVo getPrintDisheInfo(DinerBillDishe dishe)
   {
     DinerBillDisheVo dinerBillDisheVo = new DinerBillDisheVo();
 
     String dishesName = dishe.getDishesName();
 
     if (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsSet())) {
       dishesName = "+" + dishesName;
     }
 
     if (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsUserDefined())) {
       dishesName = dishesName + "(�?";
     }
 
     if (DiscountTypeEnum.GIVE.getCode().equals(dishe.getDiscountType())) {
       dishesName = dishesName + "(�?";
     }
     dinerBillDisheVo.setDishesName(dishesName);
     dinerBillDisheVo.setUnitNum(Float.valueOf(dishe.getUnitNum()));
     dinerBillDisheVo.setUnitName(dishe.getUnitName());
     dinerBillDisheVo.setNotes(dishe.getAllNotes());
     if (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsSet())) {
       dinerBillDisheVo.setIsSet(dishe.getIsSet());
       List dishesSetDishesList = new ArrayList();
       List<DinerBillDishe> billDishes = dishe.getDishesSetDishesList();
       for (DinerBillDishe dinerBillDishe : billDishes) {
         DinerBillDisheVo billDisheVo = new DinerBillDisheVo();
         billDisheVo.setDishesName(dinerBillDishe.getDishesName());
         billDisheVo.setUnitNum(Float.valueOf(dinerBillDishe.getUnitNum()));
         billDisheVo.setUnitName(dinerBillDishe.getUnitName());
         dishesSetDishesList.add(billDisheVo);
       }
       dinerBillDisheVo.setDishesSetDishesList(dishesSetDishesList);
     }
 
     return dinerBillDisheVo;
   }
 }

