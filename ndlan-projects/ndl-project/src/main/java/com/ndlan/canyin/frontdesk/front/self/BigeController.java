 package com.ndlan.canyin.frontdesk.front.self;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillZiZhuDisheService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillZiZhuService;
import com.ndlan.canyin.frontdesk.service.sygl.BuffetSettingService;
import com.ndlan.canyin.frontdesk.util.ZiZhuUtil;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
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
 @RequestMapping({"/self/bige"})
 @Lazy
 public class BigeController extends BaseManageController
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
 
   @Autowired
   DinerBillService dinerBillService;
 
   @RequestMapping({"index"})
   public String buffetIndex(Model model, HttpServletRequest request)
   {
     return "bige/index";
   }
 
   @RequestMapping({"zizhuOrder"})
   public String zizhuOrder(@RequestParam(value="cbsId", defaultValue="") String cbsId, @RequestParam(value="billId", defaultValue="") String billId, Model model, HttpServletRequest request)
   {
     silenceLogin(request);
 
     int dayOfWeek = DateUtil.getWeekDay(DateProvider.DEFAULT.getDate());
     model.addAttribute("dayOfWeek", Integer.valueOf(dayOfWeek));
     List buttets = this.buffetSettingService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     model.addAttribute("buttets", buttets);
     return "bige/zizhuOrder";
   }
 
   @RequestMapping({"zizhuBankCardPay"})
   public String zizhuBankCardPay(@RequestParam(value="realCost", defaultValue="") String realCost, @RequestParam(value="dinerBillZiZhuDisheStr", defaultValue="") String dinerBillZiZhuDisheStr, Model model, HttpServletRequest request)
   {
     if (StringUtils.isEmpty(realCost))
     {
       return "redirect:/self/bige/zizhuOrder";
     }
     DinerBillZiZhu newZizhu = null;
     try {
       newZizhu = this.dinerBillZiZhuService.saveZiZhu(getCurrentUserRestId(), null, null, dinerBillZiZhuDisheStr, realCost);
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
   public DwzAjaxDone getBankCardNo(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="realCost", defaultValue="") String realCost, @RequestParam(value="dinerBillZiZhuDisheStr", defaultValue="") String dinerBillZiZhuDisheStr, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill newZizhu = null;
     if (!StringUtils.isEmpty(billId))
     {
       newZizhu = (DinerBill)this.dinerBillService.getOne(billId);
     }
     else {
       try
       {
         newZizhu = this.dinerBillZiZhuService.saveZiZhuDinerBill(getCurrentUserRestId(), null, null, dinerBillZiZhuDisheStr, realCost);
       } catch (IOException e) {
         e.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
 
     }
 
     String cardNotes = newZizhu.getCardNotes();
     if (!StringUtils.isEmpty(cardNotes))
     {
       String[] result = cardNotes.split(",");
       if (!"1s".equals(result[0]))
       {
         newZizhu.setCardNotes(null);
         this.dinerBillService.save(newZizhu);
       }
     }
 
     ajax.setValue(newZizhu.getBillId());
 
     String resultCode = ZiZhuUtil.getBankCardNo(newZizhu.getBillId(), BigDecimalUtil.format(newZizhu.getRealCost()).toString());
     if (TrueFalseEnum.TRUE.getCode().equals(resultCode))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setType(newZizhu.getRealCost().toString());
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
     }
     return ajax;
   }
 
   @RequestMapping(value={"ajax/getMemberCardNo"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getNoWaimaiAndResettle(@RequestParam(value="realCost", defaultValue="") String realCost, @RequestParam(value="dinerBillZiZhuDisheStr", defaultValue="") String dinerBillZiZhuDisheStr, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBillZiZhu newZizhu = null;
     try {
       newZizhu = this.dinerBillZiZhuService.saveZiZhu(getCurrentUserRestId(), null, null, dinerBillZiZhuDisheStr, realCost);
     } catch (IOException e) {
       e.printStackTrace();
     } catch (ClassNotFoundException e) {
       e.printStackTrace();
     }
 
     String resultCode = ZiZhuUtil.getMemberCardNo();
 
     if (!StringUtils.isEmpty(resultCode))
     {
       String[] result = resultCode.split(",");
       newZizhu.setPayTime(DateProvider.DEFAULT.getDate());
       newZizhu.setBillStatus(BillStatusEnum.SETTLE.getCode());
       newZizhu.setPrintTimes(Integer.valueOf(1));
       newZizhu.setPayDesc(resultCode);
       this.dinerBillZiZhuService.save(newZizhu);
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       MembershipCard membershipCard = this.membershipCardService.findByCardNoAndCardPassword(result[1], result[2], getCurrentUserRestId());
       if (membershipCard != null)
       {
         LinkedHashMap map = new LinkedHashMap();
         this.membershipCardService.memberCardPay(membershipCard, newZizhu, map);
 
         doSynchMultiTable(map);
         try
         {
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
           this.payPrinterService.printPay(newZizhu, membershipCard, "1", restaurant);
         } catch (PrinterException e) {
           e.printStackTrace();
         }
       }
       else
       {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       }
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax; } 
   @RequestMapping(value={"ajax/goAdmin"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone goAdmin(RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
     String resultCode = ZiZhuUtil.goAdmin();
     if (!StringUtils.isEmpty(resultCode))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
     }
     return ajax;
   }
 
   @RequestMapping({"exit"})
   public String exit(Model model, HttpServletRequest request)
   {
     return "redirect:/self/bige/index";
   }
 
   @RequestMapping({"paySuccess"})
   public String paySuccess(@RequestParam(value="realCost", defaultValue="") String realCost, Model model, HttpServletRequest request)
   {
     model.addAttribute("realCost", realCost);
     return "bige/paySuccess";
   }
 
   @RequestMapping({"payError"})
   public String payError(Model model, HttpServletRequest request)
   {
     return "bige/payError";
   }
 
   private void silenceLogin(HttpServletRequest request) {
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken("selforder", "G2", true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 }

