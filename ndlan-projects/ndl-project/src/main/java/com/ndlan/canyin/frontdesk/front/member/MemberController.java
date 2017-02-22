 package com.ndlan.canyin.frontdesk.front.member;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterService;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterTaskService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardClassService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.meta.BaseCodeItemService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.ToPinYin;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.core.common.CardOperationTypeEnum;
import com.ndlan.canyin.core.common.MemberCardStatusEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.SmtCodeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.vo.PrintMemberCardInfoParamentsVo;
import com.ndlan.canyin.core.web.Servlets;
import com.ndlan.canyin.sharelogic.service.printer.RechargePrinterService;
import com.ndlan.canyin.sharelogic.util.MessageDataUtil;

import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/member"})
 @Lazy
 public class MemberController extends BaseManageController
 {
 
   @Autowired
   private RestMemberInfoService restMemberInfoService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private MembershipCardClassService membershipCardClassService;
 
   @Autowired
   private MembershipCardOperationService membershipCardOperationService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   private BaseCodeItemService baseCodeItemService;
 
   @Autowired
   EmployeeService employeeService;
 
   @Autowired
   PrinterService printerService;
 
   @Autowired
   RechargePrinterService rechargePrinterService;
 
   @Autowired
   PrinterTaskService printerTaskService;
 
   @RequestMapping({""})
   public String list(Model model, HttpServletRequest request)
   {
     return "member/list";
   }
 
   @RequestMapping({"ajax/member/list"})
   public String memberList(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pageSize", defaultValue="12") int pageSize, @RequestParam(value="kewWords", required=false) String kewWords, @RequestParam(value="cardStatus", required=false) String cardStatus, Model model, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
 
     List membershipCardClasses = this.membershipCardClassService.findByRestId(getCurrentUserRestId());
 
     Page restMemberInfos = this.restMemberInfoService.searchRestMember(searchParams, getCurrentUserRestId(), cardStatus, pageNumber, pageSize, kewWords, new String[] { "createTime" });
 
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
 
     model.addAttribute("membershipCardClasses", membershipCardClasses);
     model.addAttribute("restMemberInfos", restMemberInfos);
     model.addAttribute("keywords", cardStatus);
 
     return "member/listMemberContent";
   }
 
   @RequestMapping(value={"pop/jianka/createForm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String createForm(@RequestParam(value="mbId", defaultValue="") String mbId, Model model, HttpServletRequest request)
   {
     RestMemberInfo obj = new RestMemberInfo();
     if ((mbId != null) && (!mbId.isEmpty()))
     {
       obj = (RestMemberInfo)this.restMemberInfoService.getOne(mbId);
       model.addAttribute("id", obj.getMbId());
     }
     model.addAttribute("restMemberInfo", obj);
     model.addAttribute("membershipCard", new MembershipCard());
     model.addAttribute("membershipCardClasses", this.membershipCardClassService.findByRestId(getCurrentUserRestId()));
 
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     List opaymentTypes = new ArrayList();
     for (PaymentType p : paymentTypes)
     {
       if ((!p.getPaymentType().equals(PaymentTypeEnum.CARD.getCode())) && 
         (!p.getPaymentType().equals(PaymentTypeEnum.CASH.getCode())))
         continue;
       opaymentTypes.add(p);
     }
 
     model.addAttribute("paymentTypes", opaymentTypes);
     model.addAttribute("works", this.baseCodeItemService.findItemsByBcCode("MemberWork"));
     model.addAttribute("edus", this.baseCodeItemService.findItemsByBcCode("MemberEdu"));
     model.addAttribute("action", "create");
 
     return "member/JianKaForm";
   }
 
   @RequestMapping(value={"pop/jianka/createFormContent"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String createFormContent(Model model, @RequestParam(value="page", defaultValue="1") int pageNumber, HttpServletRequest request,@RequestParam(value="pageSize", defaultValue="8") int pageSize) {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     Page membershipCards = this.membershipCardService.searchPageNoMemberCard(searchParams, getCurrentUserRestId(), null, pageNumber, pageSize, null, new String[] { "createTime" });
     model.addAttribute("membershipCards", membershipCards);
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
     return "member/JianKaFormContent";
   }
   /**
    * 
    * @param restMemberInfo
    * @param popMcId
    * @param jiankaType
    * @param new_cardNo    卡号
    * @param new_membershipCardClasseType  卡类型
    * @param new_cashPledge
    * @param new_cardPassword
    * @param new_rechargeCash  充值金额
    * @param cptId           付款方式类型
    * @param new_paidinCash
    * @param new_memberIntegral
    * @param new_drawBill
    * @param new_print
    * @param salesmanId
    * @param redirectAttributes
    * @return
    * @throws JsonProcessingException
    */
   @RequestMapping(value={"jianka/create"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone jiankaSave(@Valid RestMemberInfo restMemberInfo, @RequestParam(value="popMcId", defaultValue="") String popMcId, @RequestParam(value="jiankaType", defaultValue="0") String jiankaType, @RequestParam(value="new_cardNo", defaultValue="") String new_cardNo, @RequestParam("new_membershipCardClasseType") String new_membershipCardClasseType, @RequestParam(value="new_cashPledge", defaultValue="0") String new_cashPledge, @RequestParam("new_cardPassword") String new_cardPassword, @RequestParam(value="new_rechargeCash", defaultValue="0") String new_rechargeCash, @RequestParam("new_paymentType") String cptId, @RequestParam(value="new_paidinCash", defaultValue="0") String new_paidinCash, @RequestParam(value="new_memberIntegral", defaultValue="0") String new_memberIntegral, @RequestParam(value="new_drawBill", defaultValue="0") String new_drawBill, @RequestParam(value="new_print", defaultValue="0") String new_print, @RequestParam(value="salesmanId", defaultValue="") String salesmanId, RedirectAttributes redirectAttributes)
     throws JsonProcessingException
   {
     if ((new_cashPledge == null) || (new_cashPledge.isEmpty()))
     {
       new_cashPledge = "0";
     }
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     if (validateMobile(restMemberInfo.getMobile(), restMemberInfo.getMbId()))
     {
       MembershipCard membershipCard = new MembershipCard();
       if (TrueFalseEnum.FALSE.getCode().equals(jiankaType))
       {
         membershipCard.setCardNo(restMemberInfo.getMobile());
         membershipCard.setCashPledge(new BigDecimal(new_cashPledge));
         membershipCard.setCardPassword(new_cardPassword);
         membershipCard.setRechargeCash(new BigDecimal(new_rechargeCash));
         membershipCard.setPaidinCash(new BigDecimal(new_paidinCash));
         if (StringUtils.isNotEmpty(new_memberIntegral)) {
           membershipCard.setMemberIntegral(BigDecimalUtil.formatRoundDown(new BigDecimal(new_memberIntegral)));
         }
         membershipCard.setRestMemberInfo(restMemberInfo);
         MembershipCardClass membershipCardClass = (MembershipCardClass)this.membershipCardClassService.getOne(new_membershipCardClasseType);
         membershipCard.setMembershipCardClass(membershipCardClass);
         membershipCard.setCardIssueTime(DateProvider.DEFAULT.getDate());
       }
       else {
         membershipCard.setCashPledge(new BigDecimal(new_cashPledge));
         membershipCard.setCardPassword(new_cardPassword);
         membershipCard.setRechargeCash(new BigDecimal(new_rechargeCash));
         membershipCard.setPaidinCash(new BigDecimal(new_paidinCash));
         if (StringUtils.isNotEmpty(new_memberIntegral)) {
           membershipCard.setMemberIntegral(BigDecimalUtil.formatRoundDown(new BigDecimal(new_memberIntegral)));
         }
         membershipCard.setCardIssueTime(DateProvider.DEFAULT.getDate());
       }
       if (StringUtils.isEmpty(salesmanId)) {
         restMemberInfo.setSalesman(null);
       } else {
         Employee employee = (Employee)this.employeeService.loadOne(salesmanId);
         restMemberInfo.setSalesman(employee);
       }
       restMemberInfo.setBirthday(DateUtil.toSimpleDate(restMemberInfo.getBirthdayStr()));
       LinkedHashMap map = new LinkedHashMap();
       ArrayList cloudMethodParams = new ArrayList();
       MembershipCard savedCard = this.restMemberInfoService.jiankaCreate(restMemberInfo, jiankaType, popMcId, membershipCard, cptId, new_drawBill, map);
       //返回新建会员的id 和卡号  为下一步充值做操作
       if(savedCard.getMcId()!="" && savedCard.getMcId()!=null){
    	   ajax.setForwardUrl(savedCard.getMcId());   //id
    	   ajax.setValue(savedCard.getCardNo());   //卡号
       }
       doSynchMultiTable(map);
 
       MessageDataUtil messageDataUtil = new MessageDataUtil();
       messageDataUtil.newMembersMessageData(cloudMethodParams, savedCard);
 
       doCallCloudMethod(SmtCodeEnum.NEWMEMBER, cloudMethodParams);
 
       ArrayList cloudMethodParams_recharge = new ArrayList();
       BigDecimal memberIntegral = savedCard.getMemberIntegral() == null ? BigDecimal.ZERO : savedCard.getMemberIntegral();
       BigDecimal rechargeCash = membershipCard.getRechargeCash() == null ? BigDecimal.ZERO : membershipCard.getRechargeCash();
       BigDecimal balance = savedCard.getBalance() == null ? BigDecimal.ZERO : savedCard.getBalance();
       messageDataUtil.rechargeMessageData(cloudMethodParams_recharge, savedCard.getRestMemberInfo().getMbId(), savedCard.getMcId(), rechargeCash, balance, memberIntegral, memberIntegral);
 
       doCallCloudMethod(SmtCodeEnum.RECHARGE, cloudMethodParams_recharge);
 
       if (TrueFalseEnum.TRUE.getCode().equals(new_print))
       {
         try
         {
           String restName = ((Restaurant)this.restaurantService.getOne(getCurrentUserRestId())).getRestName();
           BigDecimal b_new_memberIntegral = BigDecimal.ZERO;
           if (StringUtils.isNotEmpty(new_memberIntegral)) {
             b_new_memberIntegral = new BigDecimal(new_memberIntegral);
           }
           HashMap printParaments = new HashMap();
           printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId());
           this.rechargePrinterService.printRecharge(getCurrentUserRestId(), restName, membershipCard, savedCard, new_drawBill, b_new_memberIntegral, savedCard.getUpdateEmployee().getName(), printParaments);
         } catch (Exception e) {
           e.printStackTrace();
           ajax.setMessage("会员卡充值成功,打印失败！");
           return ajax;
         }
       }
       ajax.setStatusCode("200");
       ajax.setMessage("快速建卡成功");
     }
     else
     {
       ajax.setStatusCode("100");
       ajax.setMessage("电话号码重复");
     }
     ajax.setRel("");
     return ajax;
   }
   @RequestMapping(value={"ajax/getMemberByTel/{tel}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getMemberByTel(@PathVariable("tel") String tel, RedirectAttributes redirectAttributes) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
 
     RestMemberInfo obj = this.restMemberInfoService.findByMobile(tel, getCurrentUserRestId());
     if (obj == null) {
       ajax.setStatusCode("100");
     } else {
       ajax.setStatusCode("200");
       ajax.setMessage(obj.getName());
       ajax.setRel(obj.getMbId());
     }
     return ajax;
   }
 /**
  * 打印
  * @param mcId
  * @param redirectAttributes
  * @return
  * @throws JsonProcessingException
  */
   @RequestMapping(value={"ajax/printMemberCardInfo"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone printMemberCardInfo(@RequestParam(value="mcId", defaultValue="") String mcId, RedirectAttributes redirectAttributes)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
     if (membershipCard == null) {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       ajax.setMessage("该会员卡不存在！");
     } else {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("执行打印...");
       String operatorName = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getUserName();
       String printerId = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId();
       Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
       PrintMemberCardInfoParamentsVo paraments = new PrintMemberCardInfoParamentsVo();
       paraments.setOperatorName(operatorName);
       paraments.setOperatorTime(new Date());
       paraments.setPrinterId(printerId);
       paraments.setRestId(restaurant.getRestId());
       paraments.setRestName(restaurant.getRestName());
       try {
         this.rechargePrinterService.printMemberCardInfo(membershipCard, paraments);
       } catch (PrinterException e) {
         ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
         ajax.setMessage("打印异常！");
 
         e.printStackTrace();
       }
     }
     return ajax; } 
   @RequestMapping(value={"ajax/getMemberByName/{name}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getMemberByName(@PathVariable("name") String name, RedirectAttributes redirectAttributes) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
 
     RestMemberInfo obj = this.restMemberInfoService.findByNameAndRestId(name, getCurrentUserRestId());
     if (obj == null) {
       ajax.setStatusCode("100");
     } else {
       ajax.setStatusCode("200");
       ajax.setMessage(obj.getName());
       ajax.setRel(obj.getMbId());
     }
     return ajax; }
 
   public boolean validateMobile(String mobile, String id)
   {
     boolean message = false;
     if (StringUtils.isNotEmpty(mobile)) {
       RestMemberInfo obj = this.restMemberInfoService.findByMobile(mobile, getCurrentUserRestId());
       if (obj == null) {
         message = true;
       }
       else if (StringUtils.isNotEmpty(id)) {
         if (obj.getMbId().equals(id))
           message = true;
         else
           message = false;
       }
       else {
         message = false;
       }
     }
 
     return message;
   }
 
   @RequestMapping(value={"pop/selectCardForm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String selectCardForm(@RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     Page membershipCards = this.membershipCardService.searchPageNoMemberCard(searchParams, getCurrentUserRestId(), null, pageNumber, 20, null, new String[] { "createTime" });
     model.addAttribute("membershipCards", membershipCards);
     model.addAttribute("action", "create");
     return "member/selectMemberCardForm";
   }
 
   @RequestMapping(value={"changeCardStatus/{mcId}/{cardStatus}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone changeCardStatus(@PathVariable("mcId") String mcId, @PathVariable("cardStatus") String cardStatus, RedirectAttributes redirectAttributes)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     MembershipCard card = this.membershipCardService.changeCardStatus(mcId, cardStatus, map);
 
     doSynchMultiTable(map);
 
     ArrayList cloudMethodParams = new ArrayList();
 
     MessageDataUtil messageDataUtil = new MessageDataUtil();
     if (StringUtils.equals(card.getCardStatus(), MemberCardStatusEnum.LOSS_REGISTER.getCode())) {
       messageDataUtil.lossRegisterMessageData(cloudMethodParams, card);
 
       doCallCloudMethod(SmtCodeEnum.LOSS_REGISTER, cloudMethodParams);
     }
 
     ajax.setStatusCode("200");
     ajax.setMessage("会员卡" + MemberCardStatusEnum.getDesc(cardStatus) + "成功");
     ajax.setRel("");
     return ajax;
   }
 
   @RequestMapping(value={"pop/chongzhi/createForm/{mcId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String chongzhiForm(Model model, @PathVariable("mcId") String mcId)
   {
     MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     List opaymentTypes = new ArrayList();
     for (PaymentType p : paymentTypes)
     {
       if ((!p.getPaymentType().equals(PaymentTypeEnum.CARD.getCode())) && 
         (!p.getPaymentType().equals(PaymentTypeEnum.CASH.getCode())))
         continue;
       opaymentTypes.add(p);
     }
 
     model.addAttribute("paymentTypes", opaymentTypes);
     model.addAttribute("membershipCard", membershipCard);
     model.addAttribute("now", new Date());
     return "member/chongzhiForm";
   }
 
   @RequestMapping(value={"chongzhi/create"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone chongzhiSave(@Valid MembershipCard membershipCard, @RequestParam(value="new_memberIntegral", defaultValue="0") BigDecimal new_memberIntegral, @RequestParam(value="isDrawBill", required=true) String isDrawBill, @RequestParam(value="isPrint", required=true) String isPrint, @RequestParam("new_paymentType") String cptId, RedirectAttributes redirectAttributes)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     ArrayList cloudMethodParams = new ArrayList();
     MembershipCard savedCard = this.membershipCardService.cardChongzhi(membershipCard, cptId, new_memberIntegral, map, cloudMethodParams);
 
     doSynchMultiTable(map);
 
     doCallCloudMethod(SmtCodeEnum.RECHARGE, cloudMethodParams);
 
     if (TrueFalseEnum.TRUE.getCode().equals(isPrint))
     {
       try
       {
         String restName = ((Restaurant)this.restaurantService.getOne(getCurrentUserRestId())).getRestName();
         HashMap printParaments = new HashMap();
         printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId());
         this.rechargePrinterService.printRecharge(getCurrentUserRestId(), restName, membershipCard, savedCard, isDrawBill, new_memberIntegral, savedCard.getUpdateEmployee().getName(), printParaments);
       } catch (Exception e) {
         e.printStackTrace();
         ajax.setMessage("会员卡充值成功,打印失败！");
         return ajax;
       }
     }
 
     ajax.setStatusCode("200");
     ajax.setMessage("会员卡充值成功");
     ajax.setRel("");
     return ajax;
   }
   @RequestMapping(value={"ajax/getMembeCardTypeById"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getMembeCardTypeById(@RequestParam("cid") String cid, RedirectAttributes redirectAttributes) throws JsonProcessingException {
     DwzAjaxDone ajax = new DwzAjaxDone();
     MembershipCardClass mclass = (MembershipCardClass)this.membershipCardClassService.getOne(cid);
     ajax.setStatusCode("200");
     ajax.setMessage(BigDecimalUtil.format(mclass.getCashPledge()).toString());
     ajax.setRel(mclass.getIsConsumeIntegral());
     return ajax;
   }
 
   @RequestMapping(value={"pop/opDetail/createForm/{mcId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String opDetailForm(Model model, @PathVariable("mcId") String mcId, @RequestParam(value="showType", defaultValue="0") int showType, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="cardOperationType", defaultValue="2") String cardOperationType, HttpServletRequest request)
   {
     MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
 
     if ((CardOperationTypeEnum.RECHARGE.getCode().equals(cardOperationType)) || 
       (CardOperationTypeEnum.CONSUME.getCode().equals(cardOperationType)))
     {
       Map searchParams = Servlets.getParametersStartingWith(request, "search_");
       searchParams.put("EQ_membershipCard.mcId", mcId);
       Page membershipCardOperations = this.membershipCardOperationService.searchPageCardOperate(searchParams, getCurrentUserRestId(), cardOperationType, mcId, pageNumber, 8, new String[] { "createTime" });
       
       model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
       model.addAttribute("searchMapParams", replaceDot(searchParams));
       model.addAttribute("membershipCardOperations", membershipCardOperations);
     }
     model.addAttribute("membershipCard", membershipCard);
     
     model.addAttribute("membershipCard", membershipCard);
     model.addAttribute("cardOperationType", cardOperationType);
     model.addAttribute("showType", Integer.valueOf(showType));
     return "member/opDetailForm";
   }
 
   @RequestMapping(value={"pop/member/createForm/{mbId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String opDetailForm(Model model, @PathVariable("mbId") String mbId, HttpServletRequest request)
   {
     RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(mbId);
     List membershipCards = this.membershipCardService.findByRestId(getCurrentUserRestId());
     model.addAttribute("membershipcards", membershipCards);
     model.addAttribute("membershipCardClasses", this.membershipCardClassService.findByRestId(getCurrentUserRestId()));
     model.addAttribute("paymentTypes", this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId()));
     model.addAttribute("works", this.baseCodeItemService.findItemsByBcCode("MemberWork"));
     model.addAttribute("edus", this.baseCodeItemService.findItemsByBcCode("MemberEdu"));
 
     model.addAttribute("restMemberInfo", restMemberInfo);
     return "member/memberForm"; } 
   @RequestMapping(value={"memberdetail/create"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone memberdetailSave(@Valid RestMemberInfo restMemberInfo, @RequestParam("salesmanId") String salesmanId, RedirectAttributes redirectAttributes) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
 
     if (validateMobile(restMemberInfo.getMobile(), restMemberInfo.getMbId()))
     {
       if (StringUtils.isNotEmpty(restMemberInfo.getName())) {
         restMemberInfo.setNameCode(ToPinYin.toPinYin(restMemberInfo.getName()));
       }
       if (StringUtils.isEmpty(salesmanId)) {
         restMemberInfo.setSalesman(null);
       }
       else
       {
         Employee salesman = (Employee)this.employeeService.loadOne(salesmanId);
         restMemberInfo.setSalesman(salesman);
       }
       restMemberInfo.setBirthday(DateUtil.toSimpleDate(restMemberInfo.getBirthdayStr()));
       if (restMemberInfo.getBirthday() != null) {
         restMemberInfo.setBirthdayInt(DateUtil.getOffsetDate(restMemberInfo.getBirthday()).intValue());
       }
 
       this.restMemberInfoService.save(restMemberInfo);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), restMemberInfo);
 
       ajax.setStatusCode("200");
       ajax.setMessage("修改会员资料成功");
     }
     else
     {
       ajax.setStatusCode("100");
       ajax.setMessage("电话号码重复");
     }
     ajax.setRel("");
     return ajax; } 
   @RequestMapping(value={"member/create"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone memberSave(@Valid RestMemberInfo restmemberinfo, RedirectAttributes redirectAttributes) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
 
     ajax.setStatusCode("200");
     ajax.setMessage("会员资料修改成功");
     ajax.setRel("");
     return ajax;
   }
 
   @RequestMapping(value={"pop/tuika/createForm/{mcId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String tuikaForm(@PathVariable("mcId") String mcId, Model model, HttpServletRequest request)
   {
     MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
     model.addAttribute("membershipCard", membershipCard);
     return "member/tuikaForm";
   }
   @RequestMapping(value={"tuika/save"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone tuikaSave(@RequestParam("mcId") String mcId, @RequestParam(value="tuikaBalance", defaultValue="0") String tuikaBalance, @RequestParam(value="tuikaCashPledge", defaultValue="0") String tuikaCashPledge) throws JsonProcessingException {
     DwzAjaxDone ajax = new DwzAjaxDone();
     if (!StringUtils.isEmpty(mcId))
     {
       LinkedHashMap map = new LinkedHashMap();
       this.membershipCardService.tuika(mcId, tuikaBalance, tuikaCashPledge, map);
 
       doSynchMultiTable(map);
     }
 
     ajax.setStatusCode("200");
     ajax.setMessage("退卡成功");
     ajax.setRel("");
     return ajax;
   }
   @RequestMapping({"cc"})
   @ResponseBody
   public String chaChong(@RequestParam(value="cardNo", required=false) String cardNo, @RequestParam(value="id", required=false) String mcId) { String message = "true";
     if (StringUtils.isNotEmpty(cardNo)) {
       List list = this.membershipCardService.findByCardNo(cardNo, getCurrentUserRestId());
       if (list.size() == 0) {
         message = "true";
       }
       else if (list.size() == 1) {
         if (StringUtils.isNotEmpty(mcId)) {
           if (((MembershipCard)list.get(0)).getMcId().equals(mcId))
             message = "true";
           else
             message = "false";
         }
         else
           message = "false";
       }
       else {
         message = "false";
       }
     }
 
     return message;
   }
 
   private Map<String, Object> replaceDot(Map<String, Object> searchParams)
   {
     Map newMap = new HashMap();
     for (String e : searchParams.keySet())
     {
       String key = e.replaceAll("\\.", "_");
       newMap.put(key, searchParams.get(e));
     }
     return newMap;
   }
 
   @ModelAttribute
   public void getEntity(@RequestParam(value="id", required=false) String id, @RequestParam(value="mcId", required=false) String mcId, Model model)
   {
     if ((id != null) && (!id.isEmpty())) {
       RestMemberInfo d = (RestMemberInfo)this.restMemberInfoService.getOne(id);
       d.setSalesman(null);
       model.addAttribute("restMemberInfo", d);
     }
     else if ((mcId != null) && (!mcId.isEmpty())) {
       MembershipCard d = (MembershipCard)this.membershipCardService.getOne(mcId);
       model.addAttribute("membershipCard", d);
     }
   }
 }

