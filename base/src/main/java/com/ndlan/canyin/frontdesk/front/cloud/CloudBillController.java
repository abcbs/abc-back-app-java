 package com.ndlan.canyin.frontdesk.front.cloud;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.common.Constants;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillLogService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TakeoutService;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.frontdesk.util.CommunicationUtil;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillLog;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.CloudPaymentTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.SynDatabaseStatusEnum;
import com.ndlan.canyin.core.common.TakeoutOrderDivEnum;
import com.ndlan.canyin.core.common.TakeoutStatusEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @RequestMapping({"/cloud"})
 @Lazy
 public class CloudBillController extends BaseManageController
 {
 
   @Autowired
   private CloudBillService cloudBillService;
 
   @Autowired
   private SpeOpReasonService speOpReasonService;
 
   @Autowired
   private TableAreaService tableAreaService;
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @Autowired
   private TakeoutService takeoutService;
 
   @Autowired
   private DinerBillLogService dinerBillLogService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @RequestMapping({"cloudIndex"})
   public String cloudIndex(Model model, HttpServletRequest request)
   {
     boolean isConnect = CommunicationUtil.isConnectG2();
     String restId = getCurrentUserRestId();
 
     boolean hasNewOrder = (Constants.BOOK_CHANGED) || (Constants.TAKEOUT_CHANGED);
     int noOperationCloudSize = Constants.BOOK_SIZE + Constants.TAKEOUT_SIZE;
     if ((Constants.TIME_LAST == 0L) && (isConnect) && (hasNewOrder))
     {
       Map mapData = this.cloudBillService.getCloudBillCountForAll(restId);
       if (mapData != null) {
         String statusCode = mapData.get("statusCode").toString();
         if (StatusCodeEnum.SUCCESS.getCode().equals(statusCode)) {
           Constants.TAKEOUT_SIZE = Integer.valueOf(mapData.get("takeoutCount").toString()).intValue();
           Constants.BOOK_SIZE = Integer.valueOf(mapData.get("orderCount").toString()).intValue();
           noOperationCloudSize = Constants.BOOK_SIZE + Constants.TAKEOUT_SIZE;
           if (hasNewOrder) {
             Constants.BOOK_CHANGED = false;
             Constants.TAKEOUT_CHANGED = false;
           }
         }
       }
     }
     else if (hasNewOrder) {
       Constants.BOOK_CHANGED = false;
       Constants.TAKEOUT_CHANGED = false;
     }
 
     Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
 
     String isBandCloud = StringUtils.isEmpty(restaurant.getIsBandCloudAccount()) ? TrueFalseEnum.FALSE.getCode() : restaurant.getIsBandCloudAccount();
     if ((TrueFalseEnum.TRUE.getCode().equals(isBandCloud)) && (
       (SynDatabaseStatusEnum.ERROR.getCode().equals(restaurant.getSynDatabaseStatus())) || 
       (SynDatabaseStatusEnum.INIT.getCode().equals(restaurant.getSynDatabaseStatus())) || 
       (SynDatabaseStatusEnum.SYNING.getCode().equals(restaurant.getSynDatabaseStatus())) || 
       (SynDatabaseStatusEnum.CLOUD_SYNING.getCode().equals(restaurant.getSynDatabaseStatus())) || 
       (SynDatabaseStatusEnum.VERSION_DIFFERENT.getCode().equals(restaurant.getSynDatabaseStatus()))))
     {
       isBandCloud = TrueFalseEnum.FALSE.getCode();
     }
 
     model.addAttribute("isBandCloud", isBandCloud);
     model.addAttribute("isConnect", Boolean.valueOf(isConnect));
     model.addAttribute("noOperationCloudSize", Integer.valueOf(noOperationCloudSize));
     return "cloud/cloudIndex";
   }
   @RequestMapping({"list"})
   public String coludBillList(Model model, HttpServletRequest request) { return "cloud/coludBillList";
   }
 
   @RequestMapping({"ajax/bill/list"})
   public String dishesList(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="20") int pagzSize, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="searchType", defaultValue="") String searchType, @RequestParam(value="cloudBillStatus", defaultValue="0") String cloudBillStatus, Model model, HttpServletRequest request)
   {
     boolean isConnect = CommunicationUtil.isConnectG2();
     if (!isConnect) {
       model.addAttribute("isConnect", "0");
     } else {
       Map mapData = new HashMap();
       Page ls = this.cloudBillService.getCloudBill(getCurrentUserRestId(), pageNumber, pagzSize, keywords, searchType, cloudBillStatus, mapData);
       model.addAttribute("bills", ls);
       model.addAttribute("mapData", mapData);
     }
     return "cloud/coludBillListContent";
   }
 
   @RequestMapping({"pop/billDetail"})
   public String billDetail(@RequestParam(value="billId", defaultValue="") String billId, Model model, HttpServletRequest request)
   {
     Map userTakeout = this.cloudBillService.getUserTakeoutDetail(billId);
     List dishes = this.cloudBillService.getUserTakeoutDetailDish(billId);
     dishes = this.cloudBillService.convertedUserTakeoutDetailDishToObjects(dishes);
     model.addAttribute("dishes", dishes);
     model.addAttribute("billId", billId);
     model.addAttribute("userTakeout", userTakeout);
     return "cloud/cloudTakeoutBillDetail";
   }
 
   @RequestMapping({"pop/cloudOrderBillDetail"})
   public String cloudOrderBillDetail(@RequestParam(value="billId", defaultValue="") String billId, Model model, HttpServletRequest request)
   {
     Map userOrder = this.cloudBillService.getUserOrderDetail(billId);
     List dishes = this.cloudBillService.getUserOrderDetailDish(billId);
     dishes = this.cloudBillService.convertedUserOrderDetailDishToObjects(dishes);
 
     List tableAreas = this.tableAreaService.getTableAreaByRestID(getCurrentUserRestId());
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("billId", billId);
     model.addAttribute("userOrder", userOrder);
     model.addAttribute("dishes", dishes);
 
     return "cloud/cloudOrderBillDetail";
   }
 
   @RequestMapping({"pop/cloudBillReviewPass"})
   public String cloudBillReviewPass(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="takeoutOrderDiv", defaultValue="1") String takeoutOrderDiv, @RequestParam(value="tabIds", defaultValue="") String tabIds, @RequestParam(value="isXiadan", required=true) String isXiadan, @RequestParam(value="orderPeople", defaultValue="") String orderPeople, @RequestParam(value="mobile", defaultValue="") String mobile, @RequestParam(value="linkmanTel", defaultValue="") String linkmanTel, @RequestParam(value="sendTime", defaultValue="") String sendTime, @RequestParam(value="totalCost", defaultValue="") String totalCost, @RequestParam(value="sendAddress", defaultValue="") String sendAddress, @RequestParam(value="eatTime", defaultValue="") String eatTime, @RequestParam(value="peopleNum", defaultValue="") String peopleNum, @RequestParam(value="gender", defaultValue="") String gender, @RequestParam(value="invoiceType", defaultValue="") String invoiceType, @RequestParam(value="invoiceTitle", defaultValue="") String invoiceTitle, Model model, HttpServletRequest request)
   {
     model.addAttribute("billId", billId);
     model.addAttribute("takeoutOrderDiv", takeoutOrderDiv);
     model.addAttribute("tabIds", tabIds);
     model.addAttribute("isXiadan", isXiadan);
 
     model.addAttribute("orderPeople", orderPeople);
     model.addAttribute("mobile", mobile);
     model.addAttribute("linkmanTel", linkmanTel);
     model.addAttribute("sendTime", sendTime);
     model.addAttribute("totalCost", totalCost);
     model.addAttribute("sendAddress", sendAddress);
 
     model.addAttribute("eatTime", eatTime);
     model.addAttribute("peopleNum", peopleNum);
     model.addAttribute("gender", gender);
     model.addAttribute("invoiceType", invoiceType);
     model.addAttribute("invoiceTitle", invoiceTitle);
     return "cloud/cloudBillReviewPass";
   }
 
   @RequestMapping({"pop/cloudBillReviewFailed"})
   public String cloudBillReviewFailed(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="takeoutOrderDiv", defaultValue="1") String takeoutOrderDiv, Model model, HttpServletRequest request)
   {
     List speOpReasons = this.speOpReasonService.findByRestIdAndReaType(getCurrentUserRestId(), SpecialReasonTypeEnum.TAKEOUT_REVIEWFAILED_REASON.getCode());
     model.addAttribute("speOpReasons", speOpReasons);
     model.addAttribute("billId", billId);
     model.addAttribute("takeoutOrderDiv", takeoutOrderDiv);
     return "cloud/cloudBillReviewFailed";
   }
 
   @RequestMapping({"pop/takeoutTrack"})
   public String takeoutTrack(@RequestParam(value="billId", required=true) String onlineTakeoutId, @RequestParam(value="billNo", required=true) String onlineBillNo, Model model, HttpServletRequest request)
   {
     String restId = getCurrentUserRestId();
 
     List cloudBillLogs = this.dinerBillLogService.findByRestIdAndOnlineId(restId, onlineTakeoutId);
     Takeout takeout = this.takeoutService.findByRestIdAndOnlineTakeoutId(restId, onlineTakeoutId);
     DinerBill dinerBill = takeout.getDinerBill();
     if (dinerBill != null)
     {
       List dinerBillLogs = this.dinerBillLogService.findByRestIdAndDinerBill(restId, dinerBill);
       model.addAttribute("dinerBillLogs", dinerBillLogs);
       model.addAttribute("billId", dinerBill.getBillId());
       model.addAttribute("billNo", dinerBill.getBillNo());
     }
     model.addAttribute("cloudBillLogs", cloudBillLogs);
     model.addAttribute("onlineBillNo", onlineBillNo);
     return "cloud/takeoutTrack";
   }
 
   @RequestMapping({"pop/orderTrack"})
   public String orderTrack(@RequestParam(value="billId", required=true) String onlineOrderId, @RequestParam(value="billNo", required=true) String onlineBillNo, Model model, HttpServletRequest request)
   {
     String restId = getCurrentUserRestId();
 
     List<DinerBillLog> cloudBillLogs = this.dinerBillLogService.findByRestIdAndOnlineId(restId, onlineOrderId);
 
     List<TableOrder> tableOrderList = this.tableOrderService.findByRestIdAndOnlineOrderId(restId, onlineOrderId);
     List orderList = new ArrayList();
     if ((tableOrderList != null) && (tableOrderList.size() > 0)) {
       for (TableOrder tableOrder : tableOrderList) {
         Map orderMap = new HashMap();
         String billId = tableOrder.getBillId();
         List<DinerBillLog> dinerBillLogs = null;
         if (StringUtils.isNotEmpty(billId))
         {
           DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
           dinerBillLogs = this.dinerBillLogService.findByRestIdAndDinerBill(restId, dinerBill);
           model.addAttribute("billNo", dinerBill.getBillNo());
           model.addAttribute("dinerBillLogs", dinerBillLogs);
         }
 
         List orderBillLog = new ArrayList();
         if (cloudBillLogs != null) {
           for (DinerBillLog dinerBillLog : cloudBillLogs) {
             if (BillOpTypeEnum.APPROVED.getCode().equals(dinerBillLog.getBillOpType())) {
               DinerBillLog newLog = new DinerBillLog();
               newLog.setBillNo(tableOrder.getOrderNo());
               newLog.setBillOpTypeDesc("创建");
               newLog.setCreateEmployee(dinerBillLog.getCreateEmployee());
               newLog.setCreateTime(dinerBillLog.getCreateTime());
               orderBillLog.add(newLog);
               break;
             }
           }
 
         }
 
         if (dinerBillLogs != null) {
           for (DinerBillLog dinerBillLog : dinerBillLogs) {
             if (BillOpTypeEnum.START_TABLE.getCode().equals(dinerBillLog.getBillOpType())) {
               DinerBillLog newLog = new DinerBillLog();
               newLog.setBillNo(tableOrder.getOrderNo());
               newLog.setBillOpTypeDesc("转开台");
               newLog.setCreateEmployee(dinerBillLog.getCreateEmployee());
               newLog.setCreateTime(dinerBillLog.getCreateTime());
               orderBillLog.add(newLog);
               break;
             }
           }
         }
         orderMap.put("orderBillLog", orderBillLog);
         orderMap.put("orderId", tableOrder.getOrderId());
         orderMap.put("orderNo", tableOrder.getOrderNo());
         orderList.add(orderMap);
       }
     }
 
     model.addAttribute("orderList", orderList);
     model.addAttribute("cloudBillLogs", cloudBillLogs);
     model.addAttribute("onlineBillNo", onlineBillNo);
     return "cloud/orderTrack";
   }
 
   @RequestMapping(value={"cloudBillReviewPass/update"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone cloudBillReviewPassUpdate(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="takeoutOrderDiv", defaultValue="") String takeoutOrderDiv, @RequestParam(value="reviewPassNotes", defaultValue="") String reviewPassNotes, @RequestParam(value="tabIds", defaultValue="") String tabIds, @RequestParam(value="isXiadan", required=true) String isXiadan, @RequestParam(value="orderPeople", defaultValue="") String orderPeople, @RequestParam(value="mobile", defaultValue="") String mobile, @RequestParam(value="linkmanTel", defaultValue="") String linkmanTel, @RequestParam(value="sendTime", defaultValue="") String sendTime, @RequestParam(value="totalCost", defaultValue="0") String totalCost, @RequestParam(value="sendAddress", defaultValue="") String sendAddress, @RequestParam(value="eatTime", defaultValue="") String eatTime, @RequestParam(value="peopleNum", defaultValue="") String peopleNum, @RequestParam(value="gender", defaultValue="") String gender, @RequestParam(value="invoiceType", defaultValue="") String invoiceType, @RequestParam(value="invoiceTitle", defaultValue="") String invoiceTitle, Model model, HttpServletRequest request)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     String restId = getCurrentUserRestId();
     String statusCode = StatusCodeEnum.SUCCESS.getCode();
     String orderStatus = "";
     MembershipCard membershipCard = null;
     Map messageMap = new HashMap();
     String takeoutStatus = TakeoutStatusEnum.CONFIRM.getCode();
     String checkName = getCurrentUser().getName();
     String checkTime = String.valueOf(new Date().getTime());
     messageMap.put("takeoutOrderDiv", takeoutOrderDiv);
     if (TrueFalseEnum.TRUE.getCode().equals(isXiadan)) {
       takeoutStatus = TakeoutStatusEnum.PLACE_ORDER.getCode();
     }
     try
     {
       if (TakeoutOrderDivEnum.ORDER.getCode().equals(takeoutOrderDiv))
       {
         Map orderMap = this.cloudBillService.getUserOrderDetail(billId);
         if (orderMap == null)
         {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("网络故障，请重新审核！");
           ajax.setType("1");
           return ajax;
         }
 
         List tableOrderList = this.tableOrderService.findByRestIdAndOnlineOrderId(restId, billId);
         if ((tableOrderList != null) && (tableOrderList.size() > 0))
         {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("此订单已经审核通过！");
           ajax.setType("1");
           return ajax;
         }
 
         String mcId = (String)orderMap.get("mcId");
         if (StringUtils.isNotEmpty(mcId)) {
           membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
         }
 
         String paymentType = (String)orderMap.get("paymentType");
         if (CloudPaymentTypeEnum.MCP.getCode().equals(paymentType)) {
           BigDecimal realCostOrder = new BigDecimal(orderMap.get("realCostOrder").toString());
 
           if (realCostOrder.compareTo(membershipCard.getBalance()) == 1) {
             ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
             ajax.setMessage("会员卡余额不足！");
             ajax.setType("1");
             return ajax;
           }
         }
 
         List orderBills = this.cloudBillService.getUserOrderDetailDish(billId);
         LinkedHashMap map = new LinkedHashMap();
         this.tableOrderService.saveCloudOrderBIll(getCurrentUserRestId(), orderMap, orderBills, membershipCard, tabIds, getCurrentUser(), map, orderPeople, mobile, eatTime, peopleNum, gender);
 
         doSynchMultiTable(map);
 
         Constants.BOOK_CHANGED = true;
         Map result = this.cloudBillService.reviewBillPass(billId, takeoutOrderDiv, takeoutStatus, reviewPassNotes, checkName, checkTime);
         if (result == null)
         {
           doSynchSingleTable(billId + "_o" + OperationTypeEnum.SQL.getCode(), 
             "UPDATE cl_user_order t set order_status = '" + takeoutStatus + 
             "',review_pass_notes = '" + reviewPassNotes + 
             "',checkName = '" + checkName + 
             "',checkTime = '" + checkTime + 
             "' where uo_id = '" + billId + 
             "' and order_status = '1'");
         }
         else {
           statusCode = (String)result.get("statusCode");
           orderStatus = (String)result.get("orderStatus");
         }
       }
       if (TakeoutOrderDivEnum.TAKEOUT.getCode().equals(takeoutOrderDiv))
       {
         Map takeoutMap = this.cloudBillService.getUserTakeoutDetail(billId);
         if (takeoutMap == null)
         {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("网络故障，请重新审核！");
           ajax.setType("1");
           return ajax;
         }
 
         Takeout takeout = this.takeoutService.findByRestIdAndOnlineTakeoutId(restId, billId);
         if (takeout != null)
         {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("此订单已经审核通过！");
           ajax.setType("1");
           return ajax;
         }
 
         String paymentType = (String)takeoutMap.get("paymentType");
         if (CloudPaymentTypeEnum.MCP.getCode().equals(paymentType)) {
           String mcId = (String)takeoutMap.get("mcId");
           membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
           String tCost = takeoutMap.get("totalCost").toString();
           BigDecimal totalCostBigDecimal = new BigDecimal(tCost);
 
           if (totalCostBigDecimal.compareTo(membershipCard.getBalance()) == 1) {
             ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
             ajax.setMessage("会员卡余额不足！");
             ajax.setType("1");
             return ajax;
           }
         }
 
         List takeoutBills = this.cloudBillService.getUserTakeoutDetailDish(billId);
         takeoutBills = this.cloudBillService.convertedUserTakeoutDetailDishToObjects(takeoutBills);
         if (isJudgeRm.equals("0")) {
           Map result = this.dinerBillService.cloudStockCheck(getCurrentUserRestId(), takeoutBills);
           if ((((String)result.get("result")).equals("0")) || (((String)result.get("result")).equals("1")) || (((String)result.get("result")).equals("4"))) {
             ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
             ajax.setMessageMap(result);
             return ajax;
           }
         }
 
         LinkedHashMap map = new LinkedHashMap();
         DinerBill dinerBill = this.takeoutService.saveCloudTakeoutBIll(getCurrentUserRestId(), getCurrentUser(), takeoutMap, takeoutBills, membershipCard, map, orderPeople, mobile, linkmanTel, sendTime, totalCost, sendAddress, invoiceType, invoiceTitle);
         messageMap.put("billId", dinerBill.getBillId());
 
         doSynchMultiTable(map);
 
         Constants.TAKEOUT_CHANGED = true;
         Map result = this.cloudBillService.reviewBillPass(billId, takeoutOrderDiv, OrderStatusEnum.APPLING.getCode(), reviewPassNotes, checkName, checkTime);
         if (result == null)
         {
           doSynchSingleTable(billId + "_t" + OperationTypeEnum.SQL.getCode(), 
             "UPDATE cl_user_takeout t set t.order_status = '" + OrderStatusEnum.APPLING.getCode() + 
             "',review_pass_notes = '" + reviewPassNotes + 
             "',checkName = '" + checkName + 
             "',checkTime = '" + checkTime + 
             "' where ut_id = '" + billId + 
             "' and order_status = '1'");
         }
         else {
           statusCode = (String)result.get("statusCode");
           orderStatus = (String)result.get("orderStatus");
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     ajax.setRel(billId);
     ajax.setStatusCode(statusCode);
     ajax.setSign(orderStatus);
     ajax.setMessageMap(messageMap);
     return ajax;
   }
 
   @RequestMapping(value={"cloudBillReviewFailed/update"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone cloudBillReviewFailedUpdate(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="takeoutOrderDiv", defaultValue="") String takeoutOrderDiv, @RequestParam(value="notes", defaultValue="") String notes, @RequestParam(value="failReasonDesc", defaultValue="") String failReasonDesc, Model model, HttpServletRequest request)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Map result = this.cloudBillService.reviewBillFailed(billId, takeoutOrderDiv, notes, failReasonDesc, getCurrentUserId());
     ajax.setRel(billId);
     String statusCode = (String)result.get("statusCode");
     if (StatusCodeEnum.SUCCESS.getCode().equals(statusCode)) {
       if (TakeoutOrderDivEnum.TAKEOUT.getCode().equals(takeoutOrderDiv))
         Constants.TAKEOUT_CHANGED = true;
       else if (TakeoutOrderDivEnum.ORDER.getCode().equals(takeoutOrderDiv)) {
         Constants.BOOK_CHANGED = true;
       }
     }
     ajax.setStatusCode(statusCode);
     ajax.setSign((String)result.get("orderStatus"));
     return ajax;
   }
 
   @RequestMapping({"pop/takeoutBillDetail/{billId}"})
   public String takeoutBillDetail(@PathVariable("billId") String billId, Model model, HttpServletRequest request)
   {
     Map userTakeout = this.cloudBillService.getUserTakeoutDetail(billId);
     Takeout takeout = this.takeoutService.findByRestIdAndOnlineTakeoutId(getCurrentUserRestId(), billId);
     List dishes = this.cloudBillService.getUserTakeoutDetailDish(billId);
     dishes = this.cloudBillService.convertedUserTakeoutDetailDishToObjects(dishes);
     model.addAttribute("dishes", dishes);
     model.addAttribute("billId", billId);
     model.addAttribute("userTakeout", userTakeout);
     model.addAttribute("takeout", takeout);
     return "cloud/takeoutBillDetail";
   }
 
   @RequestMapping({"pop/orderBillDetail/{billId}"})
   public String orderBillDetail(@PathVariable("billId") String billId, Model model, HttpServletRequest request)
   {
     String restId = getCurrentUserRestId();
 
     Map userOrder = this.cloudBillService.getUserOrderDetail(billId);
 
     List tableAreas = this.tableAreaService.getTableAreaByRestID(restId);
 
     List dishes = this.cloudBillService.getUserOrderDetailDish(billId);
     dishes = this.cloudBillService.convertedUserOrderDetailDishToObjects(dishes);
 
     List tableOrders = this.tableOrderService.findByRestIdAndOnlineOrderId(restId, billId);
     if ((tableOrders != null) && (tableOrders.size() > 0)) {
       TableOrder tableOrder = (TableOrder)tableOrders.get(0);
       model.addAttribute("order", tableOrder);
     }
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("billId", billId);
     model.addAttribute("userOrder", userOrder);
     model.addAttribute("dishes", dishes);
     return "cloud/orderBillDetail";
   }
   @RequestMapping(value={"isChangedOrder"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone isChangedOrder(Model model, HttpServletRequest request) { DwzAjaxDone ajax = new DwzAjaxDone();
     Map mapMessage = new HashMap();
     if ((Constants.TIME_LAST != 0L) && (Constants.NEED_REFRESH_CLOUD)) {
       mapMessage.put("needRefreshCloud", "1");
       Constants.NEED_REFRESH_CLOUD = false;
     } else {
       mapMessage.put("needRefreshCloud", "0");
     }
     ajax.setMessageMap(mapMessage);
     return ajax;
   }
 }

