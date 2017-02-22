 package com.ndlan.canyin.sharelogic.print;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
 import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.qtsy.EmployeShift;
 import com.ndlan.canyin.core.maths.ShiftMaths;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.EmployeShiftVo;
 import com.ndlan.canyin.core.vo.PaymentTypeVO;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailDatasVo;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailTitleVo;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
import org.apache.commons.lang3.StringUtils;
 
 public class ShiftPrint
 {
   public static void print(EmployeShift employeShift, String currentCashBalance, String shiftTime, String lastShiftTime, Map<String, Object> dataMap, List<PaymentTypeVO> paymentTypeVOnew, List<PaymentTypeVO> memberPaymentTypeVOsNew, List<PaymentTypeVO> orderPaymentTypeVOsNew, Printer printer)
     throws PrinterException
   {
     EmployeShiftVo employeShiftVo = new EmployeShiftVo();
 
     employeShiftVo.setCurrentCash(employeShift.getCurrentCash());
 
     employeShiftVo.setCurrentCashBalance(currentCashBalance);
     employeShiftVo.setCurrentHandoffCash(employeShift.getCurrentHandoffCash());
     employeShiftVo.setCurrentHandonCash(employeShift.getCurrentHandonCash());
     employeShiftVo.setCurrentPaymentTypeVOs(paymentTypeVOnew);
     employeShiftVo.setCurrentShiftTime(shiftTime);
     employeShiftVo.setLastBalanceCash(employeShift.getLastBalanceCash());
     employeShiftVo.setLastShiftTime(lastShiftTime);
     employeShiftVo.setMemberPaymentTypeVOs(memberPaymentTypeVOsNew);
     employeShiftVo.setOrderPaymentTypeVOs(orderPaymentTypeVOsNew);
     employeShiftVo.setShiftEmpName(employeShift.getCreateEmployee().getName());
     employeShiftVo.setCurrentMoneySum(new BigDecimal(dataMap.get("currentMoneySum").toString()));
     employeShiftVo.setMemberMoneySum(new BigDecimal(dataMap.get("memberMoneySum").toString()));
     employeShiftVo.setOrderForegiftSum(new BigDecimal(dataMap.get("orderForegiftSum").toString()));
     employeShiftVo.setPeopleNum(Long.valueOf(dataMap.get("peopleNum").toString()));
 
     if (dataMap.get("totalMolingModeCost") != null)
     {
       employeShiftVo.setMolingSum(new BigDecimal(dataMap.get("totalMolingModeCost").toString()));
     }
     if (dataMap.get("totalSaveCost") != null)
     {
       employeShiftVo.setDiscountMoneySum(new BigDecimal(dataMap.get("totalSaveCost").toString()));
     }
     if (dataMap.get("totalServiceChargeMoney") != null)
     {
       employeShiftVo.setServiceMoneySum(new BigDecimal(dataMap.get("totalServiceChargeMoney").toString()));
     }
     if (dataMap.get("totalTuiCaiMoney") != null)
     {
       employeShiftVo.setTuicaiMoneySum(new BigDecimal(dataMap.get("totalTuiCaiMoney").toString()));
     }
     if (dataMap.get("totalZengCaiMoney") != null)
     {
       employeShiftVo.setZengcaiMoneySum(new BigDecimal(dataMap.get("totalZengCaiMoney").toString()));
     }
     if (dataMap.get("totalUnPayBillCost") != null)
     {
       employeShiftVo.setCurrentUnPayMoneySum(new BigDecimal(dataMap.get("totalUnPayBillCost").toString()));
     }
     if (dataMap.get("oddChangeSum") != null)
     {
       employeShiftVo.setOddChangeSum(new BigDecimal(dataMap.get("oddChangeSum").toString()));
     }
     if (dataMap.get("totalForceMoney") != null)
     {
       employeShiftVo.setForceMoneySum(new BigDecimal(dataMap.get("totalForceMoney").toString()));
     }
     AbstractPrinter apriter = PrinterFactory.getShiftPrinter(employeShiftVo);
     apriter.print(printer, 1, null);
   }
 
   public static void printAgain(EmployeShift employeShift, Printer printer, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     ObjectMapper mapper = new ObjectMapper();
 
     EmployeShiftVo employeShiftVo = new EmployeShiftVo();
 
     employeShiftVo.setCurrentCash(employeShift.getCurrentCash());
 
     employeShiftVo.setCurrentCashBalance(employeShift.getCurrentCashBalance() != null ? employeShift.getCurrentCashBalance().toString() : "0");
 
     employeShiftVo.setCurrentHandoffCash(employeShift.getCurrentHandoffCash());
 
     employeShiftVo.setCurrentHandonCash(employeShift.getCurrentHandonCash());
 
     BigDecimal yingyeSum = BigDecimal.ZERO;
 
     List currentPaymentTypeVOs = new ArrayList();
     try {
       List newList = (List)mapper.readValue(employeShift.getShiftPaymentDetail(), List.class);
       for (int i = 0; i < newList.size(); i++) {
         Map map = (Map)newList.get(i);
         String name = map.get("name").toString();
         String paymentType = map.get("paymentType").toString();
         String money = map.get("money").toString();
         BigDecimal b_money = new BigDecimal(money);
         if (b_money.compareTo(BigDecimal.ZERO) != 0) {
           PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
           paymentTypeVO.setPaymentName(name);
           paymentTypeVO.setPaymentType(paymentType);
           paymentTypeVO.setMoney(b_money);
           currentPaymentTypeVOs.add(paymentTypeVO);
           yingyeSum = yingyeSum.add(b_money);
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     employeShiftVo.setCurrentPaymentTypeVOs(currentPaymentTypeVOs);
 
     employeShiftVo.setCurrentShiftTime(sdf.format(employeShift.getCreateTime()));
 
     employeShiftVo.setLastBalanceCash(employeShift.getLastBalanceCash());
 
     employeShiftVo.setLastShiftTime(sdf.format(employeShift.getLastShiftTime()));
 
     BigDecimal cardMoneySum = BigDecimal.ZERO;
 
     List memberPaymentTypeVOs = new ArrayList();
     try {
       List newList = (List)mapper.readValue(employeShift.getCardPaymentDetail(), List.class);
       for (int i = 0; i < newList.size(); i++) {
         Map map = (Map)newList.get(i);
         String name = map.get("name").toString();
         String paymentType = map.get("paymentType").toString();
         String money = map.get("money").toString();
         BigDecimal b_money = new BigDecimal(money);
         if (b_money.compareTo(BigDecimal.ZERO) != 0) {
           PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
           paymentTypeVO.setPaymentName(name);
           paymentTypeVO.setPaymentType(paymentType);
           paymentTypeVO.setMoney(b_money);
           memberPaymentTypeVOs.add(paymentTypeVO);
           cardMoneySum = cardMoneySum.add(b_money);
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     employeShiftVo.setMemberPaymentTypeVOs(memberPaymentTypeVOs);
 
     BigDecimal prepayMoneySum = BigDecimal.ZERO;
 
     List orderPaymentTypeVOs = new ArrayList();
     try {
       List newList = (List)mapper.readValue(employeShift.getPrepayPaymentDetail(), List.class);
       for (int i = 0; i < newList.size(); i++) {
         Map map = (Map)newList.get(i);
         String name = map.get("name").toString();
         String paymentType = map.get("paymentType").toString();
         String money = map.get("money").toString();
         BigDecimal b_money = new BigDecimal(money);
         if (b_money.compareTo(BigDecimal.ZERO) != 0) {
           PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
           paymentTypeVO.setPaymentName(name);
           paymentTypeVO.setPaymentType(paymentType);
           paymentTypeVO.setMoney(b_money);
           orderPaymentTypeVOs.add(paymentTypeVO);
           prepayMoneySum = prepayMoneySum.add(b_money);
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     employeShiftVo.setOrderPaymentTypeVOs(orderPaymentTypeVOs);
 
     employeShiftVo.setShiftEmpName(employeShift.getCreateEmployee().getName());
 
     employeShiftVo.setCurrentMoneySum(ShiftMaths.getRealCost(yingyeSum, employeShift.getOddChangeSum()));
 
     employeShiftVo.setMemberMoneySum(cardMoneySum);
 
     employeShiftVo.setOrderForegiftSum(prepayMoneySum);
     if (employeShift.getPeopleNum() != null) {
       employeShiftVo.setPeopleNum(Long.valueOf(employeShift.getPeopleNum().intValue()));
     }
 
     employeShiftVo.setMolingSum(employeShift.getMolingSum());
 
     employeShiftVo.setDiscountMoneySum(employeShift.getDiscountMoneySum());
 
     employeShiftVo.setServiceMoneySum(employeShift.getServiceMoneySum());
 
     employeShiftVo.setTuicaiMoneySum(employeShift.getTuicaiMoneySum());
 
     employeShiftVo.setZengcaiMoneySum(employeShift.getZengcaiMoneySum());
 
     employeShiftVo.setCurrentUnPayMoneySum(employeShift.getUnPaybillcostSum());
 
     employeShiftVo.setOddChangeSum(employeShift.getOddChangeSum());
 
     employeShiftVo.setForceMoneySum(employeShift.getForcePaySum());
     AbstractPrinter apriter = PrinterFactory.getShiftPrinter(employeShiftVo);
     apriter.print(printer, 1, printParaments);
   }
 
   public static void printSalesVolumeDetail(Printer printer, PrintSalesVolumeDetailTitleVo titleInfo, List<PrintSalesVolumeDetailDatasVo> detailInfos)
     throws PrinterException
   {
     String titile = titleInfo.getTitle();
 
     titile = StringUtils.isNotEmpty(printer.getTitle()) ? printer.getTitle() : StringUtils.isNotEmpty(titile) ? titile : titleInfo.getRestName();
     titleInfo.setTitle(titile);
     AbstractPrinter apriter = PrinterFactory.getSalesVolumeDetailPrinter(titleInfo, detailInfos);
     apriter.print(printer, 1, null);
   }
 
   public static void printZizhu(EmployeShift employeShift, String shiftTime, String lastShiftTime, List<Map<String, Object>> dataList, Printer printer, int times, int cancleCount, BigDecimal cancleSum, int settleCount, BigDecimal settleSum)
     throws PrinterException
   {
     EmployeShiftVo employeShiftVo = new EmployeShiftVo();
     employeShiftVo.setCurrentShiftTime(shiftTime);
     employeShiftVo.setLastShiftTime(lastShiftTime);
 
     List<DinerBillDisheVo> dinerBillDishes = new ArrayList();
 
     if ((dataList != null) && (dataList.size() > 0))
     {
       for (int i = 0; i < dataList.size(); i++) {
         Map e = (Map)dataList.get(i);
         DinerBillDisheVo a1 = new DinerBillDisheVo();
         a1.setDishesName((String)e.get("name"));
         a1.setUnitNum(Float.valueOf(Double.valueOf(((Double)e.get("unitNum")).doubleValue()).floatValue()));
         a1.setRealCost(BigDecimal.valueOf(Double.valueOf(((Double)e.get("realCost")).doubleValue()).doubleValue()));
         dinerBillDishes.add(a1);
       }
 
     }
 
     employeShiftVo.setDinerBillDishes(dinerBillDishes);
     BigDecimal totalMoney = BigDecimal.ZERO;
     float totalUnitNum = 0.0F;
     for (DinerBillDisheVo e : dinerBillDishes)
     {
       totalUnitNum += e.getUnitNum().floatValue();
       totalMoney = totalMoney.add(e.getRealCost());
     }
     employeShiftVo.setCurrentMoneySum(totalMoney);
     employeShiftVo.setCurrentCashBalance(String.valueOf(totalUnitNum));
 
     AbstractPrinter apriter = PrinterFactory.getZizhuShiftPrinter(employeShiftVo, settleCount, settleSum, cancleCount, cancleSum);
     apriter.print(printer, times, null);
   }
 
   public static void printDisheDetail(Printer printer, String from, HashMap<String, Object> printParaments) throws PrinterException
   {
     PrintSalesVolumeDetailTitleVo titleInfo = new PrintSalesVolumeDetailTitleVo();
 
     List detailInfos = new ArrayList();
 
     AbstractPrinter apriter = PrinterFactory.getSalesVolumeDetailPrinter(titleInfo, detailInfos);
     apriter.print(printer, 1, printParaments);
   }
 }

