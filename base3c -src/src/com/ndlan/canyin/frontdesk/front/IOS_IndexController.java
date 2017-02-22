 package com.ndlan.canyin.frontdesk.front;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.BussLogTypeEnum;
import com.ndlan.canyin.core.common.DinnerStatusEnum;
import com.ndlan.canyin.core.common.DishesStatusEnum;
import com.ndlan.canyin.core.common.EnableStatusEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PrinterTypeEnum;
import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TabBillTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.web.Servlets;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.OrderBillDishesService;
import com.ndlan.canyin.frontdesk.service.qtsy.OrderBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.frontdesk.service.xtgl.SysLogService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.sharelogic.service.printer.CuicaiPrinterService;
import com.ndlan.canyin.sharelogic.util.PriorityExecutor;
import com.tencent.common.ThirdPartyPaymentUtil;

import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 @RequestMapping({"/IOS_index"})
 @Lazy
 public class IOS_IndexController extends BaseManageController
 {
 
   @Autowired
   private BillService billService;
	 
   @Autowired
   TableAreaService tableAreaService;
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private SpeOpReasonService speOpReasonService;
 
   @Autowired
   private PrinterService printerService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   RestaurantService restaurantService;
 
   @Autowired
   CuicaiPrinterService cuicaiPrinterService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @Autowired
   OrderBillDishesService orderBillDishesService;
 
   @Autowired
   OrderBillDishesSetService orderBillDishesSetService;
 
   @Autowired
   TableOrderService tableOrderService;
   
   @Autowired
   protected SysLogService sysLogService;
 
   @RequestMapping({""})
   public String list(@RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request)
   {
     String nextPage = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getUserFirstPage();
     if ("index".equals(nextPage))
     {
       return "index/index";
     }
 
     return "redirect:/" + nextPage;
   }
 
   @RequestMapping({"ajax/table/list"})
   public String tableList(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="200") int pagzSize, Model model,String orderTime, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
         
     List tableAreas = this.tableAreaService.loadTableAreaByRestID(getCurrentUserRestId());
 
     String isHasAllArea = TrueFalseEnum.FALSE.getCode();
     Employee employee = (Employee)this.employeeService.loadOne(getCurrentUserId());
     List<Role> roles = employee.getRoleList();
     List roleTableAreas = new ArrayList();
     for (Role r : roles)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(r.getIsAllTablearea())) {
         roleTableAreas = tableAreas;
         isHasAllArea = TrueFalseEnum.TRUE.getCode();
         break;
       }
       if (!TrueFalseEnum.FALSE.getCode().equals(r.getIsAllTablearea()))
         continue;
       roleTableAreas.addAll(r.getTableAreaList());
     }
     Page tables = this.tableService.searchBySql(getCurrentUserRestId(), null, searchParams, pageNumber, pagzSize, null, new String[] { "tabNo" },orderTime);
 
     calTableOrderStatus(tables.getContent());
 
     model.addAttribute("roleTableAreas", roleTableAreas);
     model.addAttribute("isHasAllArea", isHasAllArea);
 
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
 
     searchParams.remove("EQ_dinnerStatus");
     model.addAttribute("searchNostatusParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     List<Table> tabl=tables.getContent();
     for (Table table : tabl)
     {
       if(table.getBillId()!=null){
    	  DinerBill d= dinerBillService.getOne(table.getBillId());
    	  billService.calculator(getCurrentUserRestId(), d);
    	  BigDecimal str= dinerBillService.getOne(table.getBillId()).getOriCost(); //.setScale(2,   BigDecimal.ROUND_HALF_UP)
    	  BigDecimal ss= str.setScale(2,   BigDecimal.ROUND_HALF_UP); 
    	 String dd= ss.toString();
    	  table.setTotalPrice(dd);
       }
     }
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("tables", tables);
     return "index/indexTableContent"; } 
   @RequestMapping(value={"ajax/getNoWaimaiAndResettle"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getNoWaimaiAndResettle(RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
 
     Restaurant restaurant = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
     List billStatusList = new ArrayList();
     billStatusList.add(BillStatusEnum.NOT_PLACE_ORDER.getCode());
     billStatusList.add(BillStatusEnum.PLACE_ORDER.getCode());
     billStatusList.add(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     List noPayWaimaiSize = this.dinerBillService.findByRestaurantAndBillTypeAndBillStatusIn(restaurant, BillTypeEnum.WAIMAI_BILL.getCode(), billStatusList);
 
     List resettlebills = this.dinerBillService.findByRestaurantAndBillStatus(restaurant, BillStatusEnum.RESETTLE.getCode());
     if (noPayWaimaiSize != null) {
       ajax.setValue(String.valueOf(noPayWaimaiSize.size()));
     }
 
     if (resettlebills != null) {
       ajax.setRel(String.valueOf(resettlebills.size()));
     }
     return ajax;
   }
 
   private void calTableOrderStatus(List<Table> tables)
   {
     this.tableService.flushCurrentTable(tables);
   }
 
   @RequestMapping(value={"waimai/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String waimaiForm(Model model)
   {
     return "redirect:/bill/diancai?billType=" + BillTypeEnum.WAIMAI_BILL.getCode();
   }
 
   @RequestMapping(value={"pop/kaitai/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String createForm(Model model, @RequestParam(value="tabId", defaultValue="") String tabId) {
     DinerBill d = new DinerBill();
     d.setBillTime(new Date());
     d.setCreateEmployee(getCurrentUser());
     if ((tabId != null) && (!tabId.isEmpty()))
     {
       Table t = (Table)this.tableService.getOne(tabId);
       d.setTable(t);
       d.setTabNo(t.getTabNo());
       if (t.getWaiter() != null)
       {
         d.setWaiterId(t.getWaiter().getEmpId());
         d.setWaiterName(t.getWaiter().getName());
 
         d.setSalesmanId(t.getWaiter().getEmpId());
         d.setSalesmanName(t.getWaiter().getName());
       }
     }
 
     if (StringUtils.isEmpty(d.getSalesmanId()))
     {
       d.setSalesmanId(getCurrentUserId());
       d.setSalesmanName(getCurrentUser().getName());
     }
 
     d.setIsEnterDiancaiPage(UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getIsStartEnterOrder());
     model.addAttribute("dinerBill", d);
     model.addAttribute("action", "create");
     return "index/kaitaiForm";
   }
 
   /**
    * iOS下单调用
    * @param dinerBill
    * @param orderId
    * @param tableNo
    * @param isJudgeRm
    * @param redirectAttributes
    * @return
    */
   @RequestMapping(value={"kaitai/create"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone create(@Valid @ModelAttribute("dinerBill") DinerBill dinerBill, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="tableNo", required=false) String tableNo, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, RedirectAttributes redirectAttributes)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     String restId = getCurrentUserRestId();
     List tableList = new ArrayList();
     Table tab = this.tableService.findByTabNoAndRestIdAndIsEnable(tableNo, restId);
     if (tab == null) {
       a.setMessage("2");
       return a;
     }
     tableList.add(tab);
     this.tableService.flushCurrentTable(tableList);
     tab = (Table)tableList.get(0);
     if ((TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(tab.getIsHasTableOrder())) && 
       (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(tab.getIsAtOrderWarnTime())) && 
       (StringUtils.isEmpty(orderId))) {
       a.setMessage("6");
       return a;
     }
 
     if (!DinnerStatusEnum.IDLE.getCode().equalsIgnoreCase(tab.getDinnerStatus())) {
       a.setMessage("7");
       return a;
     }
 
     if (dinerBill.getTableBillRelation() != null)
     {
       a.setForwardUrl(dinerBill.getBillId());
       a.setBillId(dinerBill.getBillId());
       a.setMessage(TrueFalseEnum.FALSE.getCode());
       a.setRel(dinerBill.getIsEnterDiancaiPage() == null ? TrueFalseEnum.FALSE.getCode() : TrueFalseEnum.TRUE.getCode());
       a.setNavTabId(orderId);
       return a;
     }
 
     LinkedHashMap map = new LinkedHashMap();
 
     if (((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabId()))) || ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabNo()))))
     {
       Table table = null;
       if (StringUtils.isNotEmpty(dinerBill.getTable().getTabId()))
         table = (Table)this.tableService.getOne(dinerBill.getTable().getTabId());
       else if (StringUtils.isNotEmpty(dinerBill.getTable().getTabNo())) {
         table = this.tableService.findByTabNoAndRestIdAndIsEnable(dinerBill.getTable().getTabNo(), getCurrentUserRestId());
       }
 
       if (table == null)
       {
         a.setMessage("2");
         return a;
       }
 
       List<TableBillRelation> rs = this.tableBillRelationService.findByTableAndTabBillType(table, TabBillTypeEnum.ORDER.getCode());
       if ((rs != null) && (rs.size() >= 1))
       {
         if (table.getDinnerStatus().equals(DinnerStatusEnum.IDLE.getCode()))
         {
           for (TableBillRelation each : rs)
           {
             String id = each.getTabBillId();
             this.tableBillRelationService.delete(id);
             map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + id);
           }
         }
         else
         {
           a.setForwardUrl(dinerBill.getBillId());
           a.setBillId(dinerBill.getBillId());
           a.setMessage(TrueFalseEnum.FALSE.getCode());
           a.setRel(dinerBill.getIsEnterDiancaiPage() == null ? TrueFalseEnum.FALSE.getCode() : TrueFalseEnum.TRUE.getCode());
           a.setNavTabId(orderId);
           return a;
         }
 
       }
 
     }
 
     if (("0".equalsIgnoreCase(isJudgeRm)) && (StringUtils.isNotEmpty(orderId))) {
       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
       List orderBillDishes = this.orderBillDishesService.findByRestIdAndTableOrder(restId, tableOrder);
       List orderBillDishesSets = this.orderBillDishesSetService.findByRestIdAndTableOrder(restId, tableOrder);
       try {
         Map result = this.orderBillDishesSetService.scheduleStockCheck(restId, orderBillDishes, orderBillDishesSets);
         if ((((String)result.get("result")).equals("0")) || (((String)result.get("result")).equals("1")) || (((String)result.get("result")).equals("4"))) {
           a.setMessage("8");
           a.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
           a.setMessageMap(result);
           return a;
         }
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     boolean isOk=false;
     if(dinerBill.getBillId()==null || dinerBill.getBillId()=="")
     isOk = this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), dinerBill, orderId, null, map);
     else
     isOk = this.dinerBillService.updateCreateTableDinerBill(getCurrentUserRestId(), dinerBill, orderId, null, map);
     a.setForwardUrl(dinerBill.getBillId());
     a.setMessage(isOk ? TrueFalseEnum.TRUE.getCode() : TrueFalseEnum.FALSE.getCode());
     a.setRel(dinerBill.getIsEnterDiancaiPage());
     a.setNavTabId(orderId);
     a.setValue(dinerBill.getBillNo());
     doSynchMultiTable(map);
 
     final DinerBill f_dinerBill = dinerBill;
     PriorityExecutor.execLog(new Runnable( )
     {
       public void run() {
         String logNotes = "开台操作";
         if (f_dinerBill.getTable() != null)
         {
           logNotes = logNotes + "，桌号:" + f_dinerBill.getTable().getTabNo();
         }
         if (f_dinerBill.getPeopleNum() != null)
         {
           logNotes = logNotes + "，人数:" + f_dinerBill.getPeopleNum();
         }
         if (BillTypeEnum.WAIMAI_BILL.getCode().equals(f_dinerBill.getBillType()))
         {
           logNotes = logNotes + "，外卖单";
         }
 
         IOS_IndexController.this.dinerBillService.saveBusiLog(IOS_IndexController.this.getCurrentUserRestId(), f_dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.START_TABLE, logNotes);
       }
     });
     return a;
   }
 
   @RequestMapping(value={"pop/kaitai/update/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String updateForm(@PathVariable("id") String id, Model model)
   {
     Table t = (Table)this.tableService.loadOne(id);
     TableBillRelation lastedTableNormalBillRelation = this.tableService.getLastedTableNormalBillRelation(t);
     if (lastedTableNormalBillRelation != null)
     {
       model.addAttribute("dinerBill", lastedTableNormalBillRelation.getDinerBill());
     }
     model.addAttribute("action", "update");
     return "index/kaitaiForm";
   }
 
   @RequestMapping(value={"kaitai/update"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone update(@Valid @ModelAttribute("dinerBill") DinerBill dinerBill, @RequestParam(value="tableNo", required=true) String tableNo, RedirectAttributes redirectAttributes) {
     DwzAjaxDone a = new DwzAjaxDone();
     String restId = getCurrentUserRestId();
 
     if (dinerBill == null) {
       a.setMessage("11");
       return a;
     }
 
     List tableList = new ArrayList();
     Table tab = this.tableService.findByTabNoAndRestIdAndIsEnable(tableNo, restId);
     if (tab == null) {
       a.setMessage("2");
       return a;
     }
 
     if (!tableNo.equalsIgnoreCase(dinerBill.getTabNo())) {
       tableList.add(tab);
       this.tableService.flushCurrentTable(tableList);
       tab = (Table)tableList.get(0);
       if ((TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(tab.getIsHasTableOrder())) && 
         (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(tab.getIsAtOrderWarnTime()))) {
         a.setMessage("6");
         return a;
       }
 
       if (!DinnerStatusEnum.IDLE.getCode().equalsIgnoreCase(tab.getDinnerStatus())) {
         a.setMessage("7");
         return a;
       }
     }
     LinkedHashMap map = new LinkedHashMap();
     String status = this.dinerBillService.saveTableDinerBill(dinerBill, getCurrentUserRestId(), map);
 
     doSynchMultiTable(map);
 
     a.setForwardUrl(dinerBill.getBillId());
     a.setMessage(status);
     a.setRel(dinerBill.getIsEnterDiancaiPage());
     return a;
   }
 
   @RequestMapping({"pop/zhuantai/{id}"})
   public String zhuantai(@PathVariable("id") String id, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="left", defaultValue="0") int left, Model model, HttpServletRequest request)
   {
     Table t = (Table)this.tableService.loadOne(id);
     TableBillRelation lastedTableNormalBillRelation = this.tableService.getLastedTableNormalBillRelation(t);
     if (lastedTableNormalBillRelation != null)
     {
       model.addAttribute("dinerBill", lastedTableNormalBillRelation.getDinerBill());
     }
 
     List tableAreas = this.tableAreaService.loadTableAreaByRestID(getCurrentUserRestId());
 
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_dinnerStatus", DinnerStatusEnum.IDLE.getCode());
     Page tables = this.tableService.searchBySql(getCurrentUserRestId(), null, searchParams, pageNumber, 28, null, new String[] { "tabNo" },null);
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
 
     searchParams.remove("EQ_dinnerStatus");
     model.addAttribute("searchNostatusParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
 
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("tables", tables);
     model.addAttribute("left", Integer.valueOf(left));
     return "index/zhuantaiForm"; } 
   @RequestMapping(value={"zhuantai/update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone zhuantaicreate(@Valid @ModelAttribute("dinerBill") DinerBill dinerBill, RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
     try
     {
       String tabId = dinerBill.getTable().getTabId();
       boolean isLock = this.tableService.isLock(tabId);
       if (isLock) {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
         ajax.setMessage("转台失败，目标餐台已经被锁定了!");
         return ajax;
       }
 
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveZhuantai(getCurrentUserRestId(), dinerBill, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e)
     {
       e.printStackTrace();
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       ajax.setMessage("转台失败，请重试!");
       return ajax;
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("转台成功");
     return ajax;
   }
 
   @RequestMapping({"pop/bingtai/{id}"})
   public String bingtai(@PathVariable("id") String id, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="left", defaultValue="0") int left, Model model, HttpServletRequest request)
   {
     Table t = (Table)this.tableService.loadOne(id);
     TableBillRelation lastedTableNormalBillRelation = this.tableService.getLastedTableNormalBillRelation(t);
     if (lastedTableNormalBillRelation != null)
     {
       model.addAttribute("dinerBill", lastedTableNormalBillRelation.getDinerBill());
     }
 
     List tableAreas = this.tableAreaService.getTableAreaByRestID(getCurrentUserRestId());
 
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_dinnerStatus", DinnerStatusEnum.USING.getCode());
     Page tables = this.tableService.searchBySql(getCurrentUserRestId(), id, searchParams, pageNumber, 28, null, new String[] { "tabNo" },null);
     this.tableService.flushCurrentTable(tables.getContent());
 
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
 
     searchParams.remove("EQ_dinnerStatus");
     model.addAttribute("searchNostatusParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
 
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("tables", tables);
     model.addAttribute("left", Integer.valueOf(left));
     return "index/bingtaiForm"; } 
   @RequestMapping(value={"bingtai/update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone bingtaicreate(@Valid @ModelAttribute("dinerBill") DinerBill dinerBill, RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
     try
     {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveBingtai(getCurrentUserRestId(), dinerBill, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e)
     {
       e.printStackTrace();
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       ajax.setMessage("并台失败，请重试!");
       return ajax;
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("并台成功");
     return ajax; }
 
   @RequestMapping(value={"pop/yuding/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String yudingForm(Model model, @RequestParam(value="tabId", defaultValue="") String tabId) {
     TableOrder t = new TableOrder();
     model.addAttribute("tableOrder", t);
     model.addAttribute("action", "create");
     return "index/yudingForm";
   }
 
   @RequestMapping(value={"pop/employee/select"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String employeeSelected(@RequestParam(value="page", defaultValue="1") int pageNumber, Model model, @RequestParam(value="type", defaultValue="3") String type, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     Page employees = this.employeeService.searchEmployee(getCurrentUserRestId(), searchParams, type, pageNumber, 28, "showSeq");
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
 
     model.addAttribute("type", type);
     model.addAttribute("employees", employees);
     return "index/employeeSelected";
   }
 
   @RequestMapping(value={"pop/table/select"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String tableSelected(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="popTableType", defaultValue="1") int popTableType, @RequestParam(value="left", defaultValue="0") int left, @RequestParam(value="from", defaultValue="") String from, @RequestParam(value="tabId", defaultValue="tabId") String tabId, @RequestParam(value="tabNo", defaultValue="tabNo") String tabNo, @RequestParam(value="tableAreaId", defaultValue="", required=false) String tableAreaId, Model model, HttpServletRequest request)
   {
     List tableAreas = this.tableAreaService.getTableAreaByRestID(getCurrentUserRestId());
 
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
 
     if (popTableType == 1)
     {
       searchParams.put("EQ_dinnerStatus", DinnerStatusEnum.IDLE.getCode());
     }
 
     Page tables = this.tableService.searchBySql(getCurrentUserRestId(), null, searchParams, pageNumber, 28, null, new String[] { "tabNo" },null);
     calTableOrderStatus(tables.getContent());
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("searchMapParams", replaceDot(searchParams));
     model.addAttribute("tableAreas", tableAreas);
     model.addAttribute("tables", tables);
     model.addAttribute("popTableType", Integer.valueOf(popTableType));
     model.addAttribute("left", Integer.valueOf(left));
     model.addAttribute("from", from);
     model.addAttribute("tabId", tabId);
     model.addAttribute("tabNo", tabNo);
     model.addAttribute("tableAreaId", tableAreaId);
     return "index/tableSelected";
   }
   @RequestMapping(value={"pop/chedan/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String chedan(@PathVariable("id") String id, Model model, HttpServletRequest request) {
     List speOpReasons = this.speOpReasonService.findByRestIdAndReaType(getCurrentUserRestId(), SpecialReasonTypeEnum.CANCEL_BILL_REASON.getCode());
     model.addAttribute("speOpReasons", speOpReasons);
     model.addAttribute("dinerBill", this.dinerBillService.getOne(id));
     return "index/chedanForm";
   }
 
   @RequestMapping(value={"pop/chedan/update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone chedanupdate(DinerBill dinerBill, @RequestParam(value="cancelReasonId", required=false) String cancelReasonId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     if (dinerBill.getIsChedan().equals(TrueFalseEnum.TRUE.getCode()))
     {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveChedanDinerBill(dinerBill.getBillId(), getCurrentUserRestId(), cancelReasonId, map);
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("撤单成功");
 
       doSynchMultiTable(map);
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       ajax.setMessage("该账单不允许撤单");
     }
     ajax.setRel(dinerBill.getBillId());
     return ajax;
   }
 
   @RequestMapping(value={"cuicai/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone cuicai(@PathVariable("id") String id, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     List printers = this.printerService.findByRestIdAndStatusAndTypeAndUserPrintId(getCurrentUserRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode(), getCurrentUserId());
     if ((printers == null) || (printers.size() == 0))
     {
       ajax.setStatusCode("500");
       ajax.setMessage("无法催菜，请先设置后厨打印机！");
       ajax.setRel(id);
       return ajax;
     }
 
     int unServeNum = 0;
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(id);
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     for (DinerBillDishe e : dinerBillDishes)
     {
       if (!DishesStatusEnum.UNSERVE.getCode().equals(e.getDishesStatus()))
         continue;
       unServeNum++;
     }
 
     List<DinerBillDishesSet> dinerBillDishesSets = dinerBill.getDinerBillDishesSets();
     for (DinerBillDishesSet e : dinerBillDishesSets)
     {
       if (!DishesStatusEnum.UNSERVE.getCode().equals(e.getDsStatus()))
         continue;
       unServeNum++;
     }
 
     if (unServeNum == 0)
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       ajax.setMessage("无法催菜，此账单没有未上菜！");
       ajax.setRel(id);
       return ajax;
     }
     LinkedHashMap map = new LinkedHashMap();
     dinerBill = this.dinerBillService.saveCuiCai(id, map);
 
     doSynchMultiTable(map);
     try
     {
       this.cuicaiPrinterService.printCuiCaiAll(getCurrentUserRestId(), dinerBill);
     } catch (PrinterException e) {
       e.printStackTrace();
       ajax.setStatusCode("500");
       ajax.setMessage("催菜成功，打印失败");
       ajax.setRel(id);
       return ajax;
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("催菜成功");
     ajax.setRel(id);
     return ajax;
   }
 
   @RequestMapping(value={"qingtai/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone qingtai(@PathVariable("id") String id, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveQingtaiDinerBill(id, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("清台成功");
     ajax.setRel(id);
     return ajax;
   }
 
   @RequestMapping(value={"checkAllTableStatus"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone checkAllTableStatus(Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     List<Table> tableList = this.tableService.findByRestIdAndIsEnable(getCurrentUserRestId());
 
     this.tableService.flushCurrentTable(tableList);
 
     StringBuilder jsonBuilder = new StringBuilder();
     jsonBuilder.append("[");
     String billId = "";
     String isChedan = TrueFalseEnum.FALSE.getCode();
     SimpleDateFormat format = new SimpleDateFormat("HH:mm");
     Integer peopleNum = Integer.valueOf(0);
     String billTime = "";
     String orderTime = "";
     for (Table table : tableList) {
       TableBillRelation tableBillRelation = table.getCurrentTableBillRelation();
       String dinnerStatus = table.getDinnerStatus();
       String billStatus = tableBillRelation == null ? null : tableBillRelation.getBillStatus();
       if (tableBillRelation != null) {
         DinerBill dinerBill = tableBillRelation.getDinerBill();
         if (dinerBill != null) {
           billId = dinerBill.getBillId();
           isChedan = dinerBill.getIsChedan();
           tableBillRelation.setDinerBill(null);
           dinerBill = null;
         }
       }
 
       billTime = tableBillRelation != null ? format.format(tableBillRelation.getBillTime()) : "";
 
       if (table.getLastedTableOrderBillRelation() != null) {
         TableOrder tableOrder = table.getLastedTableOrderBillRelation().getTableOrder();
         orderTime = tableOrder != null ? format.format(tableOrder.getOrderTime()) : "";
       }
       if ((tableBillRelation != null) && (tableBillRelation.getPeopleNum() != null)) {
         peopleNum = tableBillRelation.getPeopleNum();
       }
       else {
         peopleNum = Integer.valueOf(0);
       }
       jsonBuilder.append(",{'tabId':'" + table.getTabId() + "','billId':'" + billId + "','billTime':'" + billTime);
       jsonBuilder.append("','peopleNum':'" + peopleNum + "','dinnerStatus':'" + dinnerStatus);
       jsonBuilder.append("','billStatus':'" + billStatus + "','isChedan':'" + isChedan);
       jsonBuilder.append("','isHasTableOrder':'" + table.getIsHasTableOrder());
       jsonBuilder.append("','isAtOrderWarnTime':'" + table.getIsAtOrderWarnTime());
       jsonBuilder.append("','orderTime':'" + orderTime);
       jsonBuilder.append("','seatNum':'" + table.getSeat());
       jsonBuilder.append("'}");
       table.setTableBillRelations(null);
       table.setCurrentTableBillRelation(null);
       table.setLastedTableOrderBillRelation(null);
       table.setDinerBills(null);
       table.setTableOrders(null);
       table.setTablePics(null);
       table = null;
     }
     tableList = null;
     jsonBuilder.append("]");
     String jsonStr = jsonBuilder.toString().replaceFirst(",", "");
     jsonBuilder = null;
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(jsonStr);
     return ajax;
   }
 
   @RequestMapping(value={"getTableStatusNumber"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getTableStatusNumber(Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     int all = 0;
     int using = 0;
     int payed = 0;
     int idle = 0;
 
     List<Table> tables = this.tableService.findByRestIdAndIsEnable(getCurrentUserRestId());
     all = tables != null ? tables.size() : 0;
     for (Table e : tables)
     {
       if (e.getDinnerStatus().equals(DinnerStatusEnum.IDLE.getCode()))
       {
         idle++;
       }
       if (e.getDinnerStatus().equals(DinnerStatusEnum.USING.getCode()))
       {
         using++;
       }
       if (e.getDinnerStatus().equals(DinnerStatusEnum.SETTLE.getCode()))
       {
         payed++;
       }
       e = null;
     }
     tables = null;
 
     String restId = getCurrentUserRestId();
     Object billStatusList = new ArrayList();
     ((List)billStatusList).add(BillStatusEnum.NOT_PLACE_ORDER.getCode());
     ((List)billStatusList).add(BillStatusEnum.PLACE_ORDER.getCode());
     ((List)billStatusList).add(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     Long noPayWaimaiBillCount = this.dinerBillService.getCountByBillTypeAndBillStatusIn(restId, BillTypeEnum.WAIMAI_BILL.getCode(), (List)billStatusList);
     billStatusList = null;
 
     Long resettleBillCount = this.dinerBillService.getCountByBillStatus(restId, BillStatusEnum.RESETTLE.getCode());
     String noPayWaimaiSizeStr = "0";
     String resettlebillsSizeStr = "0";
     if (noPayWaimaiBillCount != null) {
       noPayWaimaiSizeStr = String.valueOf(noPayWaimaiBillCount);
     }
     if (resettleBillCount != null) {
       resettlebillsSizeStr = String.valueOf(resettleBillCount);
     }
     String jsonStr = "[{'all':'" + all + "','using':'" + using + "','payed':'" + payed + "','idle':'" + idle + "','noPayWaimaiSize':'" + noPayWaimaiSizeStr + "','resettlebillsSize':'" + resettlebillsSizeStr + "'}]";
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(jsonStr);
     ajax.setRel("");
     return (DwzAjaxDone)ajax;
   }
 
   @RequestMapping(value={"pop/permission/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String permissionForm(@RequestParam(value="functionType", defaultValue="1") String functionType, @RequestParam(value="functionId", defaultValue="") String functionId, Model model)
   {
     model.addAttribute("functionType", functionType);
     model.addAttribute("functionId", functionId);
     return "index/permissionForm";
   }
 
   @RequestMapping(value={"permission/get"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone permissionGet(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value="functionType", defaultValue="1") String functionType, @RequestParam(value="functionId", defaultValue="") String functionId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     String isPermission = PermissionResultEnum.ERROR.code;
 
     Employee employee = this.employeeService.findByLoginUsernameAndLoginPassword(username, password, getCurrentUserRestId());
     if (employee != null)
     {
       isPermission = PermissionResultEnum.NOROLE.code;
       if (this.employeeService.isSuperRole(employee, getCurrentUserRestId()))
       {
         isPermission = PermissionResultEnum.SUCCESS.code;
       }
     }
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(isPermission);
     ajax.setRel(functionType);
     ajax.setSign(functionId);
     return ajax;
   }
 
   @RequestMapping(value={"pop/poplogin"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String tuicaiReason(Model model, HttpServletRequest request) {
     return "popLogin";
   }
 
   @RequestMapping(value={"poplogin"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone permissionGet(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     String isPermission = PopLoginResultEnum.ERROR.code;
 
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     if (!user.getLoginName().equals(username))
     {
       isPermission = PopLoginResultEnum.NOCURRENTUSER.code;
     }
     else
     {
       Employee employee = this.employeeService.findByLoginUsernameAndLoginPassword(username, password, getCurrentUserRestId());
       if (employee != null)
       {
         isPermission = PopLoginResultEnum.SUCCESS.code;
       }
       else
       {
         isPermission = PopLoginResultEnum.ERROR.code;
       }
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(isPermission);
     ajax.setValue(PopLoginResultEnum.getDesc(isPermission));
     return ajax;
   }
   @RequestMapping(value={"getLockLeaveTime"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getLockLeaveTime(Model model) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     Integer leaveTime = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getLeaveTime();
     int seconds = leaveTime.intValue() * 60;
     ajax.setMessage(String.valueOf(seconds));
     return ajax;
   }
 
   public static void main(String[] args)
   {
     String s = "[,{}]";
 
     System.out.println(s.replaceFirst(",", ""));
   }
 
   @ModelAttribute
   public void getDinerBill(@RequestParam(value="id", required=false) String id, Model model)
   {
     if ((id != null) && (!id.isEmpty())) {
       DinerBill d = (DinerBill)this.dinerBillService.getOne(id);
 
       d.setTable(null);
       model.addAttribute("dinerBill", d);
     }
   }
 
   private Map<String, Object> replaceDot(Map<String, Object> searchParams) {
     Map newMap = new HashMap();
     for (String e : searchParams.keySet())
     {
       String key = e.replaceAll("\\.", "_");
       newMap.put(key, searchParams.get(e));
     }
     return newMap;
   }
 
   private static enum PermissionResultEnum
   {
     ERROR("0", "用户名密码错误"), 
     SUCCESS("1", "正确"), 
     NOROLE("2", "没有权限");
 
     public String code;
     public String desc;
 
     private PermissionResultEnum(String code, String desc) { this.code = code;
       this.desc = desc;
     }
   }
 
   private static enum PopLoginResultEnum
   {
     ERROR("0", "用户名密码错误"), 
     SUCCESS("1", "正确"), 
     NOCURRENTUSER("2", "不是当前用户");
 
     public String code;
     public String desc;
 
     private PopLoginResultEnum(String code, String desc) { this.code = code;
       this.desc = desc; }
 
     public static String getDesc(String code) {
       for (PopLoginResultEnum status : values()) {
         if (status.getCode().equalsIgnoreCase(code)) {
           return status.getDesc();
         }
       }
       return "未知";
     }
     public String getDesc() {
       return this.desc;
     }
 
     public String getCode() {
       return this.code;
     }
   }
   
   
   @RequestMapping(value={"logoutCheck"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public void logoutCheck(RedirectAttributes redirectAttributes,Model model) { 
	   DwzAjaxDone ajax = new DwzAjaxDone();
	   List<Table> list = sysLogService.logoutCheck(getCurrentUserRestId());
	   if(list.size()>0){
		   ajax.setValue("false");
		   model.addAttribute("table", list);
	   }else{
		   ajax.setValue("true");
	   }
	   model.addAttribute("ajax", ajax);
   }
   
   

 }

