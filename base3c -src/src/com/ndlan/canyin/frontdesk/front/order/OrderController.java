 package com.ndlan.canyin.frontdesk.front.order;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.meta.BaseCodeItem;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.core.common.BillSeqTypeEnum;
import com.ndlan.canyin.core.common.EnableStatusEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.OrderWayEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.web.Servlets;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/order"})
 @Lazy
 public class OrderController extends BaseManageController
 {
 
   @Autowired
   private TableAreaService tableAreaService;
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private SpeOpReasonService speOpReasonService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   private RestMemberInfoService restMemberInfoService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @RequestMapping({""})
   public String list(Model model, HttpServletRequest request, @RequestParam(value="kewWords", required=false) String kewWords, @RequestParam(value="from", required=false, defaultValue="") String from, @RequestParam(value="preMobile", required=false) String preMobile)
   {
     model.addAttribute("from", from);
     model.addAttribute("preMobile", preMobile);
     model.addAttribute("kewWords", kewWords);
     return "order/list";
   }
 
   @RequestMapping({"ajax/order/list"})
   public String tableList(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pageSize", defaultValue="12") int pageSize, @RequestParam(value="kewWords", required=false) String kewWords, @RequestParam(value="direction", required=false, defaultValue="desc") String direction, @RequestParam(value="sortCol", required=false, defaultValue="createTime") String sortCol, Model model, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
 
     List tableAreas = this.tableAreaService.getTableAreaByRestID(getCurrentUserRestId());
 
     Page<TableOrder> tableOrders = this.tableOrderService.findByKeywords(getCurrentUserRestId(), searchParams, kewWords, pageNumber, pageSize, direction, sortCol);
     for (TableOrder each : tableOrders)
     {
       /*if(each.getOrderBillDishes().size()>0){
    	   each.setIsStatus("1");
       }else{
    	   each.setIsStatus("0");
       }*/
       if ((!each.getOrderStatus().equals(OrderStatusEnum.WAIT_COMMIT.getCode())) && 
         (!each.getOrderStatus().equals(OrderStatusEnum.APPLING.getCode())))
       {
         continue;
       }
       Date orderTime = each.getOrderTime();
       Date now = DateProvider.DEFAULT.getDate();
       if ((orderTime == null) || (DateUtil.getMinutes(orderTime, now).longValue() <= UserSettingCache.getInstance().orderExpireTime))
         continue;
       each.setOrderStatus(OrderStatusEnum.EXPIRE.getCode());
     }
 
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("tableOrders", tableOrders);
     model.addAttribute("kewWords", kewWords);
     model.addAttribute("direction", direction);
     model.addAttribute("sortCol", sortCol);
     return "order/listOrderContent";
   }
   @RequestMapping(value={"pop/ajax/confirm/{flag}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone confirm(@RequestParam(value="orderId", required=true) String orderId, @PathVariable("flag") String flag, Model model) { DwzAjaxDone ajax = new DwzAjaxDone();
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
     if (TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(flag))
       tableOrder.setOrderStatus(OrderStatusEnum.APPLING.getCode());
     else if (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(flag)) {
       tableOrder.setOrderStatus(OrderStatusEnum.NOT_CONFIRM.getCode());
     }
     this.tableOrderService.save(tableOrder);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), tableOrder);
 
     ajax.setStatusCode("200");
     ajax.setMessage("审核成功");
     ajax.setRel("");
     return ajax; }
 
   @RequestMapping(value={"pop/cancle/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popCancle(@PathVariable("id") String orderId, Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="rows", defaultValue="10") int rows)
   {
     PageRequest pageRequest = new PageRequest(page, rows, new Sort(new String[] { "reaId" }));
     Page speOpReasons = this.speOpReasonService.getPage(pageRequest, getCurrentUserRestId(), SpecialReasonTypeEnum.CANCEL_ORDER.getCode(), EnableStatusEnum.NORMAL.getCode());
     model.addAttribute("speOpReasons", speOpReasons);
     model.addAttribute("orderId", orderId);
     return "order/form/cancleOrderForm";
   }
 
   @RequestMapping(value={"pop/member/select"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popCardForm(Model model) {
     return "order/form/selectMemberForm";
   }
 
   @RequestMapping({"ajax/member/list"})
   public String memberList(@RequestParam(value="kewWords", required=false) String kewWords, Model model, HttpServletRequest request)
   {
     List restMemberInfos = this.restMemberInfoService.searchMember(getCurrentUserRestId(), kewWords);
     model.addAttribute("restMemberInfos", restMemberInfos);
     model.addAttribute("kewWords", kewWords);
     return "order/form/memberContent";
   }
 
   @RequestMapping(value={"cancle/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone cancle(@PathVariable("id") String orderId, @RequestParam(value="reaId", required=false) String reaId, @RequestParam(value="cancleReason", required=false) String cancleReason, Model model)
   {
	 if(reaId ==null && reaId ==""){
		 reaId="73ecdca0-f54f-11e4-af9a-02004c4f4f50";
	 }
	 if(cancleReason ==null && cancleReason== ""){
		 cancleReason="客户要求";
	 }
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.tableOrderService.cancelOrder(getCurrentUserRestId(), orderId, cancleReason, map);
     doSynchMultiTable(map);
     ajax.setStatusCode("200");
     ajax.setMessage("取消成功");
     ajax.setRel("");
     return ajax;
   }
 
   @RequestMapping(value={"pop/confirm/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popConfirmForm(@PathVariable("id") String orderId, Model model) {
     model.addAttribute("orderId", orderId);
     return "order/form/confirmForm";
   }
 
   @RequestMapping(value={"pop/kaitai/create/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popKaitaiForm(@PathVariable("id") String orderId, Model model) {
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
     DinerBill dinderBill = new DinerBill();
     Table table = tableOrder.getTable();
     dinderBill.setPeopleNum(tableOrder.getPeopleNum());
     if (tableOrder.getWaiter() != null)
     {
       dinderBill.setWaiterId(tableOrder.getWaiter().getEmpId());
       dinderBill.setWaiterName(tableOrder.getWaiter().getName());
     }
     if (tableOrder.getSalesMg() != null)
     {
       dinderBill.setSalesmanId(tableOrder.getSalesMg().getEmpId());
       dinderBill.setSalesmanName(tableOrder.getSalesMg().getName());
     }
     dinderBill.setNotes(tableOrder.getNotes());
     dinderBill.setBillTime(new Date());
     dinderBill.setCreateEmployee(getCurrentUser());
     dinderBill.setTable(table);
     dinderBill.setTabNo(table.getTabNo());
     model.addAttribute("dinerBill", dinderBill);
     model.addAttribute("action", "create");
     model.addAttribute("orderId", orderId);
     model.addAttribute("orderNo", tableOrder.getOrderNo());
     return "index/kaitaiForm";
   }
 
   @RequestMapping(value={"pop/order/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popCreateForm(Model model, @RequestParam(value="tabId", defaultValue="") String tabId, @RequestParam(value="preMobile", required=false) String preMobile)
   {
     String restId = getCurrentUserRestId();
 
     model.addAttribute("orderWayList", getOrderWayList());
 
     List paymentTypes = getPaymentTypeList(false);
     model.addAttribute("paymentTypeList", paymentTypes);
     TableOrder tableOrder = new TableOrder();
     tableOrder.setOrderWay(OrderWayEnum.TEL.getCode());
 
     if ((tabId != null) && (!tabId.isEmpty()))
     {
       Table t = (Table)this.tableService.getOne(tabId);
       tableOrder.setTable(t);
       tableOrder.setTabNo(t.getTabNo());
       if (t.getWaiter() != null)
       {
         tableOrder.setWaiter(t.getWaiter());
       }
     }
 
     if (StringUtils.isNotEmpty(preMobile))
     {
       tableOrder.setTelphone(preMobile);
 
       RestMemberInfo restMemberInfo = this.restMemberInfoService.findByMobile(preMobile, restId);
       if (restMemberInfo != null) {
         tableOrder.setOrderPeopleName(restMemberInfo.getName());
       }
     }
 
    //tableOrder.setPaymentType((PaymentType)paymentTypes.get(0));
     tableOrder.setIsEnterDiancaiPage(UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getIsOrderEnterDesk());
     model.addAttribute("tableOrder", tableOrder);
     model.addAttribute("action", "create");
     return "order/form/orderForm";
   }
 
   @RequestMapping(value={"pop/order/update/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popUpdateForm(@PathVariable("id") String id, Model model)
   {
     model.addAttribute("orderWayList", getOrderWayList());
 
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(id);
     PaymentType paymentType = tableOrder.getPaymentType();
     boolean isExistOnlinePay = false;
     //支付方式
     /*if (PaymentTypeEnum.WEB_PAY.getCode().equals(paymentType.getPaymentType())) {
       isExistOnlinePay = true;
     }*/
 
     model.addAttribute("paymentTypeList", getPaymentTypeList(isExistOnlinePay));
 
     PaymentTypeEnum.WEB_PAY.getCode().equals(tableOrder.getPaymentType());
 
     model.addAttribute("isExistOnlinePay", Boolean.valueOf(isExistOnlinePay));
     model.addAttribute("action", "update");
     model.addAttribute("tableOrder", tableOrder);
 
     Date orderTime = tableOrder.getOrderTime();
     Date now = DateProvider.DEFAULT.getDate();
     if ((orderTime != null) && (DateUtil.getMinutes(orderTime, now).longValue() > UserSettingCache.getInstance().orderExpireTime))
     {
       tableOrder.setOrderStatus(OrderStatusEnum.EXPIRE.getCode());
     }
 
     if (OrderStatusEnum.APPLING.getCode().equalsIgnoreCase(tableOrder.getOrderStatus())) {
       return "order/form/orderForm";
     }
 
     return "order/form/orderLossForm";
   }
 
   @RequestMapping(value={"create"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone save(@Valid @ModelAttribute("tableOrder") TableOrder tableOrder, @RequestParam(value="waiterId", required=false) String waiterId, @RequestParam(value="salesmanId", required=false) String salesmanId, @RequestParam(value="mbId", required=false) String mbId, @RequestParam(value="orderPeopleName", required=false) String orderPeopleName, RedirectAttributes redirectAttributes)
     throws JsonProcessingException, ParseException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     tableOrder.getOrderId();
     tableOrder.getTable().getOrderStatus();
     
     Table table = tableOrder.getTable();
     if ((table == null) || (table.getTabId() == null) || (table.getTabId().isEmpty()))
     {
       String tabNo = tableOrder.getTabNo();
       if ((tabNo != null) && (!tabNo.isEmpty()))
       {
         table = this.tableService.findByTabNoAndRestIdAndIsEnable(tabNo, getCurrentUserRestId());
         if (table == null)
         {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("预定餐台不存在!");
           return ajax;
         }
         tableOrder.setTable(table);
         tableOrder.setTabNo(table.getTabNo());
       }
 
     }
     else{
    	 //截取日期字符串
    	 String mmm=tableOrder.getOrderTimeStr();
    	 mmm=mmm.substring(0,10);
		 
    	 Date date=DateUtil.toDate(tableOrder.getOrderTimeStr());
		 int hour = date.getHours();
		 //时间
		 Date dates = new Date();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 df.format(dates);
		 
		 Date datea=DateUtil.toDate(df.format(dates));
		 int hours = datea.getHours();
		 //预定时段的限定
		// if(hour>=hours+2){
			 List<TableOrder> timeList=null;
			 //餐桌有订订单
			// timeList=this.tableOrderService.findOrdertimezhou(table.getTabId(), getCurrentUserRestId(),mmm);
//			 上午
			 if(hour>=0&&hour<=12)
			 {
				String mmmbegin=mmm+" 00:00:00";
				String mmmend=mmm+" 12:00:00";
				Date datebegin=DateUtil.toDate(mmmbegin);
				Date dateend=DateUtil.toDate(mmmend);
				 timeList=this.tableOrderService.findOrdertime(table.getTabId(), getCurrentUserRestId(),datebegin,dateend);
			 }
//			 下午
			 if(hour<=24&&hour>=12){
				 String mmmbegin=mmm+" 12:00:00";
				 String mmmend=mmm+" 23:59:59";
				 Date datebegin=DateUtil.toDate(mmmbegin);
				 Date dateend=DateUtil.toDate(mmmend);
				 timeList=this.tableOrderService.findOrdertime(table.getTabId(), getCurrentUserRestId(),datebegin,dateend);
			 }
			 
			 if(timeList.size()>0){
				 ajax.setStatusCode(StatusCodeEnum.YANZHENGSUCCESS.getCode());
		         ajax.setMessage("该桌台与 "+timeList.get(0)+" 已经被预定!");
		         return ajax;
			 }
		 /*}else{
			 ajax.setStatusCode(StatusCodeEnum.YANZHENGSUCCESS.getCode());
	         ajax.setMessage("预定时间请大于当前时间2小时!");
	         return ajax; 
			 
		 }*/
		 
    	 
    	 
     }
 
     if (!StringUtils.isEmpty(waiterId))
     {
       Employee waiter = new Employee();
       waiter.setEmpId(waiterId);
       tableOrder.setWaiter(waiter);
     }
     if (!StringUtils.isEmpty(salesmanId))
     {
       Employee salesMg = new Employee();
       salesMg.setEmpId(salesmanId);
       tableOrder.setSalesMg(salesMg);
     }
     tableOrder.setRestId(getCurrentUserRestId());
     tableOrder.setOrderStatus(OrderStatusEnum.APPLING.getCode());
     tableOrder.setMbId(mbId);
     tableOrder.setOrderPeopleName(orderPeopleName);
     if ((table != null) && (table.getTabId() != null))
     {
       table = (Table)this.tableService.getOne(table.getTabId());
       tableOrder.setTabNo(table.getTabNo());
       tableOrder.setTable(table);
     }
     LinkedHashMap map = new LinkedHashMap();
     tableOrder.setOrderTime(DateUtil.toDate(tableOrder.getOrderTimeStr()));
     if(tableOrder.getOrderNo()==null){
    	 tableOrder.setOrderNo(this.dinerBillSeqService.createNewBillNo(getCurrentUserRestId(), BillSeqTypeEnum.ORDER, map));
     }
     TableOrder tableOrders= this.tableOrderService.saveOrder(getCurrentUserRestId(), tableOrder, map);
     
     tableOrders.getOrderId();
     
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setForwardUrl(tableOrder.getOrderId());
     ajax.setMessage("预定成功，可以在预定中查看");
     ajax.setRel(tableOrder.getIsEnterDiancaiPage());
     return ajax;
   }
 
   @RequestMapping(value={"update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone update(@Valid @ModelAttribute("tableOrder") TableOrder tableOrder, @RequestParam(value="waiterId", required=false) String waiterId, @RequestParam(value="salesmanId", required=false) String salesmanId, RedirectAttributes redirectAttributes)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     Table table = tableOrder.getTable();
     if ((table == null) || (table.getTabId() == null) || (table.getTabId().isEmpty()))
     {
       String tabNo = tableOrder.getTabNo();
       if ((tabNo != null) && (!tabNo.isEmpty()))
       {
         table = this.tableService.findByTabNoAndRestIdAndIsEnable(tabNo, getCurrentUserRestId());
         if (table == null)
         {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("预定餐台不存在!");
           return ajax;
         }
         tableOrder.setTable(table);
       }
 
     }
 
     tableOrder.setRestId(getCurrentUserRestId());
     tableOrder.setOrderStatus(OrderStatusEnum.APPLING.getCode());
     if ((table != null) && (table.getTabId() != null))
     {
       table = (Table)this.tableService.getOne(table.getTabId());
       tableOrder.setTabNo(table.getTabNo());
     }
     if (!StringUtils.isEmpty(waiterId))
     {
       Employee waiter = new Employee();
       waiter.setEmpId(waiterId);
       tableOrder.setWaiter(waiter);
     }
     if (!StringUtils.isEmpty(salesmanId))
     {
       Employee salesMg = new Employee();
       salesMg.setEmpId(salesmanId);
       tableOrder.setSalesMg(salesMg);
     }
     LinkedHashMap map = new LinkedHashMap();
     this.tableOrderService.saveOrder(getCurrentUserRestId(), tableOrder, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setForwardUrl(tableOrder.getOrderId());
     ajax.setMessage("修改预定成功");
     ajax.setRel(tableOrder.getIsEnterDiancaiPage());
     return ajax;
   }
 
   private List<BaseCodeItem> getOrderWayList()
   {
     List orderWayList = new ArrayList();
     BaseCodeItem telWay = new BaseCodeItem();
     telWay.setBciCode(OrderWayEnum.TEL.getCode());
     telWay.setBciDesc(OrderWayEnum.TEL.getDesc());
     orderWayList.add(telWay);
     BaseCodeItem restWay = new BaseCodeItem();
     restWay.setBciCode(OrderWayEnum.RESTAURANT.getCode());
     restWay.setBciDesc(OrderWayEnum.RESTAURANT.getDesc());
     orderWayList.add(restWay);
 
     BaseCodeItem netWay = new BaseCodeItem();
     netWay.setBciCode(OrderWayEnum.RESTWEB.getCode());
     netWay.setBciDesc(OrderWayEnum.RESTWEB.getDesc());
     orderWayList.add(netWay);
 
     return orderWayList;
   }
 
   private List<PaymentType> getPaymentTypeList(boolean isExistOnlinePay)
   {
     List paymentTypeList = new ArrayList();
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     for (PaymentType e : paymentTypes) {
       if ((e.getPaymentType().equals(PaymentTypeEnum.CASH.getCode())) || (e.getPaymentType().equals(PaymentTypeEnum.CARD.getCode()))) {
         paymentTypeList.add(e);
       }
       if ((isExistOnlinePay) && (e.getPaymentType().equals(PaymentTypeEnum.WEB_PAY.getCode()))) {
         paymentTypeList.add(e);
       }
     }
     return paymentTypeList;
   }
 
   @RequestMapping(value={"ajax/getOrderStatusNum"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getOrderStatusNum(Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     int[] nums = this.tableOrderService.getOrderStatusNum(getCurrentUserRestId());
     ajax.setStatusCode("200");
     ajax.setMessage("[{'order':'" + nums[0] + "','wait':'" + nums[1] + "'}]");
     ajax.setRel("");
     return ajax;
   }
   
   @RequestMapping(value={"ajax/getOrderNo"}, produces={"application/json"})
   public void getOrderNo(Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     model.addAttribute("orderNo", this.dinerBillSeqService.createNewBillNo(getCurrentUserRestId(), BillSeqTypeEnum.ORDER, map));
     model.addAttribute("par", this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId()));
   }
   
   @RequestMapping(value={"ajax/getpaymentTypes"}, produces={"application/json"})
   public void getpaymentTypes(Model model)
   {
	   List<PaymentType> paymentTypes =this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     model.addAttribute("paymentTypes",paymentTypes );
   }
   
   @RequestMapping(value={"ajax/getorderWayList"}, produces={"application/json"})
   public void getorderWayList(Model model)
   {
     model.addAttribute("orderWayList",  getOrderWayList());
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
   public void getEntity(@RequestParam(value="id", required=false) String id, Model model)
   {
     if ((id != null) && (!id.isEmpty())) {
       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(id);
       tableOrder.setPaymentType(null);
       tableOrder.setTable(null);
       model.addAttribute("tableOrder", tableOrder);
     }
   }
 }

