 package com.ndlan.canyin.mobileserver.front;
 
 import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.dto3c.MobileRspResult;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesAvoidfoodService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetDishesService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.qtsy.CustomBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.DishesSetDishesReplaceService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.sygl.CashDiscountService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.frontdesk.service.xtgl.BusiLogService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesAvoidfood;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishesReplace;
import com.ndlan.canyin.base.entity.cygl.DishesTaste;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.entity.sygl.SpeOpReason;
import com.ndlan.canyin.base.entity.xtgl.BusiLog;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.BussLogActEnum;
import com.ndlan.canyin.core.common.BussLogTypeEnum;
import com.ndlan.canyin.core.common.CreditStatusEnum;
import com.ndlan.canyin.core.common.DishesStatusEnum;
import com.ndlan.canyin.core.common.EnableStatusEnum;
import com.ndlan.canyin.core.common.MemberCardPayTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.PungentLevelEnum;
import com.ndlan.canyin.core.common.SeviceChargeTypeEnum;
import com.ndlan.canyin.core.common.SmtCodeEnum;
import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.web.Servlets;
import com.ndlan.canyin.sharelogic.service.printer.CuicaiPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.PayPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.TuicaiPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.XiadanPrinterService;
import com.ndlan.canyin.sharelogic.util.PriorityExecutor;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 /**
  * 账单
  * @author zhengjiansong
  *
  */
 @Controller
 @RequestMapping({"/mobile/bill"})
 @Lazy
 public class DinerBillController extends BaseManageController
 {
   public static final String MONEY = "MONEY";
   public static final String CARD = "CARD";
   public static final String WEBSITE = "WEBSITE";
   public static final String OTHER = "OTHER";
 
   @Autowired
   private CustomBillService customBillService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private DinerBillDisheService dinerBillDisheService;
 
   @Autowired
   private DisheService dishService;
 
   @Autowired
   private SpeOpReasonService speOpReasonService;
 
   @Autowired
   private DishesAvoidfoodService dishesAvoidfoodService;
 
   @Autowired
   private DishesTasteService dishesTasteService;
 
   @Autowired
   RestaurantService restaurantService;
 
   @Autowired
   BillService billService;
 
   @Autowired
   BusiLogService busiLogService;
 
   @Autowired
   TuicaiPrinterService tuicaiPrinterService;
 
   @Autowired
   XiadanPrinterService xiadanPrinterService;
 
   @Autowired
   CuicaiPrinterService cuicaiPrinterService;
 
   @Autowired
   private PayPrinterService payPrinterService;
 
   @Autowired
   DishesSetDishesService dishesSetDishesService;
 
   @Autowired
   private DisheService disheService;
 
   @Autowired
   private DishesSetService dishesSetService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   private DinerBillPaymentService dinerBillPaymentService;
 
   @Autowired
   private CashDiscountService cashDiscountService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private DinerBillDishesSetService dinerBillDishesSetService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   DishesSetDishesReplaceService dishesSetDishesReplaceService;
 
   @Autowired
   private TableService tableService;
 /**
  * 获取现金折扣
  * @param billId
  * @param pageNumber
  * @param rows
  * @param model
  * @param request
  * @return
  * @throws JSONException
  */
   @RequestMapping(value={"getCashDiscount"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String getCashDiscount(@RequestParam("id") String billId, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="rows", defaultValue="10") int rows, Model model, HttpServletRequest request)
     throws JSONException
   {
     Restaurant restaurant = new Restaurant();
     restaurant.setRestId(getCurrentUserRestId());
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     Employee user = (Employee)this.employeeService.getOne(getCurrentUserId());
     List roleList = user.getRoleList();
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
     boolean isMemeber = dinerBill.getMembershipCard() != null;
     Page<CashDiscount> cashDiscountAll = this.cashDiscountService.search(getCurrentUserRestId(), roleList, isMemeber, searchParams, pageNumber, 7, new String[] { "createTime" });
     model.addAttribute("dinerBill", dinerBill);
     model.addAttribute("cashDiscounts", cashDiscountAll);
     JSONObject resultObj = new JSONObject();
     JSONArray arr = new JSONArray();
 
     for (CashDiscount cashDiscount : cashDiscountAll.getContent()) {
       JSONObject jsonObj = new JSONObject();
       jsonObj.put("ccdId", cashDiscount.getCcdId());
       jsonObj.put("discountName", cashDiscount.getDiscountName());
       SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
       if (cashDiscount.getCreateTime() != null)
         jsonObj.put("createTime", sf.format(cashDiscount.getCreateTime()));
       else {
         jsonObj.put("createTime", "");
       }
 
       jsonObj.put("updateEmployee", cashDiscount.getCreateEmployee() != null ? cashDiscount.getCreateEmployee().getName() : "");
       arr.put(jsonObj);
     }
     resultObj.put("total", cashDiscountAll.getTotalElements());
     resultObj.put("pageSize", rows);
     resultObj.put("hasNextPage", cashDiscountAll.hasNext());
     resultObj.put("pageNumber", pageNumber);
     resultObj.put("dataStr", arr.toString());
     return resultObj.toString();
   }
 /**
  * 账单列表
  * @param pageNumber
  * @param rows
  * @param searchType
  * @param model
  * @param request
  * @return
  * @throws JSONException
  */
   @RequestMapping(value={"list"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String list(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="rows", defaultValue="10") int rows, @RequestParam(value="searchType", defaultValue="") String searchType, Model model, HttpServletRequest request)
     throws JSONException
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, rows, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
     String restId = getCurrentUserRestId();
     Page<DinerBill> p = this.dinerBillService.getPage(pageRequest, restId, searchType);
     JSONObject resultObj = new JSONObject();
     JSONArray arr = new JSONArray();
 
     for (DinerBill bill : p.getContent()) {
       JSONObject jsonObj = new JSONObject();
       jsonObj.put("billId", bill.getBillId());
       jsonObj.put("billNo", bill.getBillNo());
       if (BillTypeEnum.WAIMAI_BILL.getCode().equals(bill.getBillType()))
       {
         jsonObj.put("tabNo", "外卖");
       }
       else if (BillTypeEnum.KUAICAN_BILL.getCode().equals(bill.getBillType()))
       {
         jsonObj.put("tabNo", "快餐");
       }
       else
       {
         jsonObj.put("tabNo", bill.getTabNo());
       }
       jsonObj.put("oriCost", bill.getOriCost());
       long totalCount = this.dinerBillService.getCountByBillId(bill.getBillId()).longValue();
       jsonObj.put("totalDishesCount", totalCount);
       jsonObj.put("billStatus", BillStatusEnum.getDesc(bill.getBillStatus()));
       SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
       if (bill.getCreateTime() != null)
         jsonObj.put("createTime", sf.format(bill.getCreateTime()));
       else {
         jsonObj.put("createTime", "");
       }
       jsonObj.put("updateEmployee", bill.getCreateEmployee() != null ? bill.getCreateEmployee().getName() : "");
       arr.put(jsonObj);
     }
     resultObj.put("total", p.getTotalElements());
     resultObj.put("pageSize", rows);
     resultObj.put("hasNextPage", p.hasNext());
     resultObj.put("pageNumber", pageNumber);
     resultObj.put("dataStr", arr.toString());
     return resultObj.toString();
   }
   
 /**
  * 账单详细
  * @param id
  * @param restId
  * @return
  */
   @RequestMapping(value={"detail"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String showDetail(@RequestParam("id") String id, @RequestParam(value="restId", required=false) String restId)
   {
     JSONObject resultObj = new JSONObject();
     JSONObject billObj = new JSONObject();
     JSONArray dishArr = new JSONArray();
     JSONObject dishObj = new JSONObject();
     DinerBill bill = (DinerBill)this.dinerBillService.getOne(id);
     this.billService.calculator(getCurrentUserRestId(), bill);
     try
     {
    	 
       billObj.put("billId", bill.getBillId());
       billObj.put("billNo", bill.getBillNo());
       billObj.put("billStatus", bill.getBillStatus());
       billObj.put("tabNo", bill.getTabNo());
       billObj.put("billType", bill.getBillTypeDesc());
       billObj.put("avoidfoodNameArray", bill.getAvoidfoodNameArray());
       billObj.put("avoidfoodIdArray", bill.getAvoidfoodIdArray());
       billObj.put("tasteNameArray", bill.getTasteNameArray());
       billObj.put("tasteIdArray", bill.getTasteIdArray());
       billObj.put("pungentLevel", String.valueOf(bill.getPungentLevel()));
       billObj.put("notes", bill.getNotes());
       billObj.put("lastUrgeTime", bill.getLastUrgeTime());
       billObj.put("isSpecialPrice", bill.getTable().getTableArea().getIsSpecialPrice());
       billObj.put("isOnSaleStr", bill.getTable().getTableArea().getIsOnSale());
       billObj.put("oriCost", bill.getOriCost());
 
       int totalDishCount = 0;
       String isOnSaleStr;
       if (bill.getTable() != null) {
         TableArea tableArea = bill.getTable().getTableArea();
         String serviceStr = "";
         if ((tableArea.getSeviceChargeNum() != null) && (StringUtils.isNotEmpty(tableArea.getSeviceChargeType()))) {
           serviceStr = serviceStr + tableArea.getSeviceChargeNum().toString() + SeviceChargeTypeEnum.getAppendix(tableArea.getSeviceChargeType());
         }
         if (tableArea.getConsumeLow() != null) {
           String consumeLowStr = String.valueOf(tableArea.getConsumeLow());
           serviceStr = serviceStr + ",最低消费" + consumeLowStr + "元";
         }
         String isSpecialPrice = "";
         if (StringUtils.isNotEmpty(tableArea.getIsSpecialPrice())) {
           if (TrueFalseEnum.FALSE.getCode().equals(tableArea.getIsSpecialPrice()))
             isSpecialPrice = "不享受特价";
           else {
             isSpecialPrice = "享受特价";
           }
           serviceStr = serviceStr + "," + isSpecialPrice;
         }
         isOnSaleStr = "";
         if (StringUtils.isNotEmpty(tableArea.getIsOnSale())) {
           if (TrueFalseEnum.FALSE.getCode().equals(tableArea.getIsOnSale()))
             isOnSaleStr = "不参与折扣";
           else {
             isOnSaleStr = "参与折扣";
           }
           serviceStr = serviceStr + "," + isOnSaleStr;
         }
         billObj.put("serviceStr", serviceStr);
       } else {
         billObj.put("serviceStr", "");
       }
       if ((bill.getDinerBillDishes().size() > 0) || (bill.getDinerBillDishesSets().size() > 0)) {
         List<DinerBillDishe> dishes = bill.getDinerBillDishes();
         SimpleDateFormat sf = new SimpleDateFormat("yyyy-dd-MM HH:mm");
         for (DinerBillDishe dish : dishes) {
           dishObj = new JSONObject();
           dishObj.put("isSet", TrueFalseEnum.FALSE.getCode());
           dishObj.put("dishesId", dish.getBdId());
           dishObj.put("dishesName", dish.getDishesName());
           dishObj.put("dishesStatus", dish.getDishesStatus());
           dishObj.put("unitNum", dish.getUnitNum());
           dishObj.put("unitName", dish.getUnitName());
           dishObj.put("avoidfoodIdArray", dish.getAvoidfoodIdArray());
           dishObj.put("avoidfoodNameArray", dish.getAvoidfoodNameArray());
           dishObj.put("tasteIdArray", dish.getTasteIdArray());
           dishObj.put("tasteNameArray", dish.getTasteNameArray());
           dishObj.put("fixedTime", dish.getFixedTime());
           dishObj.put("lastUrgeTime", dish.getLastUrgeTime());
           dishObj.put("pungentLevel", PungentLevelEnum.getDesc(String.valueOf(dish.getPungentLevel())));
           dishObj.put("notes", dish.getNotes());
           dishObj.put("price", dish.getUnitPrice());
           dishObj.put("isUserDefined", dish.getIsUserDefined());
           dishObj.put("isRulingPrice", dish.getIsRulingPrice());
           dishObj.put("discountType", dish.getDiscountType());
           dishObj.put("realUnitPrice", dish.getRealUnitPrice());
           dishObj.put("dishSetDishes", "");
           dishObj.put("dishSetId", "");
           dishArr.put(dishObj);
           totalDishCount++;
         }
         List<DinerBillDishesSet> dinerBillDishesSets = this.dinerBillDishesSetService.findByRestIdAndDinerBill(getCurrentUserRestId(), bill);
         for (DinerBillDishesSet dish : dinerBillDishesSets)
         {
           dishObj = new JSONObject();
           dishObj.put("isSet", TrueFalseEnum.TRUE.getCode());
           dishObj.put("dishesId", dish.getBdsId());
           dishObj.put("dishesName", dish.getDsName());
           dishObj.put("dishesStatus", dish.getDsStatus());
           dishObj.put("unitNum", dish.getUnitNum());
           dishObj.put("unitName", dish.getUnitName());
           dishObj.put("avoidfoodIdArray", dish.getAvoidfoodIdArray());
           dishObj.put("avoidfoodNameArray", dish.getAvoidfoodNameArray());
           dishObj.put("tasteIdArray", dish.getTasteIdArray());
           dishObj.put("tasteNameArray", dish.getTasteNameArray());
           dishObj.put("fixedTime", dish.getFixedTime());
           dishObj.put("lastUrgeTime", dish.getLastUrgeTime());
           dishObj.put("pungentLevel", PungentLevelEnum.getDesc(String.valueOf(dish.getPungentLevel())));
           dishObj.put("notes", dish.getNotes());
           dishObj.put("price", dish.getUnitPrice());
           dishObj.put("isUserDefined", dish.getIsUserDefined());
           dishObj.put("isRulingPrice", dish.getIsRulingPrice());
           dishObj.put("discountType", dish.getDiscountType());
           dishObj.put("realUnitPrice", dish.getRealUnitPrice());
           dishObj.put("dishSetDishes", dish.getDsDishesDesc());
           dishObj.put("dishSetId", dish.getDsId());
           dishArr.put(dishObj);
           totalDishCount++;
         }
         billObj.put("dishesStr", dishArr.toString());
         billObj.put("totalDishesCount", String.valueOf(totalDishCount));
       }
 
     }
     catch (JSONException e)
     {
       e.printStackTrace();
     }
     return billObj.toString();
   }
 /**
  * 订单金额
  * @param id
  * @return
  */
   @RequestMapping(value={"getBillCostAndDishesCount"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult getBillCostAndDishesCount(@RequestParam("id") String id)
   {
     DinerBill bill = (DinerBill)this.dinerBillService.getOne(id);
     this.billService.calculator(getCurrentUserRestId(), bill);
     long totalCount = this.dinerBillService.getCountByBillId(id).longValue();
     Map map = new HashMap();
     map.put("totalDishesCount", String.valueOf(totalCount));
     map.put("oriCost", bill.getOriCost().toString());
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获得账单价格和菜肴数量成功", map);
   }
 /**
  * 获取菜肴
  * @param dishesCode
  * @param restId
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"searchdish"}, produces={"text/html;charset=UTF-8"})
   public String findDishByDishesCode(@RequestParam(value="dishesCode", required=true) String dishesCode, @RequestParam(value="restId", required=true) String restId)
   {
     JSONArray resultObj = new JSONArray();
 
     if (StringUtils.isNotEmpty(dishesCode)) {
       List<DinerBillDishe> list = this.dinerBillDisheService.getDinerBillDishesByDish(dishesCode, getCurrentUserRestId());
       for (DinerBillDishe obj : list) {
         JSONObject jsonObj = new JSONObject();
         try {
           jsonObj.put("dishName", obj.getDishesName());
           jsonObj.put("dishesCode", obj.getDishesCode());
           jsonObj.put("dishId", obj.getDishesId());
           resultObj.put(jsonObj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
     return resultObj.toString();
   }
 /**
  * 删除菜肴
  * @param dishId
  * @param billId
  * @param isSet
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"deletedish"}, produces={"text/html;charset=UTF-8"})
   public String deleteDish(@RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="billId", required=true) String billId, @RequestParam(value="isSet", required=true) String isSet)
   {
     if (isSet.equals("1")) {
       if (StringUtils.isNotEmpty(dishId)) {
         try {
           LinkedHashMap map = new LinkedHashMap();
           this.dinerBillService.saveDishSetDelete(getCurrentUserRestId(), billId, dishId, map);
 
           doSynchMultiTable(map);
         }
         catch (Exception e)
         {
           e.printStackTrace();
         }
       }
     }
     else if (StringUtils.isNotEmpty(dishId)) {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveDishDelete(getCurrentUserRestId(), billId, dishId, map);
 
       doSynchMultiTable(map);
     }
 
     return "success";
   }
 /**
  * 催促菜
  * @param dishId
  * @param time
  * @param isSet
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"urgedish"}, produces={"text/html;charset=UTF-8"})
   public String urgeDish(@RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="time", required=true) String time, @RequestParam(value="isSet", required=true) String isSet)
   {
     Date date;
//     Date date;
     if (StringUtils.isNotEmpty(time)) {
       Long dateTime = Long.valueOf(time);
       date = new Date(dateTime.longValue());
     } else {
       date = new Date();
     }
 
     DinerBill dinerBill = null;
     DinerBillDishe dinerBillDishe = null;
 
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       LinkedHashMap map = new LinkedHashMap();
       DinerBillDishesSet dinerBillDishesSet = this.dinerBillService.saveDishsSetCuiCai(getCurrentUserRestId(), dishId, map);
 
       doSynchMultiTable(map);
 
       dinerBillDishe = new DinerBillDishe();
       dinerBill = dinerBillDishesSet.getDinerBill();
       dinerBillDishe.setCreateTime(dinerBillDishesSet.getCreateTime());
       dinerBillDishe.setDishesName(dinerBillDishesSet.getDsName());
     } else {
       LinkedHashMap map = new LinkedHashMap();
       dinerBillDishe = this.dinerBillService.saveDishCuiCai(getCurrentUserRestId(), dishId, map);
 
       doSynchMultiTable(map);
 
       dinerBill = dinerBillDishe.getDinerBill();
     }
     try
     {
       this.cuicaiPrinterService.printCuiCaiOne(getCurrentUserRestId(), dinerBillDishe, dinerBill);
     } catch (PrinterException e) {
       e.printStackTrace();
       return "false";
     }
 
     return "success";
   }
 /**
  * 催促结账？订单？
  * @param billId
  * @param time
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"urgebill"}, produces={"text/html;charset=UTF-8"})
   public String urgebill(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="time", required=true) String time)
   {
     Date date;
//     Date date;
     if (StringUtils.isNotEmpty(time)) {
       Long dateTime = Long.valueOf(time);
       date = new Date(dateTime.longValue());
     } else {
       date = new Date();
     }
     LinkedHashMap map = new LinkedHashMap();
     DinerBill dinerBill = this.dinerBillService.saveCuiCai(billId, map);
     try
     {
       this.cuicaiPrinterService.printCuiCaiAll(getCurrentUserRestId(), dinerBill);
 
       doSynchMultiTable(map);
     }
     catch (PrinterException e) {
       e.printStackTrace();
     }
     return "success";
   }
 /**
  * 退菜
  * @param dishId
  * @param cancelReasonId
  * @param cancelNum
  * @param backornot
  * @param isSet
  * @param newAddReason
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"canceldish"}, produces={"text/html;charset=UTF-8"})
   public String cancelDish(@RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="cancelReasonId", required=true) String cancelReasonId, @RequestParam(value="cancelNum", required=false, defaultValue="1") Float cancelNum, @RequestParam(value="backornot", required=true) boolean backornot, @RequestParam("isSet") String isSet, @RequestParam(value="newAddReason", required=false, defaultValue="") String newAddReason)
   {
     DinerBillDishe dinerBillDishe = new DinerBillDishe();
     Table table = new Table();
     if (TrueFalseEnum.FALSE.getCode().equals(isSet)) {
       dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(dishId);
       if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dinerBillDishe.getDishesStatus())) || 
         (DishesStatusEnum.SERVED_CANCEL.getCode().equals(dinerBillDishe.getDishesStatus())))
       {
         return "false";
       }
       DinerBill dinerBill = dinerBillDishe.getDinerBill();
       table = dinerBill.getTable();
       LinkedHashMap map = new LinkedHashMap();
       if (backornot)
         this.dinerBillService.dishTuicai(getCurrentUserRestId(), dinerBillDishe, cancelReasonId, cancelNum, newAddReason, "1", map);
       else {
         this.dinerBillService.dishTuicai(getCurrentUserRestId(), dinerBillDishe, cancelReasonId, cancelNum, newAddReason, "0", map);
       }
 
       doSynchMultiTable(map);
     }
     else {
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(dishId);
       if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dinerBillDishesSet.getDsStatus())) || 
         (DishesStatusEnum.SERVED_CANCEL.getCode().equals(dinerBillDishesSet.getDsStatus())))
       {
         return "false";
       }
       DinerBill dinerBill = dinerBillDishesSet.getDinerBill();
       table = dinerBill.getTable();
       try
       {
         LinkedHashMap map = new LinkedHashMap();
         if (backornot)
           this.dinerBillService.dishsSetTuicai(getCurrentUserRestId(), dinerBillDishesSet, cancelReasonId, cancelNum, newAddReason, "1", map);
         else {
           this.dinerBillService.dishsSetTuicai(getCurrentUserRestId(), dinerBillDishesSet, cancelReasonId, cancelNum, newAddReason, "0", map);
         }
 
         doSynchMultiTable(map);
       }
       catch (Exception localException1) {
       }
       dinerBillDishe = new DinerBillDishe();
       dinerBillDishe.setOrderTime(dinerBillDishesSet.getOrderTime());
       dinerBillDishe.setDishesName(dinerBillDishesSet.getDsName());
       dinerBillDishe.setUnitNum(dinerBillDishesSet.getUnitNum());
       dinerBillDishe.setUnitName(dinerBillDishesSet.getUnitName());
       dinerBillDishe.setDinerBill(dinerBill);
       dinerBillDishe.setCancelTime(dinerBillDishesSet.getCancelTime());
       dinerBillDishe.setIsSet(isSet);
       List dishesDishesList = new ArrayList();
       ObjectMapper mapper = new ObjectMapper();
       try
       {
         List list = (List)mapper.readValue(dinerBillDishesSet.getDsDishesDesc(), List.class);
         for (int i = 0; i < list.size(); i++) {
           Map map = (Map)list.get(i);
           String dishesId = map.get("dishesId").toString();
           String dishesName = map.get("dishesName").toString();
           String unitNum = map.get("unitNum").toString();
           String unitName = map.get("unitName").toString();
           DinerBillDishe dbdSet = new DinerBillDishe();
           dbdSet.setDishesName(dishesName);
           dbdSet.setUnitNum(Float.valueOf(unitNum).floatValue());
           dbdSet.setUnitName(unitName);
 
           DishesCategory dishesCategory = ((Dishe)this.disheService.getOne(dishesId)).getDishesCategory();
           DishesCategory newDishesCategory = new DishesCategory();
           newDishesCategory.setCategoryId(dishesCategory.getCategoryId());
           dbdSet.setDishesCategory(newDishesCategory);
           dishesDishesList.add(dbdSet);
         }
       } catch (Exception e1) {
         e1.printStackTrace();
       }
       dinerBillDishe.setDishesSetDishesList(dishesDishesList);
     }
     try
     {
       this.tuicaiPrinterService.printTuicai(getCurrentUserRestId(), dinerBillDishe, table, cancelNum.toString());
     } catch (Exception e) {
       e.printStackTrace();
       return "false";
     }
 
     return "success";
   }
 /**
  * 获得所有菜肴信息
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getalldish"}, produces={"text/html;charset=UTF-8"})
   public String getAllDish()
   {
     JSONArray resultObj = new JSONArray();
     JSONObject obj = null;
     List<Dishe> dishes = this.dishService.findAllNotStopSell(getCurrentUserRestId());
     List<DishesSet> dishesSetList = this.dishesSetService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
     if (dishes.size() > 0) {
       for (Dishe dish : dishes) {
         obj = new JSONObject();
         try {
           obj.put("isSet", "false");
           obj.put("categoryId", dish.getDishesCategory() != null ? dish.getDishesCategory().getCategoryId() : "");
           obj.put("dishesId", dish.getDishesId());
           obj.put("price", dish.getPrice());
           obj.put("dishesName", dish.getDishesName());
           obj.put("dishesCode", dish.getDishesCode());
           obj.put("saleNum", dish.getSaleNum());
           obj.put("unitName", dish.getDishesUnit().getName());
           obj.put("estimate", dish.getEstimate());
           resultObj.put(obj);
         }
         catch (JSONException e)
         {
           e.printStackTrace();
         }
       }
     }
     if (dishesSetList.size() > 0) {
       for (DishesSet dish : dishesSetList) {
         obj = new JSONObject();
         try {
           obj.put("isSet", "true");
           obj.put("categoryId", dish.getDishesSetCategory() != null ? dish.getDishesSetCategory().getDsCategoryId() : "");
           obj.put("dishesId", dish.getDsId());
           obj.put("price", dish.getPrice());
           obj.put("dishesName", dish.getDsName());
           obj.put("dishesCode", dish.getDsCode());
           obj.put("saleNum", dish.getSaleNum());
           obj.put("unitName", dish.getDishesUnit().getName());
           obj.put("estimate", dish.getEstimate());
           resultObj.put(obj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
     return resultObj.toString();
   }
   
 /**
  * 获取退菜原因
  * @return
  */
   
   @ResponseBody
   @RequestMapping(value={"gettuicaiyuanyin"}, produces={"text/html;charset=UTF-8"})
   public String getAllCancelDishReason()
   {
     List<SpeOpReason> list = this.speOpReasonService.findByRestIdAndReaType(getCurrentUserRestId(), SpecialReasonTypeEnum.CANCEL_DISH_REASON.getCode());
     JSONArray arr = new JSONArray();
     JSONObject obj = new JSONObject();
     if (list.size() > 0) {
       for (SpeOpReason sor : list) {
         obj = new JSONObject();
         try {
           obj.put("reaId", sor.getReaId());
           obj.put("name", sor.getName());
           arr.put(obj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
     return arr.toString();
   }
 /**
  * 获取撤单原因
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getchedanyuanyin"}, produces={"text/html;charset=UTF-8"})
   public String getAllCancelBillReason()
   {
     List<SpeOpReason> list = this.speOpReasonService.findByRestIdAndReaType(getCurrentUserRestId(), SpecialReasonTypeEnum.CANCEL_BILL_REASON.getCode());
     JSONArray arr = new JSONArray();
     JSONObject obj = new JSONObject();
     if (list.size() > 0) {
       for (SpeOpReason sor : list) {
         obj = new JSONObject();
         try {
           obj.put("reaId", sor.getReaId());
           obj.put("name", sor.getName());
           arr.put(obj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
     return arr.toString();
   }
 /**
  * 加菜
  * @param billId
  * @param dishId
  * @param unitNum
  * @param isJudgeRm
  * @param isRulingPrice
  * @param rulingPrice
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"jiacai"}, produces={"application/json"})
   public MobileRspResult jiacai(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="unitNum", required=true) String unitNum, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="isRulingPrice", defaultValue="0", required=false) String isRulingPrice, @RequestParam(value="rulingPrice", required=false) String rulingPrice)
   {
     float dishesNum = Float.parseFloat(unitNum);
     Map messageMap = null;
     Dishe dishe = null;
 
     Map resultMap = new HashMap();
     boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
     if (("0".equalsIgnoreCase(isJudgeRm)) && (isJudgeDishRaws))
     {
       if (dishe == null)
       {
         dishe = (Dishe)this.disheService.getOne(dishId);
       }
       messageMap = this.dinerBillService.stockCheckForDishes(getCurrentUserRestId(), "", dishe, dishesNum, isJudgeDishRaws);
       String stockResult = (String)messageMap.get("result");
       if ("0".equals(stockResult))
       {
         return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), "原料库存不足，是否继续", messageMap);
       }
       if ("4".equals(stockResult))
       {
         return new MobileRspResult(StatusCodeEnum.LOGIC_ERROR.getCode(), "此菜品数量不足,已沽清", formatFloat((String)messageMap.get("estimate")));
       }
       if ("1".equals(stockResult))
       {
         resultMap.put("stockResult", stockResult);
         resultMap.put("stockMessage", "原料库存预警，请及时补充原料");
       }
       if ("5".equals(stockResult))
       {
         isJudgeDishRaws = false;
         resultMap.put("stockResult", stockResult);
       }
 
     }
 
     LinkedHashMap map = new LinkedHashMap();
     messageMap = this.dinerBillService.saveJiacai(getCurrentUserRestId(), billId, dishId, dishesNum, null, null, null, null, getCurrentUser(), null, isJudgeDishRaws, map);
     String dinerBillDisheId = (String)messageMap.get("dinerBillDisheId");
 
     doSynchMultiTable(map);
 
     long totalCount = this.dinerBillService.getCountByBillId(billId).longValue();
     resultMap.put("dinerBillDisheId", dinerBillDisheId);
     resultMap.put("realCost", (String)messageMap.get("realCost"));
     resultMap.put("totalCount", String.valueOf(totalCount));
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "点菜(" + (String)messageMap.get("dishesName") + ")成功 +" + dishesNum, resultMap);
   }
 
   public String formatFloat(String f) {
     f = f.replaceAll("\\.00", "").replaceAll("\\.0", "");
     return f;
   }
 /**
  * 加菜设置
  * @param billId
  * @param dsId
  * @param isJudgeRm
  * @param dsDishesDesc
  * @param unitNum
  * @param billType
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"jiacaiSet"}, produces={"application/json"})
   public MobileRspResult jiacaiSet(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="dishId", required=true) String dsId, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, @RequestParam(value="unitNum", required=true) String unitNum, @RequestParam(value="billType", defaultValue="") String billType)
   {
     float unitnum = 1.0F;
     if (StringUtils.isNotEmpty(unitNum))
       unitnum = Float.valueOf(unitNum).floatValue();
     try
     {
       Map resultMap = new HashMap();
       Map messageMap = null;
       boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
 
       List<DishesSetDishes> temp = new ArrayList(((DishesSet)this.dishesSetService.getOne(dsId)).getDishesSetDishes());
       JSONArray arr = new JSONArray();
       JSONObject jsonObj = null;
       for (DishesSetDishes dishesSetDishes : temp) {
         jsonObj = new JSONObject();
         try {
           jsonObj.put("dsDishesId", dishesSetDishes.getDsDishesId());
           jsonObj.put("dishesId", dishesSetDishes.getDishe().getDishesId());
           jsonObj.put("dishesName", dishesSetDishes.getDishe().getDishesName());
           jsonObj.put("unitNum", String.valueOf(dishesSetDishes.getUnitNum()));
           jsonObj.put("unitName", dishesSetDishes.getUnitName());
           jsonObj.put("dishesCode", dishesSetDishes.getDishe().getDishesCode());
 
           jsonObj.put("mr_dishesId", dishesSetDishes.getDishe().getDishesId());
           jsonObj.put("mr_dishesName", dishesSetDishes.getDishe().getDishesName());
           jsonObj.put("mr_unitNum", String.valueOf(dishesSetDishes.getUnitNum()));
           jsonObj.put("mr_unitName", dishesSetDishes.getUnitName());
           jsonObj.put("mr_dishesCode", dishesSetDishes.getDishe().getDishesCode());
           arr.put(jsonObj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
 
       dsDishesDesc = arr.toString();
       if (("0".equalsIgnoreCase(isJudgeRm)) && (isJudgeDishRaws)) {
         messageMap = this.dinerBillService.stockCheckForSet(getCurrentUserRestId(), dsId, dsDishesDesc, "");
         String stockResult = (String)messageMap.get("result");
         if ("0".equals(stockResult))
         {
           return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), (String)messageMap.get("message"), messageMap);
         }
         if ("1".equals(stockResult))
         {
           resultMap.put("stockResult", stockResult);
           resultMap.put("stockMessage", (String)messageMap.get("message"));
         }
         if (("5".equals(stockResult)) || ("2".equals(stockResult)))
         {
           isJudgeDishRaws = false;
           resultMap.put("stockResult", stockResult);
         }
 
       }
 
       LinkedHashMap map = new LinkedHashMap();
       LinkedHashMap mapBill = new LinkedHashMap();
 
       messageMap = this.dinerBillService.saveDishesSet(getCurrentUserRestId(), billId, dsId, billType, dsDishesDesc, null, null, null, null, getCurrentUser(), Float.valueOf(unitnum), null, map, mapBill);
 
       doSynchMultiTable(mapBill);
       doSynchMultiTable(map);
 
       String dinerBillDisheId = (String)messageMap.get("dinerBillDisheId");
       resultMap.put("dinerBillDisheId", dinerBillDisheId);
       resultMap.put("realCost", (String)messageMap.get("realCost"));
       long totalCount = this.dinerBillService.getCountByBillId(billId).longValue();
       resultMap.put("totalCount", String.valueOf(totalCount));
       return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "点菜(" + (String)messageMap.get("dishesName") + ")成功 +" + unitnum, resultMap);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return new MobileRspResult(StatusCodeEnum.ERROR.getCode(), "异常", "");
   }
 /**
  * 更新加菜
  * @param billId
  * @param bdId
  * @param unitNum
  * @param isJudgeRm
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"updatejiacai"}, produces={"application/json"})
   public MobileRspResult updatejiacai(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="dishId", required=true) String bdId, @RequestParam(value="unitNum", required=true) String unitNum, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm)
   {
     float dishesNum = Float.parseFloat(unitNum);
     Map messageMap = null;
     Dishe dishe = null;
     DinerBillDishe dinerDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
 
     float addDishesNum = dishesNum - dinerDishe.getUnitNum();
     if (addDishesNum < 0.0F)
     {
       isJudgeRm = "3";
     }
     Map resultMap = new HashMap();
     boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
     if (("0".equalsIgnoreCase(isJudgeRm)) && (isJudgeDishRaws))
     {
       if (dishe == null)
       {
         dishe = (Dishe)this.disheService.getOne(dinerDishe.getDishesId());
       }
       messageMap = this.dinerBillService.stockCheckForDishes(getCurrentUserRestId(), bdId, dishe, dishesNum, isJudgeDishRaws);
       String stockResult = (String)messageMap.get("result");
       if ("0".equals(stockResult))
       {
         return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), "原料库存不足，是否继续", messageMap);
       }
       if ("4".equals(stockResult))
       {
         return new MobileRspResult(StatusCodeEnum.LOGIC_ERROR.getCode(), "此菜品数量不足,已沽清", formatFloat((String)messageMap.get("estimate")));
       }
       if ("1".equals(stockResult))
       {
         resultMap.put("stockResult", stockResult);
         resultMap.put("stockMessage", "原料库存预警，请及时补充原料");
       }
       if ("5".equals(stockResult))
       {
         isJudgeDishRaws = false;
         resultMap.put("stockResult", stockResult);
       }
     }
 
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveDishNumChange(getCurrentUserRestId(), billId, bdId, unitNum, map);
 
     doSynchMultiTable(map);
 
     resultMap.put("dinerBillDisheId", bdId);
     resultMap.put("dishNum", dinerDishe.getUnitNumStr());
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "加菜(" + dinerDishe.getDishesName() + ")成功 +" + dishesNum, resultMap);
   }
 /**
  * 更新菜肴设置为加菜
  * @param billId
  * @param dishId
  * @param unitNum
  * @param isJudgeRm
  * @param oldDishNum
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"updatedishSetjiacai"}, produces={"application/json"})
   public MobileRspResult updatedishSetjiacai(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="unitNum", required=true) String unitNum, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="oldDishNum", defaultValue="1") String oldDishNum)
   {
     Map messageMap = new HashMap();
     Map resultMap = new HashMap();
     float dishesNum = Float.parseFloat(unitNum);
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(dishId);
     float addDishesNum = dishesNum - dinerBillDishesSet.getUnitNum();
     if (addDishesNum < 0.0F)
     {
       isJudgeRm = "3";
     }
     boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
     if (("0".equalsIgnoreCase(isJudgeRm)) && (isJudgeDishRaws)) {
       try {
         messageMap = this.dinerBillService.tcStockUpdateByNumJudge(dishId, getCurrentUserRestId(), oldDishNum, unitNum);
         String stockResult = (String)messageMap.get("result");
         if ("0".equals(stockResult))
         {
           return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), (String)messageMap.get("message"), messageMap);
         }
         if ("1".equals(stockResult))
         {
           resultMap.put("stockResult", stockResult);
           resultMap.put("stockMessage", (String)messageMap.get("message"));
         }
         if (("5".equals(stockResult)) || ("2".equals(stockResult)))
         {
           resultMap.put("stockResult", stockResult);
         }
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     try
     {
       LinkedHashMap map = new LinkedHashMap();
       resultMap = this.dinerBillService.saveDishesSetNumChange(getCurrentUserRestId(), billId, dishId, unitNum, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "加菜(" + (String)resultMap.get("dishName") + ")成功 +" + unitNum, resultMap);
   }
 /**
  * 取消下单
  * @param billId
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"quxiaoxg"}, produces={"text/html;charset=UTF-8"})
	   public String quxiaoXG(@RequestParam(value="billId", required=true) String billId)
   {
     try
     {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.quxiaxiadan(billId, getCurrentUserRestId(), map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return "success";
   }
 /**
  * ???
  * @param dishId
  * @param isSet
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"huacai"}, produces={"text/html;charset=UTF-8"})
   public String huacai(@RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="isSet", required=true) String isSet)
   {
     if (isSet.equals("1")) {
       DinerBillDishesSet dinerBillDisheSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(dishId);
       if (DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDisheSet.getDsStatus()))
       {
         dinerBillDisheSet.setDsStatus(DishesStatusEnum.SERVED.getCode());
         this.dinerBillDishesSetService.save(dinerBillDisheSet);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBillDisheSet);
       }
     }
     else {
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(dishId);
       if (DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishe.getDishesStatus()))
       {
         dinerBillDishe.setDishesStatus(DishesStatusEnum.SERVED.getCode());
         this.dinerBillDisheService.save(dinerBillDishe);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
       }
 
     }
 
     return "success";
   }
 /**
  * 下单
  * @param billId
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"xiadan"}, produces={"text/html;charset=UTF-8"})
   public String xiadan(@RequestParam("billId") String billId)
   {
     DinerBill idinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     if ((idinerBill.getBillStatus().equals(BillStatusEnum.NOT_PLACE_ORDER.getCode())) || 
       (idinerBill.getBillStatus().equals(BillStatusEnum.SOME_PLACE_ORDER.getCode())) || 
       (idinerBill.getBillStatus().equals(BillStatusEnum.RESETTLE.getCode()))) {
       LinkedHashMap map = new LinkedHashMap();
       synchronized (lock) {
         final String restId = getCurrentUserRestId();
         final DinerBill dinerBill = this.dinerBillService.xiadan(restId, billId, map);
         dinerBill.setUpdateEmployee(getCurrentUser());
         dinerBill.setUpdateEmployee(getCurrentUser());
 
         doSynchMultiTable(map);
 
         PriorityExecutor.execPrint(new Runnable()
         {
           public void run() {
             Table table = dinerBill.getTable();
             try {
               HashMap printParaments = new HashMap();
               printParaments.put("isAddDishes", dinerBill.getIsAddDishes());
               printParaments.put("operatorName", dinerBill.getUpdateEmployee().getName());
               DinerBillController.this.xiadanPrinterService.printXiadan(restId, dinerBill, table, printParaments);
             } catch (Exception e) {
               e.printStackTrace();
             }
           }
         });
       }
 
       return "success";
     }
     return "fail";
   }
 /**
  * 撤单
  * @param billId
  * @param reaId
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"chedan"}, produces={"text/html;charset=UTF-8"})
   public String saveChedanDinerBill(@RequestParam("billId") String billId, @RequestParam("reaId") String reaId)
   {
     LinkedHashMap mapSyn = new LinkedHashMap();
     this.dinerBillService.saveChedanDinerBill(billId, getCurrentUserRestId(), reaId, mapSyn);
 
     doSynchMultiTable(mapSyn);
 
     LinkedHashMap map = new LinkedHashMap();
     DinerBill dinerBill = this.dinerBillService.xiadan(getCurrentUserRestId(), billId, map);
 
     doSynchMultiTable(map);
 
     BusiLog log = new BusiLog();
     log.setBillNo(dinerBill.getBillNo());
     log.setTabNo(dinerBill.getTabNo());
     log.setBussLogType(BussLogTypeEnum.TABLE.getCode());
     log.setOpType(BussLogActEnum.CANCEL_BILL.getCode());
     this.busiLogService.save(log);
     return "success";
   }
 /**
  * 获取忌口信息
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getjikou"}, produces={"text/html;charset=UTF-8"})
   public String getDishesAvoidfoods()
   {
     List<DishesAvoidfood> list = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     JSONArray arr = new JSONArray();
     JSONObject obj = new JSONObject();
     if (list.size() > 0) {
       for (DishesAvoidfood dishesAvoidfood : list) {
         obj = new JSONObject();
         if (!dishesAvoidfood.getEnableStatus().equals(EnableStatusEnum.NORMAL.getCode())) continue;
         try {
           obj.put("cdaId", dishesAvoidfood.getCdaId());
           obj.put("name", dishesAvoidfood.getName());
           arr.put(obj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
 
     return arr.toString();
   }
 /**
  * 获取口味信息
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getkouwei"}, produces={"text/html;charset=UTF-8"})
   public String getDishesTastes()
   {
     List<DishesTaste> list = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
     JSONArray arr = new JSONArray();
     JSONObject obj = new JSONObject();
     if (list.size() > 0) {
       for (DishesTaste dishesTaste : list) {
         obj = new JSONObject();
         if (!dishesTaste.getEnableStatus().equals(EnableStatusEnum.NORMAL.getCode())) continue;
         try {
           obj.put("tasteId", dishesTaste.getTasteId());
           obj.put("name", dishesTaste.getName());
           arr.put(obj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
 
     return arr.toString();
   }
/**
 * 保存？？
 * @param type
 * @param id
 * @param dishesTastesIdArr
 * @param dishesTastesNameArr
 * @param dishesAvoidfoodIdArr
 * @param dishesAvoidfoodNameArr
 * @param ladu
 * @param beizhu
 * @param isSet
 * @return
 */
   @ResponseBody
   @RequestMapping(value={"savePrbz"}, produces={"text/html;charset=UTF-8"})
   public String savePrbz(@RequestParam(value="type", required=true) String type, @RequestParam(value="id", required=true) String id, @RequestParam(value="dishesTastesIdArr", required=true) String dishesTastesIdArr, @RequestParam(value="dishesTastesNameArr", required=true) String dishesTastesNameArr, @RequestParam(value="dishesAvoidfoodIdArr", required=true) String dishesAvoidfoodIdArr, @RequestParam(value="dishesAvoidfoodNameArr", required=true) String dishesAvoidfoodNameArr, @RequestParam(value="ladu", required=true) String ladu, @RequestParam(value="beizhu", required=true) String beizhu, @RequestParam(value="isSet", required=true) String isSet)
   {
     if (type.equals("bill")) {
       DinerBill bill = (DinerBill)this.dinerBillService.getOne(id);
       bill.setTasteIdArray(dishesTastesIdArr);
       bill.setTasteNameArray(dishesTastesNameArr);
       bill.setAvoidfoodIdArray(dishesAvoidfoodIdArr);
       bill.setAvoidfoodNameArray(dishesAvoidfoodNameArr);
       bill.setPungentLevel(Integer.parseInt(ladu));
       bill.setNotes(beizhu);
       this.dinerBillService.save(bill);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), bill);
     }
     else if (type.equals("dish")) {
       if (isSet.equals("1")) {
         DinerBillDishesSet dishset = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(id);
         dishset.setTasteIdArray(dishesTastesIdArr);
         dishset.setTasteNameArray(dishesTastesNameArr);
         dishset.setAvoidfoodIdArray(dishesAvoidfoodIdArr);
         dishset.setAvoidfoodNameArray(dishesAvoidfoodNameArr);
         dishset.setPungentLevel(Integer.parseInt(ladu));
         dishset.setNotes(beizhu);
         this.dinerBillDishesSetService.save(dishset);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishset);
       }
       else {
         DinerBillDishe dish = (DinerBillDishe)this.dinerBillDisheService.getOne(id);
         dish.setTasteIdArray(dishesTastesIdArr);
         dish.setTasteNameArray(dishesTastesNameArr);
         dish.setAvoidfoodIdArray(dishesAvoidfoodIdArr);
         dish.setAvoidfoodNameArray(dishesAvoidfoodNameArr);
         dish.setPungentLevel(Integer.parseInt(ladu));
         dish.setNotes(beizhu);
         this.dinerBillDisheService.save(dish);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dish);
       }
 
     }
 
     return "success";
   }
 /**
  * 根据Id获取菜肴
  * @param id
  * @param isSet
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getDishById"}, produces={"text/html;charset=UTF-8"})
   public String getDishById(@RequestParam("id") String id, @RequestParam(value="isSet", required=true, defaultValue="0") String isSet)
   {
     JSONObject dishObj = new JSONObject();
     if (isSet.equals(TrueFalseEnum.TRUE.getCode())) {
       DinerBillDishesSet dish = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(id);
       try {
         dishObj.put("pungentLevel", String.valueOf(dish.getPungentLevel()));
         dishObj.put("avoidfoodIdArray", dish.getAvoidfoodIdArray());
         dishObj.put("avoidfoodNameArray", dish.getAvoidfoodNameArray());
         dishObj.put("tasteIdArray", dish.getTasteIdArray());
         dishObj.put("tasteNameArray", dish.getTasteNameArray());
         dishObj.put("notes", dish.getNotes());
       } catch (JSONException e) {
         e.printStackTrace();
       }
     }
     else
     {
       DinerBillDishe dish = (DinerBillDishe)this.dinerBillDisheService.getOne(id);
       try {
         dishObj.put("pungentLevel", String.valueOf(dish.getPungentLevel()));
         dishObj.put("avoidfoodIdArray", dish.getAvoidfoodIdArray());
         dishObj.put("avoidfoodNameArray", dish.getAvoidfoodNameArray());
         dishObj.put("tasteIdArray", dish.getTasteIdArray());
         dishObj.put("tasteNameArray", dish.getTasteNameArray());
         dishObj.put("notes", dish.getNotes());
       } catch (JSONException e) {
         e.printStackTrace();
       }
     }
 
     return dishObj.toString();
   }
 /**
  * 搜索账单
  * @param pageNumber
  * @param rows
  * @param keyword
  * @param model
  * @param request
  * @return
  * @throws JSONException
  */
   @RequestMapping(value={"searchbill"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String search(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="rows", defaultValue="10") int rows, @RequestParam(value="keyword", defaultValue="") String keyword, Model model, HttpServletRequest request)
     throws JSONException
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, rows, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
 
     Page<DinerBill> p = this.customBillService.searchPage(pageRequest, getCurrentUserRestId(), keyword);
     JSONObject resultObj = new JSONObject();
     JSONArray arr = new JSONArray();
 
     for (DinerBill bill : p.getContent()) {
       JSONObject jsonObj = new JSONObject();
       jsonObj.put("billId", bill.getBillId());
       jsonObj.put("billNo", bill.getBillNo());
       jsonObj.put("tabNo", bill.getTabNo());
       jsonObj.put("billStatus", BillStatusEnum.getDesc(bill.getBillStatus()));
       SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
       if (bill.getCreateTime() != null)
         jsonObj.put("createTime", sf.format(bill.getCreateTime()));
       else {
         jsonObj.put("createTime", "");
       }
       jsonObj.put("updateEmployee", bill.getUpdateEmployee().getName());
       arr.put(jsonObj);
     }
     resultObj.put("total", p.getTotalElements());
     resultObj.put("pageSize", rows);
     resultObj.put("hasNextPage", p.hasNext());
     resultObj.put("pageNumber", pageNumber);
     resultObj.put("dataStr", arr.toString());
     return resultObj.toString();
   }
 /**
  * 展现账单详细
  * @param id
  * @param restId
  * @return
  */
   @RequestMapping(value={"showdetail"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String showBillDetail(@RequestParam("id") String id, @RequestParam(value="restId", required=false) String restId)
   {
     JSONObject resultObj = new JSONObject();
     JSONObject billObj = new JSONObject();
     JSONArray dishArr = new JSONArray();
     JSONObject dishObj = new JSONObject();
     DinerBill bill = (DinerBill)this.dinerBillService.getOne(id);
     this.billService.calculator(getCurrentUserRestId(), bill);
 
     StringBuilder sb = new StringBuilder();
     List dinerBillPayments = bill.getDinerBillPayments();
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       PaymentType paymentType = dinerBillPayment.getPaymentType();
       sb.append(paymentType.getPaymentName());
       sb.append("：");
       sb.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
       if (i != dinerBillPayments.size() - 1) {
         sb.append(",");
       }
     }
     bill.setPayments(sb.toString());
     try
     {
       billObj.put("billId", bill.getBillId());
       billObj.put("billNo", bill.getBillNo());
       billObj.put("billStatus", bill.getBillStatus());
       if (BillTypeEnum.WAIMAI_BILL.getCode().equals(bill.getBillType()))
       {
         billObj.put("tabNo", "外卖");
       }
       else if (BillTypeEnum.KUAICAN_BILL.getCode().equals(bill.getBillType()))
       {
         billObj.put("tabNo", "快餐");
       }
       else
       {
         billObj.put("tabNo", bill.getTabNo());
       }
       billObj.put("billType", bill.getBillTypeDesc());
       billObj.put("avoidfoodNameArray", bill.getAvoidfoodNameArray());
       billObj.put("avoidfoodIdArray", bill.getAvoidfoodIdArray());
       billObj.put("tasteNameArray", bill.getTasteNameArray());
       billObj.put("tasteIdArray", bill.getTasteIdArray());
       billObj.put("pungentLevel", String.valueOf(bill.getPungentLevel()));
       billObj.put("notes", bill.getNotes());
       billObj.put("payments", bill.getPayments());
       billObj.put("oddChange", bill.getOddChange());
 
       billObj.put("oriCost", bill.getOriCost());
 
       billObj.put("serviceChargeMoney", bill.getServiceChargeMoney());
 
       String serviceChargeType = "";
       TableArea ta = null;
       BigDecimal danjia = new BigDecimal(0);
       if ((bill.getTable() != null) && (bill.getTable().getTableArea() != null)) {
         ta = bill.getTable().getTableArea();
         danjia = ta.getSeviceChargeNum();
       }
 
       if (SeviceChargeTypeEnum.SCALE.getCode().equals(bill.getServiceChargeType()))
       {
         serviceChargeType = "( " + danjia + "%" + " )";
       } else if (SeviceChargeTypeEnum.TIME.getCode().equals(bill.getServiceChargeType()))
       {
         serviceChargeType = "( 5分钟/" + danjia + "元)";
       }
       else if (SeviceChargeTypeEnum.FIXED.getCode().equals(bill.getServiceChargeType()))
       {
         serviceChargeType = "( 固定" + danjia + "元 )";
       }
       else if (SeviceChargeTypeEnum.PEOPLE.getCode().equals(bill.getServiceChargeType()))
       {
         serviceChargeType = "(" + danjia + "元/人 )";
       }
       billObj.put("serviceChargeType", serviceChargeType);
 
       billObj.put("consumeCost", bill.getConsumeCost());
 
       if (bill.getMembershipCard() != null)
         billObj.put("cardNo", bill.getMembershipCard().getCardNo());
       else {
         billObj.put("cardNo", "");
       }
 
       billObj.put("discountName", bill.getDiscountName());
 
       billObj.put("dishesTypeDiscountDesc", bill.getDishesTypeDiscountDesc());
 
       billObj.put("saveCost", bill.getSaveCost());
 
       billObj.put("molingModeCost", bill.getMolingModeCost());
 
       billObj.put("rateCost", bill.getRateCost());
 
       billObj.put("payableCost", bill.getPayableCost());
 
       billObj.put("realCost", bill.getRealCost());
       String isSpecialPrice;
       if (bill.getTable() != null) {
         billObj.put("isSpecialPrice", bill.getTable().getTableArea().getIsSpecialPrice());
         billObj.put("isOnSaleStr", bill.getTable().getTableArea().getIsOnSale());
         TableArea tableArea = bill.getTable().getTableArea();
         String serviceStr = "";
         if ((tableArea.getSeviceChargeNum() != null) && (StringUtils.isNotEmpty(tableArea.getSeviceChargeType()))) {
           serviceStr = serviceStr + tableArea.getSeviceChargeNum().toString() + SeviceChargeTypeEnum.getAppendix(tableArea.getSeviceChargeType());
         }
         if (tableArea.getConsumeLow() != null) {
           String consumeLowStr = String.valueOf(tableArea.getConsumeLow());
           serviceStr = serviceStr + ",最低消费" + consumeLowStr + "元";
         }
         isSpecialPrice = "";
         if (StringUtils.isNotEmpty(tableArea.getIsSpecialPrice())) {
           if (TrueFalseEnum.FALSE.getCode().equals(tableArea.getIsSpecialPrice()))
             isSpecialPrice = "不享受特价";
           else {
             isSpecialPrice = "享受特价";
           }
           serviceStr = serviceStr + "," + isSpecialPrice;
         }
         String isOnSaleStr = "";
         if (StringUtils.isNotEmpty(tableArea.getIsOnSale())) {
           if (TrueFalseEnum.FALSE.getCode().equals(tableArea.getIsOnSale()))
             isOnSaleStr = "不参与折扣";
           else {
             isOnSaleStr = "参与折扣";
           }
           serviceStr = serviceStr + "," + isOnSaleStr;
         }
         billObj.put("serviceStr", serviceStr);
       } else {
         billObj.put("serviceStr", "");
         billObj.put("isSpecialPrice", TrueFalseEnum.FALSE.getCode());
         billObj.put("isOnSaleStr", TrueFalseEnum.FALSE.getCode());
       }
       if ((bill.getDinerBillDishes().size() > 0) || (bill.getDinerBillDishesSets().size() > 0)) {
         List<DinerBillDishe> dishes = bill.getDinerBillDishes();
         Dishe dishe;
         for (DinerBillDishe dish : dishes) {
           dishObj = new JSONObject();
           dishObj.put("isSet", TrueFalseEnum.FALSE.getCode());
           dishObj.put("dishesId", dish.getBdId());
           dishObj.put("dishesName", dish.getDishesName());
           dishObj.put("unitNum", dish.getUnitNum());
           dishObj.put("unitName", dish.getUnitName());
           dishObj.put("categoryId", dish.getDishesCategory().getCategoryId());
           dishObj.put("price", dish.getUnitPrice());
           dishObj.put("isUserDefined", dish.getIsUserDefined());
           dishObj.put("isRulingPrice", dish.getIsRulingPrice());
           dishObj.put("discountType", dish.getDiscountType());
           dishObj.put("realUnitPrice", dish.getRealUnitPrice());
           dishObj.put("dishesStatus", dish.getDishesStatus());
           dishObj.put("dishSetId", "");
           dishObj.put("localDishesId", dish.getDishesId());
 
           dishObj.put("avoidfoodNameArray", dish.getAvoidfoodNameArray());
           dishObj.put("tasteNameArray", dish.getTasteNameArray());
           dishObj.put("ladu_value", String.valueOf(dish.getPungentLevel()));
           dishObj.put("billnotes", dish.getNotes());
 
           dishe = (Dishe)this.dishService.getOne(dish.getDishesId());
           dishObj.put("isRecommend", dishe.getIsRecommend());
           dishObj.put("isSpecialty", dishe.getIsSpecialty());
           dishObj.put("memberPrice", dishe.getMemberPrice());
           dishObj.put("pungentLevel", PungentLevelEnum.getDesc(String.valueOf(dish.getPungentLevel())));
 
           dishArr.put(dishObj);
         }
 
         List<DinerBillDishesSet> dinerBillDishesSets = this.dinerBillDishesSetService.findByRestIdAndDinerBill(getCurrentUserRestId(), bill);
         for (DinerBillDishesSet dish : dinerBillDishesSets)
         {
           dishObj = new JSONObject();
           dishObj.put("isSet", TrueFalseEnum.TRUE.getCode());
           dishObj.put("dishesId", dish.getBdsId());
           dishObj.put("dishesName", dish.getDsName());
           dishObj.put("unitNum", dish.getUnitNum());
           dishObj.put("unitName", dish.getUnitName());
           dishObj.put("categoryId", dish.getDishesSetCategory() != null ? dish.getDishesSetCategory().getDsCategoryId() : "");
           dishObj.put("price", dish.getUnitPrice());
           dishObj.put("isUserDefined", dish.getIsUserDefined());
           dishObj.put("isRulingPrice", dish.getIsRulingPrice());
           dishObj.put("discountType", dish.getDiscountType());
           dishObj.put("realUnitPrice", dish.getRealUnitPrice());
           dishObj.put("dishesStatus", dish.getDsStatus());
           dishObj.put("dishSetDishes", dish.getDsDishesDesc());
           dishObj.put("dishSetId", dish.getDsId());
           dishObj.put("localDishesId", dish.getDsId());
 
           dishObj.put("avoidfoodNameArray", dish.getAvoidfoodNameArray());
           dishObj.put("tasteNameArray", dish.getTasteNameArray());
           dishObj.put("ladu_value", String.valueOf(dish.getPungentLevel()));
           dishObj.put("billnotes", dish.getNotes());
 
           DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dish.getDsId());
           dishObj.put("isRecommend", dishesSet.getIsRecommend());
           dishObj.put("isSpecialty", dishesSet.getIsSpecialty());
           dishObj.put("memberPrice", dishesSet.getMemberPrice());
           dishObj.put("pungentLevel", PungentLevelEnum.getDesc(String.valueOf(dish.getPungentLevel())));
           dishArr.put(dishObj);
         }
         billObj.put("dishesStr", dishArr.toString());
       }
 
     }
     catch (JSONException e)
     {
       e.printStackTrace();
     }
     return billObj.toString();
   }
 /**
  * 获取菜肴价格
  * @param model
  * @param request
  * @param id
  * @param isSet
  * @return
  */
   @RequestMapping(value={"getDishEstimate"}, produces={"application/json"})
   @ResponseBody
   public Float getDishEstimate(Model model, ServletRequest request, @RequestParam(value="id", required=false) String id, @RequestParam("isSet") String isSet)
   {
     Float estimate = null;
     if ("false".equals(isSet)) {
       Dishe d = (Dishe)this.dishService.getOne(id);
       estimate = d.getEstimate();
     } else {
       estimate = ((DishesSet)this.dishesSetService.getOne(id)).getEstimate();
     }
 
     return Float.valueOf(estimate != null ? estimate.floatValue() : -1.0F);
   }
 
   private BigDecimal getDishesConsume(DinerBill dinerBill)
   {
     BigDecimal total = dinerBill.getOriCost() == null ? BigDecimal.ZERO : dinerBill.getOriCost();
     for (DinerBillDishe e : dinerBill.getDinerBillDishes()) {
       Dishe dishe = (Dishe)this.disheService.getOne(e.getDishesId());
 
       if ((TrueFalseEnum.TRUE.getCode().equals(dishe.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(e.getDishesStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(e.getDishesStatus()))) {
         total = total.subtract(e.getRealCost());
       }
     }
 
     for (DinerBillDishesSet e : dinerBill.getDinerBillDishesSets()) {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(e.getDsId());
 
       if ((TrueFalseEnum.TRUE.getCode().equals(dishesSet.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(e.getDsStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(e.getDsStatus()))) {
         total = total.subtract(e.getRealCost());
       }
     }
     return total;
   }
 
   private void setPayData(DinerBill dinerBill, Model model)
   {
     if (!BillStatusEnum.SETTLE.getCode().equals(dinerBill.getBillStatus()))
     {
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
     }
 
     StringBuilder sb = new StringBuilder();
     List dinerBillPayments = dinerBill.getDinerBillPayments();
     PaymentType paymentType;
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       paymentType = dinerBillPayment.getPaymentType();
       sb.append(paymentType.getPaymentName());
       sb.append("：");
       sb.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
       if (i != dinerBillPayments.size() - 1) {
         sb.append(",");
       }
     }
     dinerBill.setPayments(sb.toString());
 
     model.addAttribute("dinerBill", dinerBill);
 
     if ((dinerBill.getRestMemberInfo() != null) && (dinerBill.getRestMemberInfo().getCompany() != null)) {
       model.addAttribute("company", dinerBill.getRestMemberInfo().getCompany().split(",")[0]);
     }
 
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     for (PaymentType paymentType1 : paymentTypes)
     {
       List dinerBillPaymentList = this.dinerBillPaymentService.findByRestIdAndDinerBillAndPaymentType(getCurrentUserRestId(), dinerBill, paymentType1);
       if ((dinerBillPaymentList != null) && (dinerBillPaymentList.size() > 0)) {
         paymentType1.setMoney(BigDecimalUtil.format(((DinerBillPayment)dinerBillPaymentList.get(0)).getMoney()));
       }
     }
     model.addAttribute("paymentTypes", paymentTypes);
 
     model.addAttribute("settle", BillStatusEnum.SETTLE.getCode());
   }
 
   @RequestMapping(value={"notes/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveNotes(@PathVariable("id") String billId, @RequestParam(value="notes", required=true) String notes, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("此账单无法操作，或者已经结账了");
       return ajax;
     }
     dinerBill.setBillNotes(notes);
     this.billService.save(dinerBill);
     setPayData(dinerBill, model);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("撤单成功");
     ajax.setRel(dinerBill.getBillId());
     return ajax;
   }
 /**
  * 现金结账？
  * @param ccdId
  * @param billId
  * @param model
  * @return
  */
   @RequestMapping(value={"cashDiscount/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveCashDiscount(@PathVariable("id") String ccdId, @RequestParam(value="billId", required=true) String billId, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       a.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       a.setMessage("此账单无法操作，或者已经结账了");
       return a;
     }
     CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(ccdId);
     dinerBill.setCashDiscount(cashDiscount);
     dinerBill.setDiscountName(cashDiscount.getDiscountName());
     dinerBill.setDiscount(Integer.valueOf(0));
     dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
     this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     return a;
   }
 /**
  * 取消结账？
  * @param ccdId
  * @param billId
  * @param model
  * @return
  */
   @RequestMapping(value={"cancle/cashDiscount/{id}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone cancleCashDiscount(@PathVariable("id") String ccdId, @RequestParam(value="billId", required=true) String billId, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       a.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       a.setMessage("此账单无法操作，或者已经结账了");
       return a;
     }
 
     dinerBill.setCashDiscount(null);
     dinerBill.setDiscountName("");
     dinerBill.setDiscount(Integer.valueOf(0));
     dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
     dinerBill.setOtherDiscount("");
     this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     return a;
   }
 
   private Boolean isMemberCardPay(String paymentType, String money)
   {
     if ((PaymentTypeEnum.MEMBER_CARD.getCode().equalsIgnoreCase(paymentType)) && 
       (StringUtils.isNotEmpty(money))) {
       BigDecimal inputMoney = new BigDecimal(money);
       if (inputMoney.compareTo(BigDecimal.ZERO) == 1) {
         return Boolean.valueOf(true);
       }
     }
 
     return Boolean.valueOf(false);
   }
 
   private String getSubPaymentType(String paymentType)
   {
     if ((PaymentTypeEnum.CASH.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.CARD.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.CHEQUE.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.COUPON.getCode().equalsIgnoreCase(paymentType)))
       return "MONEY";
     if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.TEAM_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.MEMBER_CARD.getCode().equalsIgnoreCase(paymentType)))
       return "CARD";
     if (PaymentTypeEnum.WEB_SITE_MEMBER.getCode().equalsIgnoreCase(paymentType)) {
       return "WEBSITE";
     }
     return "OTHER";
   }
/**
 * 保存支付类型
 * @param billId
 * @param cptId
 * @param dbpId
 * @param mcId
 * @param mbId
 * @param money
 * @param paymentType
 * @param membercardPayType
 * @param model
 * @return
 */
   @RequestMapping(value={"pop/paymentType/update"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public DwzAjaxDone savePaymentType(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="cptId", required=true) String cptId, @RequestParam(value="dbpId", required=false, defaultValue="") String dbpId, @RequestParam(value="mcId", required=false) String mcId, @RequestParam(value="mbId", required=false) String mbId, @RequestParam(value="money", required=false) String money, @RequestParam(value="paymentType", required=true) String paymentType, @RequestParam(value="membercardPayType", required=false) String membercardPayType, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       a.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       a.setMessage("此账单无法操作，或者已经结账了");
       return a;
     }
 
     if (MemberCardPayTypeEnum.CONSUME.getCode().equals(membercardPayType)) {
       MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
       BigDecimal inputMoney = new BigDecimal(money);
       BigDecimal balance = membershipCard.getBalance();
 
       if (inputMoney.compareTo(balance) == 1) {
         a.setRel("false");
         a.setMessage("会员卡余额不足，请使用现金或充值");
         return a;
       }
     }
 
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     PaymentType paymentype = null;
     PaymentType paymentype1 = null;
     PaymentType paymentype7 = null;
     for (PaymentType paymtType : paymentTypes)
     {
       if (paymtType.getPaymentName().equals("现金")) {
         paymentype1 = paymtType;
         break;
       }
 
     }
 
     for (PaymentType paymtType : paymentTypes)
     {
       if (paymtType.getPaymentName().equals("优惠券")) {
         paymentype7 = paymtType;
         break;
       }
     }
 
     if (paymentType.equals("1")) paymentype = paymentype1; else if (paymentType.equals("7")) paymentype = paymentype7;
 
     cptId = paymentype.getCptId();
     DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, getCurrentUserRestId(), paymentype);
     if (dinerBillPayment != null)
       dbpId = dinerBillPayment.getDbpId();
     else {
       dbpId = "";
     }
 
     String subType = getSubPaymentType(paymentType);
 
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillPaymentService.savePaymentsByMobile(subType, getCurrentUserRestId(), billId, mcId, mbId, dbpId, cptId, money, map);
 
     doSynchMultiTable(map);
 
     a.setMessage("支付成功");
     return a;
   }
 /**
  * 保存现金支付
  * @param billId
  * @param money
  * @return
  */
   @RequestMapping(value={"saveMoneyPayment/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveMoneyPayment(@PathVariable("id") String billId, @RequestParam(value="money", required=true) String money)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("此账单无法结账，或者已经结账了");
       ajax.setRel("2");
       return ajax;
     }
 
     Table table = dinerBill.getTable();
     if ((table != null) && (table.getTableArea().getConsumeLow() != null) && 
       (getDishesConsume(dinerBill).floatValue() < table.getTableArea().getConsumeLow().floatValue())) {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("不满足最低消费，不能结账");
       ajax.setRel("2");
       return ajax;
     }
 
     DinerBillPayment dinerBillPayment = new DinerBillPayment();
     PaymentType pType = this.paymentTypeService.findeByRestIdAndPaymentType(getCurrentUserRestId(), PaymentTypeEnum.CASH.getCode());
     dinerBillPayment.setDinerBill(dinerBill);
     dinerBillPayment.setPaymentType(pType);
     dinerBillPayment.setBillNo(dinerBill.getBillNo());
     dinerBillPayment.setRestId(getCurrentUserRestId());
     dinerBillPayment.setPayTime(new Date());
     dinerBillPayment.setMoney(new BigDecimal(money));
     if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equals(pType.getPaymentType())) || 
       (PaymentTypeEnum.TEAM_CREDIT.getCode().equals(pType.getPaymentType())) || 
       (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equals(pType.getPaymentType()))) {
       dinerBillPayment.setCreditStatus(CreditStatusEnum.CREDIT_BILL.getCode());
     }
     this.dinerBillPaymentService.save(dinerBillPayment);
 
     doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), dinerBillPayment);
 
     ajax.setRel(TrueFalseEnum.TRUE.getCode());
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 /**
  * 预结账
  * @param billId
  * @param model
  * @param redirectAttributes
  * @return
  */
   @RequestMapping(value={"predict/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone predict(@PathVariable("id") String billId, Model model, RedirectAttributes redirectAttributes)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("此账单无法结账，或者已经结账了");
       ajax.setRel("2");
       return ajax;
     }
 
     this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
     boolean isPrint = false;
     try {
       HashMap printParaments = new HashMap();
       printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId());
       isPrint = this.payPrinterService.printPay(getCurrentUserRestId(), dinerBill, dinerBill.getTable(), "PREDICT", "2", printParaments);
       if (!isPrint)
         ajax.setMessage("打印失败，没有找到匹配的打印机");
     }
     catch (PrinterException e) {
       e.printStackTrace();
       ajax.setMessage("打印失败");
     }
     ajax.setMessage("预结成功!");
     return ajax;
   }
 /**
  * 结账支付
  * @param billId
  * @param isPrint
  * @param isForce
  * @return
  */
   @RequestMapping(value={"pay"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone pay(@RequestParam(value="billId", required=false) String billId, @RequestParam(value="isPrint", required=true) String isPrint, @RequestParam(value="isForce", required=true) String isForce)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     final DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("此账单无法结账，或者已经结账了");
       ajax.setRel("2");
       return ajax;
     }
 
     ajax.setType(UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getIsPayEnterDesk());
 
     Table table = dinerBill.getTable();
     if ((!"true".equalsIgnoreCase(isForce)) && 
       (table != null) && (table.getTableArea().getConsumeLow() != null) && 
       (getDishesConsume(dinerBill).floatValue() < table.getTableArea().getConsumeLow().floatValue())) {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       ajax.setMessage("不满足最低消费，不能结账");
       ajax.setRel("2");
       return ajax;
     }
 
     String restId = getCurrentUserRestId();
     MembershipCard membershipCard = dinerBill.getMembershipCard();
     if (membershipCard != null) {
       PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.MEMBER_CARD.getCode());
       DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, restId, paymentType);
       if (dinerBillPayment != null)
       {
         if (dinerBillPayment.getMoney().compareTo(membershipCard.getBalance()) == 1) {
           ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           ajax.setMessage("会员卡余额不足，不能结账");
           ajax.setRel("3");
           return ajax;
         }
       }
     }
 
     this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
     if ("false".equalsIgnoreCase(isForce))
     {
       if (dinerBill.getPayableCost().compareTo(dinerBill.getRealCost()) != 0) {
         ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
         ajax.setMessage("付款金额不足，不能结账");
         ajax.setRel("1");
         return ajax;
       }
     }
     LinkedHashMap map = new LinkedHashMap();
     ArrayList cloudMethodParams = new ArrayList();
 
     this.billService.savePay(getCurrentUser(), dinerBill, membershipCard, getCurrentUserRestId(), TrueFalseEnum.FALSE.getCode(), map, cloudMethodParams);
 
     doSynchMultiTable(map);
 
     doCallCloudMethod(SmtCodeEnum.CONSUM, cloudMethodParams);
 
     final String innerRestId = getCurrentUserRestId();
     final String innerIsPrint = isPrint;
     final String userId = getCurrentUserId();
 
     PriorityExecutor.execPrint(new Runnable()
     {
       public void run() {
         Table table = dinerBill.getTable();
 
         if (("true".equalsIgnoreCase(innerIsPrint)) && (dinerBill.getBillType().equals(BillTypeEnum.KUAICAN_BILL.getCode()))) {
           try {
             HashMap printParaments_kuaican = new HashMap();
             printParaments_kuaican.put("operatorName", DinerBillController.this.getCurrentUser().getName());
             DinerBillController.this.xiadanPrinterService.printXiadan(innerRestId, dinerBill, table, printParaments_kuaican);
           } catch (Exception e) {
             System.out.println("结账成功，打印失败");
           }
 
         }
 
         boolean isPrinted = false;
         if ("true".equalsIgnoreCase(innerIsPrint))
           try {
             HashMap printParaments = new HashMap();
             printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(userId).getPrinterId());
             isPrinted = DinerBillController.this.payPrinterService.printPay(innerRestId, dinerBill, table, "PAY", "2", printParaments);
             if (!isPrinted)
             {
               System.out.println("打印失败，没有找到匹配的打印机");
             }
           }
           catch (PrinterException e)
           {
             System.out.println("打印失败");
           }
       }
     });
     ajax.setRel("0");
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 /**
  * 获取餐台的预定
  * @param sortType
  * @param pageNumber
  * @param model
  * @param request
  * @return
  */
   @RequestMapping(value={"getTableOrderList"}, produces={"application/json"})
   @ResponseBody
   public List<TableOrder> getTableOrderList(@RequestParam(value="sortType", defaultValue="auto") String sortType, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, ServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     String kewWords = request.getParameter("kewWords");
 
     Page<TableOrder> tableOrders = this.tableOrderService.findByKeywords(getCurrentUserRestId(), searchParams, kewWords, pageNumber, 30, "desc", "updateTime");
     for (TableOrder each : tableOrders)
     {
       if ((each.getOrderStatus().equals(OrderStatusEnum.WAIT_COMMIT.getCode())) || 
         (each.getOrderStatus().equals(OrderStatusEnum.APPLING.getCode())))
       {
         Date orderTime = each.getOrderTime();
         Date now = DateProvider.DEFAULT.getDate();
         if ((orderTime != null) && (orderTime.before(now)))
         {
           each.setOrderStatus(OrderStatusEnum.EXPIRE.getCode());
         }
       }
       Table table = each.getTable();
       if (table != null) {
         ArrayList tableList = new ArrayList();
         tableList.add(table);
         this.tableService.flushCurrentTable(tableList);
         each.setTableStatus(((Table)this.tableService.getOne(each.getTabId())).getOrderStatus());
       }
     }
 
     return tableOrders.getContent();
   }
 /**
  * 改变餐台信息状态
  * @param tableOrder
  * @param model
  * @param redirectAttributes
  * @return
  */
   @RequestMapping(value={"changeTableOrderStatus"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public boolean changeTableOrderStatus(@RequestBody(required=true) TableOrder tableOrder, Model model, RedirectAttributes redirectAttributes)
   {
     Table table = new Table();
     table.setTabId(tableOrder.getClientTabId());
     tableOrder.setTable(table);
     TableOrder tableorder = (TableOrder)this.tableOrderService.save(tableOrder);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), tableorder);
 
     return tableorder != null;
   }
 /**
  * 替换套餐内容
  * @param dsId
  * @param dsDishesId
  * @param model
  * @param request
  * @return
  */
   @RequestMapping(value={"replaceDishesContent"}, produces={"application/json"})
   @ResponseBody
   public List<Dishe> replaceDishesContent(@RequestParam(value="dsId", required=true) String dsId, @RequestParam(value="dsDishesId", required=true) String dsDishesId, Model model, HttpServletRequest request)
   {
     DishesSetDishes dishesSetDishes = (DishesSetDishes)this.dishesSetDishesService.getOne(dsDishesId);
     List<DishesSetDishesReplace> dishesSetDishesReplaces = this.dishesSetDishesReplaceService.findByRestIdAndDsIdAndDishesSetDishes(getCurrentUserRestId(), dsId, dishesSetDishes);
     List datas = new ArrayList();
     Dishe dishe = null;
     for (DishesSetDishesReplace dsDishes : dishesSetDishesReplaces) {
       dishe = new Dishe();
       dishe.setDishesId(dsDishes.getReplaceDishe().getDishesId());
       dishe.setDishesName(dsDishes.getDishesName());
       dishe.setDsUnitNum(dsDishes.getUnitNum());
       dishe.setDishesUnitName(dsDishes.getUnitName());
       dishe.setDishesCode(dsDishes.getReplaceDishe().getDishesCode());
       datas.add(dishe);
     }
     model.addAttribute("dishesSetDishesReplaces", dishesSetDishesReplaces);
     model.addAttribute("dishesSetDishes", dishesSetDishes);
     return datas;
   }
 /**
  * 
  * @param dsId
  * @param model
  * @param request
  * @return
  */
   @RequestMapping(value={"findByRestIdAndDishesSet"}, produces={"application/json"})
   @ResponseBody
   public List<Dishe> findByRestIdAndDishesSet(@RequestParam(value="dsId", required=true) String dsId, Model model, HttpServletRequest request)
   {
     DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dsId);
     List<DishesSetDishes> data = null;
     List datas = new ArrayList();
     Dishe dishe = null;
     String restId = getCurrentUserRestId();
     if (dishesSet != null) {
       data = this.dishesSetDishesService.findByRestIdAndDishesSet(restId, dishesSet);
       for (DishesSetDishes dishset : data) {
         dishe = new Dishe();
         dishe.setDishesId(dishset.getDishe().getDishesId());
         dishe.setDishesName(dishset.getDishesName());
         dishe.setDsDishesId(dishset.getDsDishesId());
         dishe.setDsUnitNum(dishset.getUnitNum());
         dishe.setDishesUnitName(dishset.getUnitName());
         dishe.setDishesCode(dishset.getDishe().getDishesCode());
         datas.add(dishe);
       }
     }
     return datas;
   }
/**
 * 编辑套餐替换菜品
 * @param dishesId
 * @param dsDishesDesc
 * @param model
 * @param request
 * @return
 * @throws JsonProcessingException
 */
   @RequestMapping(value={"editDishesSetReplace"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String editDishesSetReplace(@RequestParam(value="dishesId", required=true) String dishesId, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, Model model, HttpServletRequest request)
     throws JsonProcessingException
   {
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(dishesId);
     LinkedHashMap map = new LinkedHashMap();
 
     Map messageMap = this.dinerBillService.dishSetSaveAndUpdateStock(dishesId, getCurrentUserRestId(), dsDishesDesc, dinerBillDishesSet.getDsDishesDesc(), map);
 
     doSynchMultiTable(map);
 
     return "success";
   }
 
   public static String beanToJson(Object obj) {
     String json = null;
     try {
       ObjectMapper mapper = new ObjectMapper();
       StringWriter writer = new StringWriter();
       JsonGenerator generator = new JsonFactory().createJsonGenerator(writer);
       mapper.writeValue(generator, obj);
       generator.close();
       json = writer.toString();
       writer.close();
     }
     catch (JsonGenerationException e) {
       e.printStackTrace();
     }
     catch (JsonMappingException e) {
       e.printStackTrace();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     return json;
   }
 /**
  * 获取账单状态
  * @param billId
  * @return
  */
   @RequestMapping(value={"getBillStatus"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String getBillStatus(@RequestParam(value="billId", required=true) String billId) {
     String billStatus = "";
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     billStatus = dinerBill.getBillStatus();
     return billStatus;
   }
 }

