 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhuDishe;
 import com.ndlan.canyin.base.entity.qtsy.Takeout;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.core.common.BillTypeEnum;
 import com.ndlan.canyin.core.common.DiscountTypeEnum;
 import com.ndlan.canyin.core.common.DishesStatusEnum;
 import com.ndlan.canyin.core.utils.BeanUtils;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.HashMap;
import java.util.List;
 
 public class PayPrint
 {
   public static void print(DinerBill dinerBill, String type, Printer printer, String from, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     DinerBillVo dinerBillVo = new DinerBillVo();
     String message = "欢迎您的光临！";
     if ("PREDICT".equalsIgnoreCase(type)) {
       message = "预结小票";
     }
     dinerBillVo.setMessage(message);
     dinerBillVo.setRestName(dinerBill.getRestaurant().getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setResettleTime(dinerBill.getResettleTime());
     MembershipCard membershipCard = dinerBill.getMembershipCard();
     if (membershipCard != null) {
       dinerBillVo.setCardNo(membershipCard.getCardNo());
       dinerBillVo.setMemberIntegral(membershipCard.getMemberIntegral());
     }
     dinerBillVo.setCashierName(dinerBill.getCashierName());
     dinerBillVo.setMolingModeCost(dinerBill.getMolingModeCost());
     dinerBillVo.setOddChange(dinerBill.getOddChange());
     dinerBillVo.setOriCost(dinerBill.getOriCost());
     dinerBillVo.setPayableCost(dinerBill.getPayableCost());
     if (dinerBill.getTable() != null) {
       dinerBillVo.setTabName(dinerBill.getTable().getTabName());
     }
     dinerBillVo.setRealCost(dinerBill.getRealCost());
     dinerBillVo.setBillType(dinerBill.getBillType());
     dinerBillVo.setBillFrom(from);
     List<DinerBillPayment> dinerBillPayments = dinerBill.getDinerBillPayments();
     StringBuilder sb = new StringBuilder();
     for (DinerBillPayment dinerBillPayment : dinerBillPayments) {
       String paymentName = dinerBillPayment.getPaymentType().getPaymentName();
       String money = BigDecimalUtil.format(dinerBillPayment.getMoney()).toString();
       sb.append(paymentName);
       sb.append(":");
       sb.append(money);
       sb.append("&");
     }
     dinerBillVo.setPayments(sb.toString());
     dinerBillVo.setPayTime(dinerBill.getPayTime());
     dinerBillVo.setSaveCost(dinerBill.getSaveCost());
     dinerBillVo.setServiceChargeMoney(dinerBill.getServiceChargeMoney());
     dinerBillVo.setIsDrawBill(dinerBill.getIsDrawBill());
 
     if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) {
       Takeout takeout = dinerBill.getTakeout();
       if (takeout != null) {
         dinerBillVo.setContactName(takeout.getContactName());
         dinerBillVo.setMobile(takeout.getMobile());
         dinerBillVo.setTelephone(takeout.getTelephone());
         dinerBillVo.setSendAddress(takeout.getSendAddress());
         dinerBillVo.setSenderName(takeout.getSenderName());
         dinerBillVo.setDeliverCost(takeout.getDeliverCost());
       }
     }
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     List<DinerBillDishesSet> dinerBillDishesSets = dinerBill.getDinerBillDishesSets();
 
     dinerBillDishes = mergeSameDish(dinerBill.getDinerBillDishes());
 
     dinerBillDishesSets = mergeSameDishesSet(dinerBill.getDinerBillDishesSets());
 
     List dinerBillDisheVos = new ArrayList();
     DinerBillDisheVo vo;
     for (DinerBillDishe dinerBillDishe : dinerBillDishes)
     {
       vo = new DinerBillDisheVo();
       vo.setDishesName(dinerBillDishe.getDishesName());
       vo.setUnitNum(Float.valueOf(dinerBillDishe.getUnitNum()));
       vo.setUnitName(dinerBillDishe.getUnitName());
       if ((DiscountTypeEnum.MEMBER.getCode().equals(dinerBillDishe.getDiscountType())) || 
         (DiscountTypeEnum.SPECIAL.getCode().equals(dinerBillDishe.getDiscountType())))
       {
         vo.setUnitPrice(dinerBillDishe.getRealUnitPrice());
       }
       else
       {
         vo.setUnitPrice(dinerBillDishe.getUnitPrice());
       }
       vo.setDiscountType(dinerBillDishe.getDiscountType());
       vo.setDiscount(dinerBillDishe.getDiscount());
       vo.setRealCost(dinerBillDishe.getRealCost());
       vo.setOriCost(dinerBillDishe.getOriCost());
 
       if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus())) || 
         (DishesStatusEnum.SERVED_CANCEL.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus()))) {
         vo.setRealCost(BigDecimal.ZERO);
         vo.setDishesName(dinerBillDishe.getDishesName() + "(退)");
       }
 
       dinerBillDisheVos.add(vo);
     }
 
     dinerBillVo.setDinerBillDishes(dinerBillDisheVos);
 
     List dinerBillDishesSetVos = new ArrayList();
     for (DinerBillDishesSet dinerBillDishesSet : dinerBillDishesSets)
     {
       vo = new DinerBillDisheVo();
       vo.setDishesName(dinerBillDishesSet.getDsName());
       vo.setUnitNum(Float.valueOf(dinerBillDishesSet.getUnitNum()));
       vo.setUnitName(dinerBillDishesSet.getUnitName());
       if ((DiscountTypeEnum.MEMBER.getCode().equals(dinerBillDishesSet.getDiscountType())) || (DiscountTypeEnum.SPECIAL.getCode().equals(dinerBillDishesSet.getDiscountType()))) {
         vo.setUnitPrice(dinerBillDishesSet.getRealUnitPrice());
       }
       else {
         vo.setUnitPrice(dinerBillDishesSet.getUnitPrice());
       }
       vo.setDiscountType(dinerBillDishesSet.getDiscountType());
       vo.setDiscount(dinerBillDishesSet.getDiscount());
       vo.setRealCost(dinerBillDishesSet.getRealCost());
       vo.setOriCost(dinerBillDishesSet.getOriCost());
       vo.setDsDishesDesc(dinerBillDishesSet.getDsDishesDesc());
 
       if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equalsIgnoreCase(dinerBillDishesSet.getDsStatus())) || 
         (DishesStatusEnum.SERVED_CANCEL.getCode().equalsIgnoreCase(dinerBillDishesSet.getDsStatus()))) {
         vo.setRealCost(BigDecimal.ZERO);
         vo.setDishesName(dinerBillDishesSet.getDsName() + "(退)");
       }
 
       dinerBillDishesSetVos.add(vo);
     }
 
     dinerBillVo.setDinerBillDishesSet(dinerBillDishesSetVos);
 
     int times = printer.getPrintTimes();
     for (int i = 0; i < times; i++)
     {
       AbstractPrinter aprinter = PrinterFactory.getPayPrinter(dinerBillVo);
 
       aprinter.print(printer, i + 1, printParaments);
     }
   }
 
   private static List<DinerBillDishe> mergeSameDish(List<DinerBillDishe> dinerBillDishes)
   {
     List newDishes = new ArrayList();
 
     for (DinerBillDishe dinerBillDishe : dinerBillDishes)
     {
       DinerBillDishe isHas = isTheBillHasSameDish(dinerBillDishe, newDishes);
       if (isHas == null)
       {
         BeanUtils u = new BeanUtils();
         try
         {
           DinerBillDishe newDIsh = (DinerBillDishe)BeanUtils.deepCopy(dinerBillDishe);
           newDishes.add(newDIsh);
         } catch (Exception e) {
           e.printStackTrace();
         }
 
       }
       else
       {
         isHas.setUnitNum(isHas.getUnitNum() + dinerBillDishe.getUnitNum());
         isHas.setRealCost(isHas.getRealCost().add(dinerBillDishe.getRealCost()));
         isHas.setOriCost(isHas.getOriCost().add(dinerBillDishe.getOriCost()));
       }
     }
 
     return newDishes;
   }
 
   private static List<DinerBillDishesSet> mergeSameDishesSet(List<DinerBillDishesSet> dinerBillDishesSetList)
   {
     List newDishesSet = new ArrayList();
 
     for (DinerBillDishesSet dinerBillDishesSet : dinerBillDishesSetList)
     {
       DinerBillDishesSet isHas = isTheBillHasSameDishesSet(dinerBillDishesSet, newDishesSet);
       if (isHas == null)
       {
         BeanUtils u = new BeanUtils();
         try
         {
           DinerBillDishesSet newDIsh = (DinerBillDishesSet)BeanUtils.deepCopy(dinerBillDishesSet);
           newDishesSet.add(newDIsh);
         } catch (Exception e) {
           e.printStackTrace();
         }
 
       }
       else
       {
         isHas.setUnitNum(isHas.getUnitNum() + dinerBillDishesSet.getUnitNum());
         isHas.setRealCost(isHas.getRealCost().add(dinerBillDishesSet.getRealCost()));
         isHas.setOriCost(isHas.getOriCost().add(dinerBillDishesSet.getOriCost()));
       }
     }
 
     return newDishesSet;
   }
 
   private static DinerBillDishe isTheBillHasSameDish(DinerBillDishe dinerBillDishe, List<DinerBillDishe> newDishes)
   {
     for (DinerBillDishe each : newDishes) {
       if ((!each.getDishesId().equals(dinerBillDishe.getDishesId())) || 
         (!each.getDishesStatus().equals(dinerBillDishe.getDishesStatus())) || 
         (!each.getRealUnitPrice().equals(dinerBillDishe.getRealUnitPrice()))) {
         continue;
       }
       if (each.getDiscountType() != null)
       {
         if (each.getDiscountType().equals(dinerBillDishe.getDiscountType()))
         {
           return each;
         }
       }
       else
       {
         return each;
       }
     }
 
     return null;
   }
 
   private static DinerBillDishesSet isTheBillHasSameDishesSet(DinerBillDishesSet dinerBillDishesSet, List<DinerBillDishesSet> newDishesSets)
   {
     for (DinerBillDishesSet each : newDishesSets) {
       if ((!each.getDsId().equals(dinerBillDishesSet.getDsId())) || 
         (!each.getDsStatus().equals(dinerBillDishesSet.getDsStatus())) || 
         (!each.getRealUnitPrice().equals(dinerBillDishesSet.getRealUnitPrice()))) {
         continue;
       }
       if (each.getDiscountType() != null)
       {
         if (each.getDiscountType().equals(dinerBillDishesSet.getDiscountType()))
         {
           return each;
         }
       }
       else
       {
         return each;
       }
     }
 
     return null;
   }
 
   public static void printZiZhu(DinerBillZiZhu dinerBill, MembershipCard membershipCard, String type, Printer printer, Restaurant restaurant)
     throws PrinterException
   {
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(restaurant.getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setOrderTime(dinerBill.getPayTime());
     if (membershipCard != null) {
       dinerBillVo.setCardNo(membershipCard.getCardNo());
       if (membershipCard.getBalance() != null) {
         dinerBillVo.setCardBalance(membershipCard.getBalance().toString());
       }
     }
 
     dinerBillVo.setBillType(type);
     dinerBillVo.setRealCost(dinerBill.getRealCost());
     List list = new ArrayList();
     if (dinerBill.getDinerBillZiZhuDishe() != null)
     {
       for (DinerBillZiZhuDishe e : dinerBill.getDinerBillZiZhuDishe())
       {
         DinerBillDisheVo dish = new DinerBillDisheVo();
         dish.setDishesName(e.getZiZhuYouhuiName());
         dish.setUnitNum(Float.valueOf(e.getUnitNum()));
         dish.setRealCost(e.getZiZhuYouhuiTotalPrice());
         list.add(dish);
       }
       dinerBillVo.setDinerBillDishes(list);
     }
 
     int times = printer.getPrintTimes();
     for (int i = 0; i < times; i++)
     {
       AbstractPrinter aprinter = PrinterFactory.getZiZhuPrinter(dinerBillVo);
       aprinter.print(printer, i + 1, null);
     }
   }
 
   public static void printZiZhu(DinerBill dinerBill, MembershipCard membershipCard, String type, Printer printer, Restaurant restaurant) throws PrinterException {
     DinerBillVo dinerBillVo = setDinerBillVo(dinerBill, membershipCard, type, restaurant);
     int times = printer.getPrintTimes();
     for (int i = 0; i < times; i++)
     {
       AbstractPrinter aprinter = PrinterFactory.getZiZhuPrinter(dinerBillVo);
       aprinter.print(printer, i + 1, null);
     }
   }
 
   public static void printZiZhuForPrintType(DinerBill dinerBill, MembershipCard membershipCard, String type, Printer printer, Restaurant restaurant, String printType, String isReissue) throws PrinterException
   {
     DinerBillVo dinerBillVo = setDinerBillVo(dinerBill, membershipCard, type, restaurant);
     AbstractPrinter aprinter = PrinterFactory.getZiZhuPrinter(dinerBillVo, printType, isReissue);
     aprinter.print(printer, 1, null);
   }
 
   private static DinerBillVo setDinerBillVo(DinerBill dinerBill, MembershipCard membershipCard, String type, Restaurant restaurant) {
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(restaurant.getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
     dinerBillVo.setOrderTime(dinerBill.getPayTime());
     if (membershipCard != null) {
       dinerBillVo.setCardNo(membershipCard.getCardNo());
       if (membershipCard.getBalance() != null) {
         dinerBillVo.setCardBalance(membershipCard.getBalance().toString());
       }
     }
     dinerBillVo.setBillType(type);
     String cardNote = dinerBill.getCardNotes();
     if ((cardNote != null) && (cardNote.indexOf("1s") < 0))
     {
       cardNote = "1s," + cardNote;
     }
     dinerBillVo.setNotes(cardNote);
     BigDecimal totalCost = BigDecimal.ZERO;
     List list = new ArrayList();
     if (dinerBill.getDinerBillDishes() != null)
     {
       for (DinerBillDishe e : dinerBill.getDinerBillDishes())
       {
         if ((!e.getDishesStatus().equals(DishesStatusEnum.UNSERVE.getCode())) && 
           (!e.getDishesStatus().equals(DishesStatusEnum.SERVED.getCode())) && 
           (!e.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode())))
           continue;
         DinerBillDisheVo dish = new DinerBillDisheVo();
         dish.setDishesName(e.getDishesName());
         dish.setUnitNum(Float.valueOf(e.getUnitNum()));
         dish.setRealCost(e.getRealCost());
         totalCost = totalCost.add(dish.getRealCost());
         list.add(dish);
       }
 
       dinerBillVo.setDinerBillDishes(list);
     }
     if (dinerBill.getDinerBillDishesSets() != null)
     {
       for (DinerBillDishesSet e : dinerBill.getDinerBillDishesSets())
       {
         if ((!e.getDsStatus().equals(DishesStatusEnum.UNSERVE.getCode())) && 
           (!e.getDsStatus().equals(DishesStatusEnum.SERVED.getCode())) && 
           (!e.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode())))
           continue;
         DinerBillDisheVo dish = new DinerBillDisheVo();
         dish.setDishesName(e.getDsName());
         dish.setUnitNum(Float.valueOf(e.getUnitNum()));
         dish.setRealCost(e.getRealCost());
         totalCost = totalCost.add(dish.getRealCost());
         list.add(dish);
       }
 
       dinerBillVo.setDinerBillDishes(list);
     }
     dinerBillVo.setRealCost(totalCost);
     return dinerBillVo;
   }
 }

