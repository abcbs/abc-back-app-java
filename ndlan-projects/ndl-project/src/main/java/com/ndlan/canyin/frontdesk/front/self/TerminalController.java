 package com.ndlan.canyin.frontdesk.front.self;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesPicService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetDishesService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.DishesSetDishesReplaceService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.util.ZiZhuUtil;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesPic;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.CardOperationTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.PicTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.NetUtil;
import com.ndlan.canyin.sharelogic.service.printer.PayPrinterService;

import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/self/terminal"})
 @Lazy
 public class TerminalController extends BaseManageController
 {
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   DishesCategoryService dishesCategoryService;
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   DishesSetDishesReplaceService dishesSetDishesReplaceService;
 
   @Autowired
   DishesSetDishesService dishesSetDishesService;
 
   @Autowired
   private BillService billService;
 
   @Autowired
   private DinerBillDisheService dinerBillDisheService;
 
   @Autowired
   private DinerBillDishesSetService dinerBillDishesSetService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private MembershipCardOperationService cardOperationService;
 
   @Autowired
   private DishesPicService dishesPicService;
 
   @Autowired
   private PayPrinterService payPrinterService;
 
   @Autowired
   RestaurantService restaurantService;
 
   @Autowired
   PaymentTypeService paymentTypeService;
 
   @Autowired
   DinerBillPaymentService dinerBillPaymentService;
 
   private void silenceLogin(HttpServletRequest request)
   {
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken("selforder", "G2", true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 
   @RequestMapping({"index"})
   public String index(Model model, HttpServletRequest request)
   {
     silenceLogin(request);
     return "terminal/index";
   }
 
   @RequestMapping({"list"})
   public String list(@RequestParam(value="billId", defaultValue="") String billId, Model model, HttpServletRequest request)
   {
     silenceLogin(request);
     model.addAttribute("billId", billId);
     model.addAttribute("billType", BillTypeEnum.ZIZHU_BILL.getCode());
     return "terminal/list";
   }
 
   @RequestMapping({"ajax/billContent"})
   public String diancaiBillContent(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="3") String billType, Model model, HttpServletRequest request) {
     if ((billId != null) && (!billId.isEmpty()))
     {
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
 
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
       List dinerBillDishes = this.dinerBillDisheService.findByRestIdAndBillId(getCurrentUserRestId(), dinerBill.getBillId());
       List<DinerBillDishesSet> dinerBillDishesSets = this.dinerBillDishesSetService.findByRestIdAndDinerBill(getCurrentUserRestId(), dinerBill);
       for (DinerBillDishesSet dinerBillDishesSet : dinerBillDishesSets) {
         DinerBillDishe newSet = new DinerBillDishe();
         newSet.setIsSet(TrueFalseEnum.TRUE.getCode());
         newSet.setDishesId(dinerBillDishesSet.getDsId());
         newSet.setBdId(dinerBillDishesSet.getBdsId());
         newSet.setDishesName(dinerBillDishesSet.getDsName());
         newSet.setDiscountType(dinerBillDishesSet.getDiscountType());
         newSet.setDishesStatus(dinerBillDishesSet.getDsStatus());
         newSet.setAvoidfoodNameArray(dinerBillDishesSet.getAvoidfoodNameArray());
         newSet.setTasteNameArray(dinerBillDishesSet.getTasteNameArray());
         newSet.setPungentLevel(dinerBillDishesSet.getPungentLevel());
         newSet.setNotes(dinerBillDishesSet.getNotes());
         newSet.setUnitName(dinerBillDishesSet.getUnitName());
         newSet.setUnitNum(dinerBillDishesSet.getUnitNum());
         newSet.setDiscount(dinerBillDishesSet.getDiscount());
         newSet.setUnitPrice(dinerBillDishesSet.getUnitPrice());
         newSet.setRealUnitPrice(dinerBillDishesSet.getRealUnitPrice());
         newSet.setOriCost(dinerBillDishesSet.getOriCost());
         newSet.setRealCost(dinerBillDishesSet.getRealCost());
         newSet.setDiscount(dinerBillDishesSet.getDiscount());
         newSet.setDiscountType(dinerBillDishesSet.getDiscountType());
 
         dinerBillDishes.add(newSet);
       }
       model.addAttribute("newBill", dinerBill);
       model.addAttribute("dinerBillDishes", dinerBillDishes);
     }
     else
     {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setBillType(billType);
       d.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       d.setTable(null);
       d.setCreateEmployee(getCurrentUser());
       model.addAttribute("newBill", d);
     }
 
     return "terminal/billContent";
   }
 
   @RequestMapping({"ajax/dishesContent"})
   public String diancaiContent(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="99999") int pagzSize, @RequestParam(value="categoryId", defaultValue="") String categoryId, @RequestParam(value="dsCategoryId", defaultValue="") String dsCategoryId, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="1") String billType, Model model, HttpServletRequest request)
   {
     List dishesCategorys = this.dishesCategoryService.findAllDishesCategoryByRestId(getCurrentUserRestId());
     model.addAttribute("dishesCategorys", dishesCategorys);
 
     if (StringUtils.isEmpty(categoryId))
     {
       categoryId = (dishesCategorys != null) && (dishesCategorys.size() > 0) ? ((DishesCategory)dishesCategorys.get(0)).getCategoryId() : null;
     }
     Page<Dishe> dishes = this.disheService.searchDishe(getCurrentUserRestId(), categoryId, dsCategoryId, null, null, null, pageNumber, pagzSize, null);
     for (Dishe dishe : dishes) {
       List<DishesPic> pics = this.dishesPicService.findByDishesId(dishe.getDishesId());
       String picUrl = "";
       if ((pics != null) && (pics.size() > 0))
       {
         for (DishesPic e : pics)
         {
           if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
             continue;
           picUrl = e.getPicUrl();
           break;
         }
       }
 
       dishe.setPicUrl(picUrl);
     }
     model.addAttribute("dishes", dishes);
 
     List dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
     model.addAttribute("dishesSetCategorys", dishesSetCategorys);
 
     model.addAttribute("categoryId", categoryId);
     model.addAttribute("dsCategoryId", dsCategoryId);
     model.addAttribute("serverIp", NetUtil.getLocalIp());
     return "terminal/dishesContent";
   }
 
   @RequestMapping(value={"ajax/getBankCardNo"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getBankCardNo(@RequestParam(value="billId", defaultValue="") String billId, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
 
     ajax.setValue(dinerBill.getBillId());
 
     String cardNotes = dinerBill.getCardNotes();
     if (!StringUtils.isEmpty(cardNotes))
     {
       String[] result = cardNotes.split(",");
       if (!"1s".equals(result[0]))
       {
         dinerBill.setCardNotes(null);
         this.dinerBillService.save(dinerBill);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
       }
 
     }
 
     String resultCode = ZiZhuUtil.getBankCardNo(dinerBill.getBillId(), BigDecimalUtil.format(dinerBill.getOriCost()).toString());
     if (TrueFalseEnum.TRUE.getCode().equals(resultCode))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setType(dinerBill.getRealCost().toString());
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
     }
 
     return ajax;
   }
 
   @RequestMapping({"ajax/cardSelect"})
   public String cardSelect(Model model, HttpServletRequest request) {
     return "terminal/cardSelect";
   }
 
   @RequestMapping({"cardPay"})
   public String cardPay(Model model, HttpServletRequest request, @RequestParam("billId") String billId) {
     model.addAttribute("billId", billId);
     return "terminal/cardPay";
   }
   @RequestMapping(value={"ajax/cardPayUpdate"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone cardPayUpdate(Model model, HttpServletRequest request, @RequestParam("billId") String billId, @RequestParam("cardNo") String cardNo) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     if (StringUtils.isNotEmpty(cardNo)) {
       List cards = this.membershipCardService.findByCardNo(cardNo, getCurrentUserRestId());
       if ((cards != null) && (cards.size() > 0)) {
         MembershipCard membershipCard = (MembershipCard)cards.get(0);
         DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
         if ((membershipCard != null) && (dinerBill != null)) {
           if (membershipCard.getBalance().compareTo(dinerBill.getOriCost()) == -1) {
             ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
             ajax.setMessage("余额不足！");
             return ajax;
           }
           LinkedHashMap map = new LinkedHashMap();
 
           DinerBillPayment dinerBillPayment = new DinerBillPayment();
           PaymentType pType = this.paymentTypeService.findeByRestIdAndPaymentType(getCurrentUserRestId(), PaymentTypeEnum.MEMBER_CARD.getCode());
           dinerBillPayment.setDinerBill(dinerBill);
           dinerBillPayment.setPaymentType(pType);
           dinerBillPayment.setBillNo(dinerBill.getBillNo());
           dinerBillPayment.setRestId(getCurrentUserRestId());
           dinerBillPayment.setPayTime(new Date());
           dinerBillPayment.setMoney(dinerBill.getOriCost());
           this.dinerBillPaymentService.save(dinerBillPayment);
 
           map.put(dinerBillPayment.getDbpId() + "_" + OperationTypeEnum.CREATE.getCode(), dinerBillPayment);
 
           this.billService.calculator(getCurrentUserRestId(), dinerBill);
           dinerBill.setPayTime(DateProvider.DEFAULT.getDate());
           dinerBill.setBillStatus(BillStatusEnum.SETTLE.getCode());
 
           dinerBill.setMembershipCard(membershipCard);
           this.billService.savePay(getCurrentUser(), dinerBill, membershipCard, getCurrentUserRestId(), TrueFalseEnum.FALSE.getCode(), map, null);
           ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
           ajax.setMessage("支付成功！");
 
           doSynchMultiTable(map);
           try
           {
             membershipCard.setBalance(membershipCard.getBalance());
             membershipCard.setCardNo(cardNo);
             Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
             this.payPrinterService.printPay(dinerBill, membershipCard, "1", restaurant);
           } catch (PrinterException e) {
             e.printStackTrace();
           }
           return ajax;
         }
       }
     }
 
     return ajax;
   }
 
   @RequestMapping({"paySuccess"})
   public String paySuccess(Model model, HttpServletRequest request, @RequestParam("billId") String billId) {
     if (StringUtils.isNotEmpty(billId)) {
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       model.addAttribute("dinerBill", dinerBill);
     }
     return "terminal/paySuccess";
   }
   @RequestMapping({"payFailure"})
   public String payFailure(Model model, HttpServletRequest request) {
     return "terminal/payFailure";
   }
 
   @RequestMapping({"ajax/cardInfo"})
   public String cardInfo(Model model, HttpServletRequest request, @RequestParam("cardNo") String cardNo) {
     if (StringUtils.isNotEmpty(cardNo)) {
       List cards = this.membershipCardService.findByCardNo(cardNo, getCurrentUserRestId());
       if ((cards != null) && (cards.size() > 0)) {
         MembershipCard membershipCard = (MembershipCard)cards.get(0);
         BigDecimal consumeCashSum = this.cardOperationService.getConsumeCashSumByMcId(getCurrentUserRestId(), membershipCard.getMcId(), CardOperationTypeEnum.CONSUME.getCode());
         consumeCashSum = consumeCashSum == null ? BigDecimal.ZERO : consumeCashSum;
         model.addAttribute("membershipCard", membershipCard);
         model.addAttribute("consumeCashSum", consumeCashSum);
       }
     }
     return "terminal/cardInfo";
   }
 
   @RequestMapping(value={"dishDeleteAll/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishDelete(@PathVariable("billId") String billId, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     try {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveDishDeleteAll(getCurrentUserRestId(), billId, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("删除成功");
     ajax.setRel(billId);
     return ajax;
   }
 
   @RequestMapping(value={"ajax/getMemberCardInfo"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone validateCardNoAndPassword(@RequestParam(value="cardNo", required=true) String cardNo, @RequestParam(value="cardPassword", required=true) String cardPassword, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     MembershipCard membershipCard = this.membershipCardService.findByCardNoAndCardPassword(cardNo, cardPassword, getCurrentUserRestId());
     if (membershipCard != null) {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage(membershipCard.getBalance() == null ? "0" : membershipCard.getBalance().toString());
       ajax.setRel(membershipCard.getMemberIntegral() == null ? "0" : membershipCard.getMemberIntegral().toString());
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax;
   }
 }

