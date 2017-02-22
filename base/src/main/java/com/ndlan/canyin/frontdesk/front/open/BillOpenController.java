 package com.ndlan.canyin.frontdesk.front.open;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.EmployeShiftService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.EmployeShift;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.OpenPrintTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.PrinterTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.DateUtil;
import com.ndlan.canyin.sharelogic.service.printer.DinerBillPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.PayPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.ShiftPrinterService;

import java.awt.print.PrinterException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/open/bill"})
 @Lazy
 public class BillOpenController extends BaseManageController
 {
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   private PayPrinterService payPrinterService;
 
   @Autowired
   private EmployeShiftService employeShiftService;
 
   @Autowired
   private ShiftPrinterService shiftPrinterService;
 
   @Autowired
   private PrinterService printerService;
 
   @Autowired
   private DinerBillPrinterService dinerBillPrinterService;
 
   @Autowired
   private BillService billService;
 
   @Autowired
   private DinerBillPaymentService dinerBillPaymentService;
 
   @Autowired
   PaymentTypeService paymentTypeService;
 
   @RequestMapping({"ajax/getBill"})
   public String getBill(@RequestParam(value="billNo", defaultValue="") String billNo, Model model, HttpServletRequest request)
   {
     Restaurant restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     DinerBill bill = this.dinerBillService.findByRestaurantAndBillNo(restaurant, billNo);
     if (bill != null)
     {
       model.addAttribute("notes", bill.getCardNotes());
     }
     return "open/getBill";
   }
 
   @RequestMapping(value={"ajax/printBill"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String printBill(@RequestParam(value="billNo", required=false) String billNo, @RequestParam(value="printType", required=false) String printType, @RequestParam(value="isReissue", required=false) String isReissue, Model model, HttpServletRequest request)
   {
     String returnStr = "open/printBill";
     if ((!printType.equalsIgnoreCase(OpenPrintTypeEnum.RESTAURANT.getCode())) && (!printType.equalsIgnoreCase(OpenPrintTypeEnum.CUSTOMER.getCode()))) {
       model.addAttribute("statusCode", StatusCodeEnum.CHECK_ERROR.getCode());
       model.addAttribute("message", "printType参数有误!");
       return returnStr;
     }
 
     Restaurant restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     DinerBill bill = this.dinerBillService.findByRestaurantAndBillNoAndBillStatus(restaurant, billNo, BillStatusEnum.SETTLE.getCode());
 
     if (bill == null) {
       model.addAttribute("statusCode", StatusCodeEnum.CHECK_ERROR.getCode());
       model.addAttribute("message", "不存在账单：" + billNo);
       return returnStr;
     }
     try
     {
       this.payPrinterService.printPayForPrintType(bill, bill.getMembershipCard(), "2", restaurant, printType, isReissue);
       model.addAttribute("statusCode", StatusCodeEnum.SUCCESS.getCode());
       model.addAttribute("message", "打印成功！");
       return returnStr;
     } catch (PrinterException e) {
       model.addAttribute("statusCode", StatusCodeEnum.ERROR.getCode());
       model.addAttribute("message", "打印失败！");
       e.printStackTrace();
     }return returnStr;
   }
 
   @RequestMapping({"ajax/updateBill"})
   public String updateBill(@RequestParam(value="billNo", defaultValue="") String billNo, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model)
   {
     Restaurant restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     DinerBill bill = this.dinerBillService.findByRestaurantAndBillNo(restaurant, billNo);
     if ((bill != null) && (bill.getBillStatus().equals(BillStatusEnum.SETTLE.getCode())))
     {
       silenceLogin(request);
       bill.setBillStatus(BillStatusEnum.CANCEL_BILL.getCode());
       this.dinerBillService.save(bill);
 
       if (TrueFalseEnum.TRUE.getCode().equals(bill.getIsShift())) {
         String cesId = bill.getCesId();
         if (StringUtils.isNotEmpty(cesId)) {
           EmployeShift currentShift = (EmployeShift)this.employeShiftService.getOne(cesId);
           List<DinerBill> dinerBillList = this.dinerBillService.findByRestaurantAndCesId(restaurant, cesId);
           String lastShiftTime = "";
           List lastEmployeShiftList = this.employeShiftService.findByRestIdAndCreateTimeLessThanOrderByCreateTimeDesc(restaurant.getRestId(), currentShift.getCreateTime());
           if ((lastEmployeShiftList != null) && (lastEmployeShiftList.size() > 0))
           {
             lastShiftTime = DateUtil.toStringHH(((EmployeShift)lastEmployeShiftList.get(0)).getCreateTime());
           }
           List dishesStat = this.employeShiftService.getCurrentShiftDishesStatByCesId(restaurant.getRestId(), cesId, BillStatusEnum.SETTLE.getCode());
           BigDecimal totalMoney = BigDecimal.ZERO;
           if ((dishesStat != null) && (dishesStat.size() > 0))
           {
             for (int i = 0; i < dishesStat.size(); i++) {
               Map e = (Map)dishesStat.get(i);
               totalMoney = totalMoney.add(BigDecimal.valueOf(Double.valueOf(((Double)e.get("realCost")).doubleValue()).doubleValue()));
             }
           }
           int settleCount = dinerBillList != null ? dinerBillList.size() : 0;
           BigDecimal settleSum = BigDecimal.ZERO;
           int cancleCount = 0;
           BigDecimal cancleSum = BigDecimal.ZERO;
           for (DinerBill dinerBill : dinerBillList) {
             if (dinerBill.getOriCost() != null) {
               settleSum = settleSum.add(dinerBill.getOriCost());
             }
             if (BillStatusEnum.CANCEL_BILL.getCode().equals(dinerBill.getBillStatus())) {
               cancleCount++;
               cancleSum = cancleSum.add(dinerBill.getOriCost());
             }
           }
           try {
             this.shiftPrinterService.printZizhuShift(currentShift, DateUtil.toStringHH(currentShift.getCreateTime()), lastShiftTime, dishesStat, settleCount, settleSum, cancleCount, cancleSum);
           }
           catch (PrinterException e) {
             e.printStackTrace();
           }
         }
       }
       Subject sub = SecurityUtils.getSubject();
       sub.logout();
       model.addAttribute("notes", "200");
     }
     else
     {
       model.addAttribute("notes", "404");
     }
     return "open/getBill";
   }
 
   public EmployeShift findByRestIdOrderByCreateTimeDesc(String restId)
   {
     List ls = this.employeShiftService.findByRestIdOrderByCreateTimeDesc(restId);
     if ((ls != null) && (ls.size() > 0))
     {
       return (EmployeShift)ls.get(0);
     }
     return null;
   }
 
   @RequestMapping(value={"billPrint/{billNo}"}, produces={"application/json"})
   public String billPrint(@PathVariable("billNo") String billNo, Model model)
     throws JsonProcessingException
   {
     String returnStr = "open/printBill";
     Restaurant restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     DinerBill dinerBill = this.dinerBillService.findByRestaurantAndBillNo(restaurant, billNo);
     List<Printer> printers = this.printerService.findAllPrinters(restaurant.getRestId());
     for (Printer printer : printers) {
       String type = printer.getType();
       try {
         if (PrinterTypeEnum.FRONT_DESC.getCode().equals(type))
           this.dinerBillPrinterService.printPay(dinerBill, "PAY", printer.getSysName(), printer, "2", null);
       }
       catch (PrinterException e) {
         model.addAttribute("statusCode", StatusCodeEnum.ERROR.getCode());
         model.addAttribute("message", "打印失败！");
         e.printStackTrace();
         return returnStr;
       }
     }
     model.addAttribute("statusCode", StatusCodeEnum.SUCCESS.getCode());
     model.addAttribute("message", "打印成功！");
     return returnStr;
   }
 
   private void silenceLogin(HttpServletRequest request)
   {
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken("selforder", "G2", true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
   @RequestMapping(value={"ajax/getBankCardPayResult"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getBankCardPayResult(@RequestParam(value="resultCode", defaultValue="") String resultCode, HttpServletRequest request, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     System.out.println("getBankCardPayResult 支付返回值：" + resultCode);
     System.out.println("进入结账返回方法：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
     silenceLogin(request);
     System.out.println("登陆完成：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
     if (!StringUtils.isEmpty(resultCode))
     {
       String[] result = resultCode.split(",");
       if ("1s".equals(result[0]))
       {
         String billId = result[1];
         DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
         dinerBill.setCardNotes(resultCode);
 
         PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.CARD.getCode());
         DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, dinerBill.getRestaurant().getRestId(), paymentType);
         if (dinerBillPayment == null)
         {
           dinerBillPayment = new DinerBillPayment();
         }
         dinerBillPayment.setDinerBill(dinerBill);
         dinerBillPayment.setBillNo(dinerBill.getBillNo());
         Double myMoney = Double.valueOf(result[3]);
         BigDecimal needMoneyDec = new BigDecimal(Double.toString(myMoney.doubleValue()));
         dinerBillPayment.setMoney(needMoneyDec);
         dinerBillPayment.setPaymentType(paymentType);
         dinerBillPayment.setPayTime(new Date());
         dinerBillPayment.setRestId(dinerBill.getRestaurant().getRestId());
         this.dinerBillPaymentService.save(dinerBillPayment);
 
         dinerBill.setBillType(BillTypeEnum.SELFORDER_BILL.getCode());
         this.dinerBillService.save(dinerBill);
         System.out.println("保存账单完成：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
         try {
           MembershipCard membershipCard = new MembershipCard();
           membershipCard.setBalance(BigDecimal.ZERO);
           membershipCard.setCardNo(result[2]);
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
           this.payPrinterService.printPay(dinerBill, membershipCard, "2", restaurant);
           System.out.println("打印完成：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
         } catch (PrinterException e) {
           e.printStackTrace();
           System.out.println("支付打印异常：" + resultCode);
         }
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setValue(result[1]);
         ajax.setType(dinerBill.getRealCost().toString());
         System.out.println("返回：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
       }
       else
       {
         String billId = result[1];
         DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
         dinerBill.setCardNotes(resultCode);
         this.dinerBillService.save(dinerBill);
       }
 
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax;
   }
 
   public static void main(String[] args)
   {
     System.out.println("进入结账返回方法：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
     try {
       Thread.sleep(1000L);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
   }
 
   @RequestMapping(value={"ajax/getBankCardPayResultSettle"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getBankCardPayResultSettle(@RequestParam(value="resultCode", defaultValue="") String resultCode, HttpServletRequest request, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     System.out.println("getBankCardPayResultSettle 支付返回值：" + resultCode);
     System.out.println("进入结账返回方法：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
     silenceLogin(request);
     System.out.println("登陆完成：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
     if (!StringUtils.isEmpty(resultCode))
     {
       String[] result = resultCode.split(",");
       if ("1s".equals(result[0]))
       {
         LinkedHashMap map = new LinkedHashMap();
 
         String billId = result[1];
         DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
         dinerBill.setCardNotes(resultCode);
 
         PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.CARD.getCode());
         DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, dinerBill.getRestaurant().getRestId(), paymentType);
         String op = OperationTypeEnum.UPDATE.getCode();
         if (dinerBillPayment == null)
         {
           dinerBillPayment = new DinerBillPayment();
           op = OperationTypeEnum.CREATE.getCode();
         }
         dinerBillPayment.setDinerBill(dinerBill);
         dinerBillPayment.setBillNo(dinerBill.getBillNo());
         Double myMoney = Double.valueOf(result[3]);
         BigDecimal needMoneyDec = new BigDecimal(Double.toString(myMoney.doubleValue()));
         dinerBillPayment.setMoney(needMoneyDec);
         dinerBillPayment.setPaymentType(paymentType);
         dinerBillPayment.setPayTime(new Date());
         dinerBillPayment.setRestId(dinerBill.getRestaurant().getRestId());
         this.dinerBillPaymentService.save(dinerBillPayment);
 
         map.put(dinerBillPayment.getDbpId() + "_" + op, dinerBillPayment);
 
         dinerBill.setBillType(BillTypeEnum.ZIZHU_BILL.getCode());
         this.billService.savePay(getCurrentUser(), dinerBill, dinerBill.getMembershipCard(), getCurrentUserRestId(), TrueFalseEnum.FALSE.getCode(), map, null);
 
         doSynchMultiTable(map);
 
         System.out.println("保存账单完成：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
         try {
           MembershipCard membershipCard = new MembershipCard();
           membershipCard.setBalance(BigDecimal.ZERO);
           membershipCard.setCardNo(result[2]);
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
           this.payPrinterService.printPay(dinerBill, membershipCard, "2", restaurant);
           System.out.println("打印完成：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
         } catch (PrinterException e) {
           e.printStackTrace();
           System.out.println("支付打印异常：" + resultCode);
         }
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setValue(result[1]);
         ajax.setType(dinerBill.getRealCost().toString());
         System.out.println("返回：" + DateUtil.toString(DateProvider.DEFAULT.getDate()));
       }
       else
       {
         String billId = result[1];
         DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
         dinerBill.setCardNotes(resultCode);
         this.dinerBillService.save(dinerBill);
       }
 
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax;
   }
 }

