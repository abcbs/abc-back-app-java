 package com.ndlan.canyin.frontdesk.front.self;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillZiZhuDisheService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillZiZhuService;
import com.ndlan.canyin.frontdesk.service.sygl.BuffetSettingService;
import com.ndlan.canyin.frontdesk.util.ZiZhuUtil;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
import com.ndlan.canyin.base.entity.sygl.BuffetSetting;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.DateUtil;
import com.ndlan.canyin.sharelogic.service.printer.PayPrinterService;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/self/zizhu"})
 @Lazy
 public class ZiZhuController extends BaseManageController
 {
 
   @Autowired
   BuffetSettingService buffetSettingService;
 
   @Autowired
   DinerBillZiZhuService dinerBillZiZhuService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   PayPrinterService payPrinterService;
 
   @Autowired
   DinerBillZiZhuDisheService dinerBillZiZhuDisheService;
 
   @RequestMapping({"index"})
   public String buffetIndex(Model model, HttpServletRequest request)
   {
     silenceLogin(request);
     List buttets = this.buffetSettingService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     model.addAttribute("buttets", buttets);
     return "zizhu/index";
   }
 
   @RequestMapping({"zizhuOrder"})
   public String zizhuOrder(@RequestParam(value="cbsId", defaultValue="") String cbsId, @RequestParam(value="billId", defaultValue="") String billId, Model model, HttpServletRequest request)
   {
     DinerBillZiZhu newZizhu = new DinerBillZiZhu();
     if (!StringUtils.isEmpty(billId))
     {
       newZizhu = this.dinerBillZiZhuService.resetZiZhu(billId);
       cbsId = newZizhu.getCbsId();
     }
     model.addAttribute("billId", billId);
     model.addAttribute("bill", newZizhu);
 
     BuffetSetting buffetSetting = (BuffetSetting)this.buffetSettingService.getOne(cbsId);
     model.addAttribute("buffetSetting", buffetSetting);
 
     int dayOfWeek = DateUtil.getWeekDay(DateProvider.DEFAULT.getDate());
 
     if (dayOfWeek == 1)
     {
       model.addAttribute("oriPrice", buffetSetting.getMondayPrice());
       model.addAttribute("youhuis", buffetSetting.getMondayYouhuiList());
     }
     if (dayOfWeek == 2)
     {
       model.addAttribute("oriPrice", buffetSetting.getTuesdayPrice());
       model.addAttribute("youhuis", buffetSetting.getTuesdayYouhuiList());
     }
     if (dayOfWeek == 3)
     {
       model.addAttribute("oriPrice", buffetSetting.getWednesdayPrice());
       model.addAttribute("youhuis", buffetSetting.getWednesdayYouhuiList());
     }
     if (dayOfWeek == 4)
     {
       model.addAttribute("oriPrice", buffetSetting.getThursdayPrice());
       model.addAttribute("youhuis", buffetSetting.getThursdayYouhuiList());
     }
     if (dayOfWeek == 5)
     {
       model.addAttribute("oriPrice", buffetSetting.getFridayPrice());
       model.addAttribute("youhuis", buffetSetting.getFridayYouhuiList());
     }
     if (dayOfWeek == 6)
     {
       model.addAttribute("oriPrice", buffetSetting.getSaturdayPrice());
       model.addAttribute("youhuis", buffetSetting.getSaturdayYouhuiList());
     }
     if ((dayOfWeek == 0) || (dayOfWeek == 7))
     {
       model.addAttribute("oriPrice", buffetSetting.getSundayPrice());
       model.addAttribute("youhuis", buffetSetting.getSundayYouhuiList());
     }
     return "zizhu/zizhuOrder";
   }
 
   @RequestMapping({"zizhuMemberPay"})
   public String zizhuMemberPay(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="cbsId", defaultValue="") String cbsId, @RequestParam(value="realCost", defaultValue="") String realCost, @RequestParam(value="dinerBillZiZhuDisheStr", defaultValue="") String dinerBillZiZhuDisheStr, Model model, HttpServletRequest request)
   {
     if (StringUtils.isEmpty(realCost))
     {
       return "redirect:/self/zizhu/zizhuOrder?billId=" + billId + "&cbsId=" + cbsId;
     }
     DinerBillZiZhu newZizhu = null;
     try {
       newZizhu = this.dinerBillZiZhuService.saveZiZhu(getCurrentUserRestId(), billId, cbsId, dinerBillZiZhuDisheStr, realCost);
     } catch (IOException e) {
       e.printStackTrace();
     } catch (ClassNotFoundException e) {
       e.printStackTrace();
     }
     model.addAttribute("bill", newZizhu);
     return "zizhu/zizhuMemberPay"; } 
   @RequestMapping(value={"ajax/getMemberCardNo"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getNoWaimaiAndResettle(RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
     String no = ZiZhuUtil.getMemberCardNo();
     if (!StringUtils.isEmpty(no))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setValue(no);
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax;
   }
 
   @RequestMapping({"zizhuMemberPay/submit"})
   public String zizhuMemberPaySubmit(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="cardNo", required=true) String cardNo, @RequestParam(value="cardPassword", required=true) String cardPassword, Model model, HttpServletRequest request)
   {
     if (StringUtils.isEmpty(billId))
     {
       return "redirect:/self/zizhu/index";
     }
     DinerBillZiZhu newZizhu = (DinerBillZiZhu)this.dinerBillZiZhuService.getOne(billId);
 
     MembershipCard membershipCard = this.membershipCardService.findByCardNoAndCardPassword(cardNo, cardPassword, getCurrentUserRestId());
     if (membershipCard != null)
     {
       LinkedHashMap map = new LinkedHashMap();
       this.membershipCardService.memberCardPay(membershipCard, newZizhu, map);
 
       doSynchMultiTable(map);
 
       model.addAttribute("bill", newZizhu);
       try {
         Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
         this.payPrinterService.printPay(newZizhu, membershipCard, "1", restaurant);
       } catch (PrinterException e) {
         e.printStackTrace();
       }
       return "zizhu/paySuccess";
     }
 
     model.addAttribute("bill", newZizhu);
     return "zizhu/payError";
   }
 
   @RequestMapping({"zizhuBankCardPay"})
   public String zizhuBankCardPay(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="cbsId", defaultValue="") String cbsId, @RequestParam(value="realCost", defaultValue="") String realCost, @RequestParam(value="dinerBillZiZhuDisheStr", defaultValue="") String dinerBillZiZhuDisheStr, Model model, HttpServletRequest request)
   {
     if (StringUtils.isEmpty(realCost))
     {
       return "redirect:/self/zizhu/zizhuOrder?billId=" + billId + "&cbsId=" + cbsId;
     }
     DinerBillZiZhu newZizhu = null;
     try {
       newZizhu = this.dinerBillZiZhuService.saveZiZhu(getCurrentUserRestId(), billId, cbsId, dinerBillZiZhuDisheStr, realCost);
     } catch (IOException e) {
       e.printStackTrace();
     } catch (ClassNotFoundException e) {
       e.printStackTrace();
     }
     model.addAttribute("bill", newZizhu);
     return "zizhu/zizhuBankCardPay";
   }
   @RequestMapping(value={"ajax/getBankCardNo"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getBankCardNo(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="dinerBillZiZhuDisheStr", defaultValue="") String dinerBillZiZhuDisheStr, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBillZiZhu newZizhu = (DinerBillZiZhu)this.dinerBillZiZhuService.getOne(billId);
 
     String no = ZiZhuUtil.getBankCardNo(billId, newZizhu.getRealCost().toString());
 
     if (!StringUtils.isEmpty(no))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setValue(no);
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax;
   }
 
   @RequestMapping({"zizhuBankCardPay/submit"})
   public String zizhuBankCardPaySubmit(@RequestParam(value="resultCode", defaultValue="") String resultCode, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="bankCardNo", required=true) String bankCardNo, @RequestParam(value="bankCardPassword", required=true) String bankCardPassword, Model model, HttpServletRequest request)
   {
     if (StringUtils.isEmpty(billId))
     {
       return "redirect:/self/zizhu/index";
     }
 
     if (!StringUtils.isEmpty(resultCode)) {
       String[] result = resultCode.split(",");
       DinerBillZiZhu newZizhu = (DinerBillZiZhu)this.dinerBillZiZhuService.getOne(result[0]);
       newZizhu.setPayTime(DateProvider.DEFAULT.getDate());
       newZizhu.setBillStatus(BillStatusEnum.SETTLE.getCode());
       newZizhu.setPrintTimes(Integer.valueOf(1));
       newZizhu.setPayDesc(resultCode);
       this.dinerBillZiZhuService.save(newZizhu);
 
       model.addAttribute("bill", newZizhu);
       try {
         MembershipCard membershipCard = new MembershipCard();
         membershipCard.setBalance(BigDecimal.ZERO);
         membershipCard.setCardNo(result[1]);
         Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
         this.payPrinterService.printPay(newZizhu, membershipCard, "2", restaurant);
       } catch (PrinterException e) {
         e.printStackTrace();
       }
 
       return "zizhu/paySuccess";
     }
 
     return "zizhu/payError";
   }
 
   @RequestMapping({"cancelBill"})
   public String cancelBill(@RequestParam(value="billId", defaultValue="") String billId, Model model, HttpServletRequest request)
   {
     if (StringUtils.isEmpty(billId))
     {
       return "redirect:/self/zizhu/index";
     }
     DinerBillZiZhu newZizhu = (DinerBillZiZhu)this.dinerBillZiZhuService.getOne(billId);
     newZizhu.setBillStatus(BillStatusEnum.CANCEL_BILL.getCode());
     this.dinerBillZiZhuService.save(newZizhu);
     return "redirect:/self/zizhu/index";
   }
 
   @RequestMapping({"exit"})
   public String exit(Model model, HttpServletRequest request)
   {
     return "redirect:/self/zizhu/index";
   }
 
   private void silenceLogin(HttpServletRequest request)
   {
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken("selforder", "G2", true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 }

