 package com.ndlan.canyin.frontdesk.front.takeout;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.TakeoutService;
import com.ndlan.canyin.frontdesk.service.sygl.CashDiscountService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.MemberCardStatusEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.RoleTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateUtil;
import com.ndlan.canyin.core.web.Servlets;
import com.ndlan.canyin.sharelogic.service.printer.PayPrinterService;

import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @RequestMapping({"/takeout"})
 @Lazy
 public class TakeoutController extends BaseManageController
 {
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private TakeoutService takeoutService;
 
   @Autowired
   private RestMemberInfoService restMemberInfoService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private PayPrinterService payPrinterService;
 
   @Autowired
   private BillService billService;
 
   @Autowired
   private CashDiscountService cashDiscountService;
 
   @RequestMapping({"list"})
   public String list(Model model, HttpServletRequest request, @RequestParam(value="keywords", required=false) String keywords, @RequestParam(value="from", required=false, defaultValue="") String from, @RequestParam(value="preMobile", required=false) String preMobile)
   {
     model.addAttribute("from", from);
     model.addAttribute("preMobile", preMobile);
     model.addAttribute("keywords", keywords);
     return "takeout/list";
   }
 
   @RequestMapping({"ajax/takeoutContent"})
   public String takeoutContent(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize, @RequestParam(value="sendTimeType", required=false) String sendTimeType, @RequestParam(value="billStatus", required=false) String billStatus, @RequestParam(value="keyWords", required=false) String keywords, Model model, HttpServletRequest request)
   {
     int yudingCount = 0;
 
     int shiwuCount = 0;
 
     int sanshiCount = 0;
 
     int longCount = 0;
 
     int weiwanjieCount = 0;
 
     int weixiadanCount = 0;
 
     int yixiadanCount = 0;
 
     int paisongzhongCount = 0;
 
     int yiwanjieCount = 0;
 
     String restId = getCurrentUserRestId();
     String startDate = getDateBeforeSevenDays();
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);
 
     List yudingListSize = this.dinerBillService.getTakeoutListSize(restId, billStatus, "0", startDate, keywords);
 
     List shiwuListSize = this.dinerBillService.getTakeoutListSize(restId, billStatus, "15", startDate, keywords);
 
     List sanshiListSize = this.dinerBillService.getTakeoutListSize(restId, billStatus, "30", startDate, keywords);
 
     List longListSize = this.dinerBillService.getTakeoutListSize(restId, billStatus, "-1", startDate, keywords);
 
     List weiwanjieSize = this.dinerBillService.getTakeoutListSize(restId, "-1", sendTimeType, startDate, keywords);
 
     List weixiadanSize = this.dinerBillService.getTakeoutListSize(restId, BillStatusEnum.NOT_PLACE_ORDER.getCode(), sendTimeType, startDate, keywords);
 
     List yixiadanSize = this.dinerBillService.getTakeoutListSize(restId, BillStatusEnum.PLACE_ORDER.getCode(), sendTimeType, startDate, keywords);
 
     List paisongzhongSize = this.dinerBillService.getTakeoutListSize(restId, BillStatusEnum.SENDING.getCode(), sendTimeType, startDate, keywords);
 
     List yiwanjieSize = this.dinerBillService.getTakeoutListSize(restId, BillStatusEnum.SETTLE.getCode(), sendTimeType, startDate, keywords);
     yudingCount = yudingListSize == null ? 0 : yudingListSize.size();
     shiwuCount = shiwuListSize == null ? 0 : shiwuListSize.size();
     sanshiCount = sanshiListSize == null ? 0 : sanshiListSize.size();
     longCount = longListSize == null ? 0 : longListSize.size();
 
     weiwanjieCount = weiwanjieSize == null ? 0 : weiwanjieSize.size();
     weixiadanCount = weixiadanSize == null ? 0 : weixiadanSize.size();
     yixiadanCount = yixiadanSize == null ? 0 : yixiadanSize.size();
     paisongzhongCount = paisongzhongSize == null ? 0 : paisongzhongSize.size();
     yiwanjieCount = yiwanjieSize == null ? 0 : yiwanjieSize.size();
 
     List takeoutList = this.dinerBillService.getTakeoutList(restId, billStatus, sendTimeType, startDate, keywords, TrueFalseEnum.FALSE.getCode(), pageRequest.getOffset(), pageRequest.getPageSize());
     int totalSize = 0;
     if ("0".equals(sendTimeType)) {
       totalSize = yudingCount;
     } else if ("15".equals(sendTimeType)) {
       totalSize = shiwuCount;
     } else if ("30".equals(sendTimeType)) {
       totalSize = sanshiCount;
     } else if ("-1".equals(sendTimeType)) {
       totalSize = longCount;
     } else {
       List takeoutListAll = this.dinerBillService.getTakeoutListSize(restId, billStatus, sendTimeType, startDate, keywords);
       totalSize = takeoutListAll == null ? 0 : takeoutListAll.size();
       takeoutListAll = null;
     }
     Page<Map> page = new PageImpl(takeoutList, pageRequest, totalSize);
     for (Map map : page.getContent()) {
       String billId = (String)map.get("billId");
       if (StringUtils.isNotEmpty(billId)) {
         DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
         this.billService.calculator(restId, dinerBill);
         //Double sd =(Double)dinerBill.getOriCost().valueOf(val);
       BigDecimal moneyDev =new BigDecimal(dinerBill.getOriCost().toString()).setScale(2,RoundingMode.HALF_UP); 
      
       map.get("minuteDiff");

        
        
       
         map.put("totalCost", moneyDev.toString());
         dinerBill = null;
       }else{
    	   map.put("totalCost", "0.00");
       }
     }
     model.addAttribute("takeoutList", page);
 
     List dinerBills = this.dinerBillService.getTakeoutToday(restId);
     Object billStatusList = new ArrayList();
     ((List)billStatusList).add(BillStatusEnum.SETTLE.getCode());
     BigDecimal settleSum = this.dinerBillService.getRealCostSumByRestaurantAndBillStatus(restId, (List)billStatusList);
     settleSum = settleSum == null ? BigDecimal.ZERO : settleSum;
 
     ((List)billStatusList).removeAll((Collection)billStatusList);
     ((List)billStatusList).add(BillStatusEnum.NOT_PLACE_ORDER.getCode());
     ((List)billStatusList).add(BillStatusEnum.PLACE_ORDER.getCode());
     ((List)billStatusList).add(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     ((List)billStatusList).add(BillStatusEnum.SENDING.getCode());
     BigDecimal notSettleSum = this.dinerBillService.getPayableCostSumByRestaurantAndBillStatus(restId, (List)billStatusList);
     notSettleSum = notSettleSum == null ? BigDecimal.ZERO : notSettleSum;
 
     model.addAttribute("sendTimeType", sendTimeType);
     model.addAttribute("billStatus", billStatus);
     model.addAttribute("keywords", keywords);
     model.addAttribute("takeoutSize", String.valueOf(dinerBills.size()));
     model.addAttribute("settleSum", String.valueOf(settleSum));
     model.addAttribute("notSettleSum", String.valueOf(notSettleSum));
     model.addAttribute("today", DateUtil.shortSdf.format(new Date()));
 
     model.addAttribute("yudingCount", Integer.valueOf(yudingCount));
     model.addAttribute("shiwuCount", Integer.valueOf(shiwuCount));
     model.addAttribute("sanshiCount", Integer.valueOf(sanshiCount));
     model.addAttribute("longCount", Integer.valueOf(longCount));
 
     model.addAttribute("weiwanjieCount", Integer.valueOf(weiwanjieCount));
     model.addAttribute("weixiadanCount", Integer.valueOf(weixiadanCount));
     model.addAttribute("yixiadanCount", Integer.valueOf(yixiadanCount));
     model.addAttribute("paisongzhongCount", Integer.valueOf(paisongzhongCount));
     model.addAttribute("yiwanjieCount", Integer.valueOf(yiwanjieCount));
 
     yudingListSize = null;
     shiwuListSize = null;
     sanshiListSize = null;
     longListSize = null;
     weiwanjieSize = null;
     weixiadanSize = null;
     yixiadanSize = null;
     paisongzhongSize = null;
     yiwanjieSize = null;
     dinerBills = null;
     billStatusList = null;
     pageRequest = null;
     return (String)"takeout/listContent";
   }
 
   @RequestMapping(value={"ajax/takeoutInfo"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone takeoutInfo(Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Map messageMap = new HashMap();
     String restId = getCurrentUserRestId();
     String startDate = getDateBeforeSevenDays();
 
     List notPlaceOrderList = this.dinerBillService.getByRestaurantAndBillStatus(restId, BillStatusEnum.NOT_PLACE_ORDER.getCode(), startDate);
 
     List placeOrderList = this.dinerBillService.getByRestaurantAndBillStatus(restId, BillStatusEnum.PLACE_ORDER.getCode(), startDate);
 
     List settleList = this.dinerBillService.getByRestaurantAndBillStatus(restId, BillStatusEnum.SETTLE.getCode(), startDate);
 
     List somePlaceOrderList = this.dinerBillService.getByRestaurantAndBillStatus(restId, BillStatusEnum.SOME_PLACE_ORDER.getCode(), startDate);
 
     List sendingList = this.dinerBillService.getByRestaurantAndBillStatus(restId, BillStatusEnum.SENDING.getCode(), startDate);
 
     messageMap.put("notPlaceOrder", String.valueOf(notPlaceOrderList.size()));
     messageMap.put("placeOrder", String.valueOf(placeOrderList.size()));
     messageMap.put("settle", String.valueOf(settleList.size()));
     messageMap.put("sending", String.valueOf(sendingList.size()));
 
     messageMap.put("notSettle", String.valueOf(notPlaceOrderList.size() + placeOrderList.size() + somePlaceOrderList.size() + sendingList.size()));
     ajax.setMessageMap(messageMap);
     return ajax;
   }
 
   @RequestMapping(value={"pop/addTakeout"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String addTakeout(@RequestParam(value="tId", required=false) String tId, @RequestParam(value="preMobile", required=false) String preMobile, Model model, HttpServletRequest request)
   {
     boolean isMember = false;
     RestMemberInfo restMemberInfo = null;
     String restId = getCurrentUserRestId();
 
     if (StringUtils.isNotEmpty(tId)) {
       Takeout takeout = (Takeout)this.takeoutService.getOne(tId);
       model.addAttribute("takeout", takeout);
 
       String mobile = takeout.getMobile();
       if (StringUtils.isNotEmpty(mobile)) {
         restMemberInfo = this.restMemberInfoService.findByMobile(takeout.getMobile(), restId);
       }
 
       String ccdId = takeout.getCcdId();
       if (StringUtils.isNotEmpty(ccdId))
         model.addAttribute("ccdId", ccdId);
     }
     else {
       List takeoutList = this.takeoutService.getLastSendAtOnce();
       if ((takeoutList != null) && (takeoutList.size() > 0)) {
         String sendAtOnce = ((Takeout)takeoutList.get(0)).getSendAtOnce();
         model.addAttribute("sendAtOnce", sendAtOnce);
       }
     }
     model.addAttribute("startDate", new Date());
 
     if ((restMemberInfo == null) && 
       (StringUtils.isNotEmpty(preMobile))) {
       Takeout takeout = new Takeout();
       String str = "^[0-9]{11}$";
       if (preMobile.matches(str))
         takeout.setMobile(preMobile);
       else {
         takeout.setTelephone(preMobile);
       }
       restMemberInfo = this.restMemberInfoService.findByMobile(preMobile, restId);
       if (restMemberInfo != null) {
         takeout.setContactName(restMemberInfo.getName());
       }
       model.addAttribute("takeout", takeout);
     }
 
     if (restMemberInfo != null)
     {
       List<MembershipCard> membershipCardsList = this.membershipCardService.findByRestMemberInfoAndRestIdAndCardStatus(restMemberInfo, restId, MemberCardStatusEnum.NORMAL.getCode());
       List membershipCards = new ArrayList();
       for (MembershipCard membershipCard : membershipCardsList) {
         Map m = new HashMap();
         m.put("mcId", membershipCard.getMcId());
         m.put("cardNo", membershipCard.getCardNo());
         membershipCards.add(m);
       }
       model.addAttribute("membershipCards", membershipCards);
       isMember = true;
     }
 
     Employee user = (Employee)this.employeeService.loadOne(getCurrentUserId());
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     List roleList = user.getRoleList();
     Page cashDiscountAll = this.cashDiscountService.search(getCurrentUserRestId(), roleList, isMember, searchParams, 1, 999, new String[] { "createTime" });
     model.addAttribute("cashDiscountAll", cashDiscountAll);
     return "takeout/form/takeoutForm";
   }
 
   @RequestMapping(value={"ajax/getCashDiscounts"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getCashDiscount(Model model, HttpServletRequest request, @RequestParam(value="isOnlyMember", required=true) String isOnlyMember)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     List discountList = new ArrayList();
     Employee user = (Employee)this.employeeService.loadOne(getCurrentUserId());
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     List roleList = user.getRoleList();
     boolean isOnlyMemberBool = TrueFalseEnum.TRUE.getCode().equals(isOnlyMember);
     Page<CashDiscount> cashDiscountAll = this.cashDiscountService.search(getCurrentUserRestId(), roleList, isOnlyMemberBool, searchParams, 1, 999, new String[] { "createTime" });
     for (CashDiscount cashDiscount : cashDiscountAll.getContent()) {
       Map cashMap = new HashMap();
       cashMap.put("ccdId", cashDiscount.getCcdId());
       cashMap.put("discountName", cashDiscount.getDiscountName());
       discountList.add(cashMap);
     }
     Map messageObject = new HashMap();
     messageObject.put("discountList", discountList);
     ajax.setMessageObject(messageObject);
     return ajax;
   }
 
   @RequestMapping(value={"pop/sendForm/{tId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String sendForm(@PathVariable("tId") String tId, Model model, HttpServletRequest request)
   {
     Page employees = this.employeeService.searchEmployee(getCurrentUserRestId(), null, RoleTypeEnum.SENDMAN.getCode(), 1, 99999, "showSeq");
    
     Takeout takeout = (Takeout)this.takeoutService.getOne(tId);
     model.addAttribute("takeout", takeout);
     model.addAttribute("employees", employees);
     model.addAttribute("tId", tId);
     return "takeout/form/sendForm";
   }
 
   @RequestMapping(value={"pop/takeoutDetail/{tId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String takeoutDetail(@PathVariable("tId") String tId, Model model, HttpServletRequest request)
   {
     Takeout takeout = (Takeout)this.takeoutService.getOne(tId);
 
     if (takeout.getDinerBill() != null) {
       List dinerBillDishes = takeout.getDinerBill().getDinerBillDishes();
       List<DinerBillDishesSet> dinerBillDishesSets = takeout.getDinerBill().getDinerBillDishesSets();
       for (DinerBillDishesSet dinerBillDishesSet : dinerBillDishesSets) {
         DinerBillDishe newSet = new DinerBillDishe();
         newSet.setIsSet(TrueFalseEnum.TRUE.getCode());
         newSet.setDsId(dinerBillDishesSet.getDsId());
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
         newSet.setCreateTime(dinerBillDishesSet.getCreateTime());
         List dishesDishesList = new ArrayList();
         ObjectMapper mapper = new ObjectMapper();
         try
         {
           List list = (List)mapper.readValue(dinerBillDishesSet.getDsDishesDesc(), List.class);
           for (int i = 0; i < list.size(); i++) {
             Map map = (Map)list.get(i);
             String dishesName = map.get("dishesName").toString();
             String unitNum = map.get("unitNum").toString();
             String unitName = map.get("unitName").toString();
             DinerBillDishe dbdSet = new DinerBillDishe();
             dbdSet.setDishesName(dishesName);
             dbdSet.setUnitNum(Float.valueOf(unitNum).floatValue());
             dbdSet.setUnitName(unitName);
             dishesDishesList.add(dbdSet);
           }
         } catch (Exception e1) {
           e1.printStackTrace();
         }
         newSet.setDishesSetDishesList(dishesDishesList);
         dinerBillDishes.add(newSet);
       }
       Collections.sort(dinerBillDishes, new Comparator()
       {
    	 @Override
         public int compare(Object o1, Object o2) {
           return ((DinerBillDishe)o1).getCreateTime().compareTo(((DinerBillDishe)o2).getCreateTime());
         }

       });
       model.addAttribute("dinerBillDishes", dinerBillDishes);
     }
     model.addAttribute("takeout", takeout);
     return "takeout/form/takeoutDetail";
   }
 
   @RequestMapping(value={"saveSender"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveSender(Model model, HttpServletRequest request, @Valid @ModelAttribute("takeout") Takeout takeout)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     LinkedHashMap map = new LinkedHashMap();
     try {
       this.takeoutService.saveSender(takeout, map);
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       DinerBill dinerBill = takeout.getDinerBill();
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
       HashMap printParaments = new HashMap();
       printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId());
       this.payPrinterService.printPay(getCurrentUserRestId(), dinerBill, dinerBill.getTable(), "PAY", "1", printParaments);
     } catch (Exception e) {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       e.printStackTrace();
     }
 
     doSynchMultiTable(map);
 
     return ajax;
   }
 
   @RequestMapping(value={"print/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone pay(@PathVariable("id") String billId)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     try {
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
       this.payPrinterService.printPay(getCurrentUserRestId(), dinerBill, dinerBill.getTable(), "PAY", "1", null);
     } catch (PrinterException e) {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       ajax.setMessage("打印失败");
       return ajax;
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("打印成功");
     return ajax;
   }
 
   @RequestMapping(value={"ajax/getMemberCardList"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getMemberCardList(@RequestParam(value="mobile", required=true) String mobile, HttpServletRequest request, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Map messageObject = new HashMap();
     String restId = getCurrentUserRestId();
     RestMemberInfo restMemberInfo = null;
     if (StringUtils.isNotEmpty(mobile)) {
       restMemberInfo = this.restMemberInfoService.findByMobile(mobile, restId);
     }
 
     if (restMemberInfo == null) {
       List takeoutList = this.takeoutService.getLastTakeoutByMobile(restId, mobile);
       if ((takeoutList != null) && (takeoutList.size() > 0))
       {
         Takeout takeout = (Takeout)takeoutList.get(0);
         messageObject.put("memeberName", takeout.getContactName());
       }
       ajax.setMessageObject(messageObject);
       return ajax;
     }
 
     List<MembershipCard> membershipCardsList = this.membershipCardService.findByRestMemberInfoAndRestIdAndCardStatus(restMemberInfo, restId, MemberCardStatusEnum.NORMAL.getCode());
     List membershipCards = new ArrayList();
     for (MembershipCard membershipCard : membershipCardsList) {
       Map m = new HashMap();
       m.put("mcId", membershipCard.getMcId());
       m.put("cardNo", membershipCard.getCardNo());
       membershipCards.add(m);
     }
     messageObject.put("membershipCards", membershipCards);
 
     messageObject.put("memeberName", restMemberInfo.getName());
     ajax.setMessageObject(messageObject);
     return ajax;
   }
 
   @RequestMapping(value={"ajax/getDiscountInfo"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getDiscountName(@RequestParam(value="mcId", required=true) String mcId, HttpServletRequest request, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Map messageObject = new HashMap();
     MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
     if (membershipCard != null) {
       messageObject.put("ccdId", membershipCard.getMembershipCardClass().getCashDiscount().getCcdId());
     }
     ajax.setMessageObject(messageObject);
     return ajax;
   }
 
   @RequestMapping(value={"saveTakeout"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveAddress(Model model, HttpServletRequest request, @Valid @ModelAttribute("takeout") Takeout takeout, @RequestParam("ccdId") String ccdId)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     //takeId  派送员
     LinkedHashMap map = new LinkedHashMap();
     OperationTypeEnum o = OperationTypeEnum.CREATE;
     if (StringUtils.isNotEmpty(takeout.gettId())) {
       o = OperationTypeEnum.UPDATE;
     }
     else
     {
       takeout.settId(null);
     }
     try {
       takeout.setTotalCost(takeout.getDeliverCost());
       if (StringUtils.isNotEmpty(ccdId)) {
         takeout.setCcdId(ccdId);
       }
       if (TrueFalseEnum.TRUE.getCode().equals(takeout.getSendAtOnce())) {
         takeout.setSendTime(new Date());
       }
       //生成编号
       /*Date date = new Date();
       int k = 1;
       String number = String.format("%tY%<tm%<td%03d", date, k);
      // SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
       SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
       DecimalFormat df = new DecimalFormat("000");
       String d = sdf.format(date);
       String n = df.format(k);
       ArrayList list = new ArrayList();
       for (char c = 'A'; c <= 'Z'; c++) {
           list.add(c);
       }
       String str = "";
       for (int i = 0; i < 2; i++) {
           int num = (int) (Math.random() * 26);
           str = str + list.get(num);
       }
       takeout.setTakesNumber(d +str);*/
       
 
       Takeout ftakeout = this.takeoutService.save(takeout);
 
       map.put(takeout.gettId() + "_" + o.getCode(), takeout);
 
       doSynchMultiTable(map);
 
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setValue(takeout.gettId());
       ajax.setForwardUrl(ftakeout.getBillNo());
     } catch (Exception e) {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       e.printStackTrace();
     }
     return ajax;
   }
 
   @RequestMapping(value={"chedanForNoBill"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone chedanForNoBill(Model model, HttpServletRequest request, @Valid @ModelAttribute("takeout") Takeout takeout)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     LinkedHashMap map = new LinkedHashMap();
     OperationTypeEnum o = OperationTypeEnum.CREATE;
     if (StringUtils.isNotEmpty(takeout.gettId())) {
       o = OperationTypeEnum.UPDATE;
     }
     else
     {
       takeout.settId(null);
     }
     try {
       takeout.setIsCanceled(TrueFalseEnum.TRUE.getCode());
       this.takeoutService.save(takeout);
 
       map.put(takeout.gettId() + "_" + o.getCode(), takeout);
 
       doSynchMultiTable(map);
 
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     } catch (Exception e) {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       e.printStackTrace();
     }
     return ajax;
   }
 
   @RequestMapping(value={"getMobileList"}, produces={"application/json"})
   @ResponseBody
   public List<String> getMobileList(@RequestParam(value="mobile", required=false) String mobile, Model model, HttpServletRequest request)
   {
     String dateTime = getDateBeforeSevenDays();
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     List mobileList = null;
     try {
       mobile = "%" + mobile + "%";
       mobileList = this.takeoutService.getMobileList(df.parse(dateTime), getCurrentUserRestId(), mobile);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return mobileList;
   }
 
   @RequestMapping(value={"getSendAddressList"}, produces={"application/json"})
   @ResponseBody
   public List<String> getSendAddressList(@RequestParam(value="mobile", required=false) String mobile, @RequestParam(value="telephone", required=false) String telephone, Model model, HttpServletRequest request)
   {
     String dateTime = getDateBeforeSevenDays();
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     List addressList = null;
     try {
       String restId = getCurrentUserRestId();
       if (StringUtils.isNotEmpty(mobile))
         addressList = this.takeoutService.getSendAddressListByMobile(df.parse(dateTime), restId, mobile);
       else if (StringUtils.isNotEmpty(telephone))
         addressList = this.takeoutService.getSendAddressListByTelephone(df.parse(dateTime), restId, telephone);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return addressList;
   }
 
   private String getDateBeforeSevenDays()
   {
     Calendar cal = Calendar.getInstance();
     cal.set(5, cal.get(5) - 7);
     return DateUtil.shortSdf.format(cal.getTime());
   }
 
   @ModelAttribute
   public void getUserAddress(@RequestParam(value="tId", required=false) String id, Model model) {
     if ((id != null) && (!id.isEmpty())) {
       Takeout takeout = (Takeout)this.takeoutService.getOne(id);
       model.addAttribute("takeout", takeout);
     }
   }
 }

