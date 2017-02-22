 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.core.common.DiscountTypeEnum;
 import com.ndlan.canyin.core.common.DishesStatusEnum;
 import com.ndlan.canyin.core.utils.BeanUtils;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.util.ArrayList;
import java.util.List;
 
 public class DinerBillPrint
 {
   public static void printDinerBill(DinerBill dinerBill, String printType, String printName, Printer printer)
     throws PrinterException
   {
     DinerBillVo dinerBillVo = new DinerBillVo();
     dinerBillVo.setRestName(dinerBill.getRestaurant().getRestName());
     dinerBillVo.setBillNo(dinerBill.getBillNo());
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
 
     List<DinerBillPayment> dinerBillPayments = dinerBill.getDinerBillPayments();
     StringBuilder sb = new StringBuilder();
     String money;
     for (DinerBillPayment dinerBillPayment : dinerBillPayments) {
       String paymentName = dinerBillPayment.getPaymentType().getPaymentName();
       money = BigDecimalUtil.format(dinerBillPayment.getMoney()).toString();
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
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
 
     if ("PAY".equals(printType))
     {
       dinerBillDishes = mergeSameDish(dinerBill.getDinerBillDishes());
     }
 
     Object dinerBillDisheVos = new ArrayList();
     for (DinerBillDishe dinerBillDishe : dinerBillDishes) {
       if ((!DishesStatusEnum.SERVED.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus())) && 
         (!DishesStatusEnum.UNSERVE.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus())) && 
         (!DishesStatusEnum.UNXIADAN.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus()))) continue;
       DinerBillDisheVo vo = new DinerBillDisheVo();
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
       ((List)dinerBillDisheVos).add(vo);
     }
 
     dinerBillVo.setDinerBillDishes((List)dinerBillDisheVos);
 
     int times = printer.getPrintTimes();
     for (int i = 0; i < times; i++)
     {
       AbstractPrinter aprinter = PrinterFactory.getXiadanPrinter(dinerBillVo);
       aprinter.print(printer, i + 1, null);
     }
   }
 
   private static List<DinerBillDishe> mergeSameDish(List<DinerBillDishe> dinerBillDishes)
   {
     List newDishes = new ArrayList();
 
     for (DinerBillDishe dinerBillDishe : dinerBillDishes) {
       if ((!DishesStatusEnum.SERVED.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus())) && 
         (!DishesStatusEnum.UNSERVE.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus())) && 
         (!DishesStatusEnum.UNXIADAN.getCode().equalsIgnoreCase(dinerBillDishe.getDishesStatus()))) continue;
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
 }

